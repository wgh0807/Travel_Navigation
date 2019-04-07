document.write("<script language=javascript src='js/global.js'></script>");



brand = document.getElementById('brand')
signIn = document.getElementById('signIn')
signUp = document.getElementById('signUp')
jump2signIn = document.getElementById('jump2signIn')
jump2signUp = document.getElementById('jump2signUp')
sendtokenBtn = document.getElementById("sendtoken")

signInBtn = document.getElementById('signInBtn')
signUpBtn = document.getElementById('signUpBtn')


// input输入框
// sign In 部分
signInEmail = document.getElementById("email")
signInPassword = document.getElementById('password')
robotCheckInput = document.getElementById('robotCheckInput')
tokenImg = document.getElementById("token-img");
robotCheckArea = document.getElementById('robotCheckArea')
// sign Up 部分
signUpName = document.getElementById("name")
signUpPassword = document.getElementById("passwd")
signUpWdsub = document.getElementById("wdsub")
signUpMail = document.getElementById("mail")
signUpToken = document.getElementById("token")

// 其他变量
time = 60;
errTime = 0;
isSame = 0;
mailExist = 1;

// 表单和错误信息框
signInForm = document.getElementById("signInForm");
signUpForm = document.getElementById("signUpForm");
signInInfo = document.getElementById("info");

// serverAddress = "http://127.0.0.1:8080/";


signUpInfo = document.getElementById("info2");


window.onload = function () {
    // console.log("page on load")
    signInEmail.value = "";
    signInPassword.value = "";
    robotCheckInput.value = "";

    //  ajax获取session中的errTime
    $.ajax({
        url: serverAddress+"user/getErrTime",
        type: "POST",
        xhrFields:{
            withCredentials:true
        },
        data: {},
        success: function (data, state) {
            console.log(state);
            errTime = data.errTime;
            console.log("errTime", errTime);

            if (data.errTime >= 3) {
                robotCheckArea.classList.remove("d-none");
                tokenImg.innerText = data.token;
                robotCheckInput.required = true
            }
        },
        error: function (state, msg) {
            console.log(state, msg);
            errTime = 0
        }
    })

}
window.onbeforeunload = function () {
    //  ajax上传更新session中的errTime
    $.ajax({
        url: serverAddress+"user/setErrTime",
        type: "POST",
        xhrFields:{
            withCredentials:true
        },
        data: {"errTime": errTime},
        success: function (data, state) {
            console.log(data)
        },
        error: function (state) {
            console.log(state)
        }
    })
};

// 点击‘立刻注册’时的动画效果
jump2signUp.onclick = function () {
    brand.classList.remove('brand-jump');
    brand.classList.add('brand-jump-back');

    signIn.classList.remove('block-show');
    signIn.classList.add('block-hide');
    setTimeout(function () {
        signUp.style.display = '';
        signUp.classList.remove('block-hide');
        signUp.classList.add('block-show');
        signIn.style.display = 'none'
    }, 300)
};

// 点击‘返回登陆’时的动画效果
jump2signIn.onclick = function () {
    brand.classList.remove("brand-jump-back");
    brand.classList.add('brand-jump');

    signUp.classList.remove('block-show');
    signUp.classList.add('block-hide');
    setTimeout(function () {
        signIn.style.display = '';
        signIn.classList.remove('block-hide');
        signIn.classList.add('block-show');
        signUp.style.display = 'none'
    }, 300)
};


// 发送验证码部分
sendtokenBtn.onclick = function () {
    if (signUpMail.value.length < 1) {
        signUpMail.classList.add("border-danger");
        alert("请输入邮箱");
        setTimeout('signUpMail.classList.remove("border-danger")', 1000)
    } else if (mailExist == 1) {
        alert("邮箱已存在！");
        return ;
    } else {
        // todo: ajax向服务器请求发送邮箱验证码
        $.ajax({
            url: serverAddress+"user/sendEmailToken",
            type: "POST",
            xhrFields:{
                withCredentials:true
            },
            data: {
                "mail": signUpMail.value
            },
            success: function (data, status) {
                console.log(data)
                //    发送成功
                if(data.result=="success"){
                    sendtokenBtn.innerHTML = '已发送';
                    sendtokenBtn.classList.add('disabled');
                    timeInterval1 = setInterval(TimeJump, 1000);
                    alert("发送成功。"+data.sendStatus)
                }else{
                    alert("发送失败。"+data.sendStatus)
                }

            },
            error: function (status) {
                //    网络问题，发送失败
                alert("您的网络不给力哦")
            }
        })
    }
};

// signIn表单提交时事件
signInForm.onsubmit = function () {
    console.log("signIn form submit");
    // ajax 登陆
    $.ajax({
        url: serverAddress+"user/signIn",
        type: "POST",
        xhrFields:{
            withCredentials:true
        },
        data: {
            "errT": errTime,
            "mail": signInEmail.value,
            "password": signInPassword.value,
            "robotCheckInput": robotCheckInput.value
        },
        // 获取结果

        success: function (data, state) {
            console.log(data, state)
            // 成功时
            if (data.result == "success") {
                //    todo 测试session是否保存成功
                $.ajax({
                    url: serverAddress+"user/currentUser",
                    type: "POST",
                    xhrFields:{
                        withCredentials:true
                    },
                    data: {},
                    success: function (data, state) {
                        console.log(data);
                    },
                    error: function (state, msg) {
                        console.log(state, msg);
                    }
                })

                setTimeout(window.location.href = data.nextSite, 1000);

            } else {
                // 报错时
                signInInfo.innerText = data.message
                signInInfo.classList.remove("d-none")

                errTime = data.errTime

                if (data.errTime >= 3) {
                    robotCheckArea.classList.remove("d-none");
                    tokenImg.innerText = data.token;
                    robotCheckInput.required = true
                }
            }
        },
        error: function () {
            alert("网络不给力哦")
        }
    });
}

// 注册表单提交时事件
signUpForm.onsubmit = function () {
    console.log("signUp form submit");

    signUpInfo.innerText = "";
    signUpInfo.classList.add("d-none");
    signUpInfo.classList.remove("text-success");
    signUpInfo.classList.remove("text-danger");

    if (isSame == 1) {
        // ajax 注册
        $.ajax({
            url: serverAddress+"user/signUp",
            type: "POST",
            xhrFields:{
                withCredentials:true
            },
            data: {
                "name": signUpName.value,
                "password": signUpPassword.value,
                "mail": signUpMail.value,
                "emailToken": signUpToken.value
            },
            success: function (data, state) {
                // 获取结果
                if (data.result == "success") {
                    //    注册成功
                    signUpInfo.innerText = "注册成功！";
                    signUpInfo.classList.add("text-success");

                    signUpPassword.value = "";
                    signUpWdsub.value = "";

                    setTimeout(jump2signIn.click(), 1000)


                } else {
                    //    注册失败
                    signUpInfo.innerText = "注册失败! " + data.message;
                    signUpInfo.classList.add("text-danger");
                }
            },
            error: function () {
                alert("您的网络不给力哦");
                signUpInfo.innerText = "注册失败! ";
                signUpInfo.classList.add("text-danger");
            }
        });
        signUpInfo.classList.remove("d-none")
    } else {
        // 注册页两次密码不一致。
        signUpInfo.innerText = "两次密码输入不一致";
        signUpInfo.classList.add("text-danger");
        signUpInfo.classList.remove("d-none");
    }
};

// 点击登陆按钮，监控错误次数，适时提供验证码功能
signInBtn.onclick = function () {
    signInInfo.classList.add("d-none");
    signInInfo.innerText = "";

    errTime = errTime + 1;
    // if (errTime == 3) {
    //     robotCheckInput.value = ""
    // }
    // if (errTime > 2) {
    //     robotCheckArea.classList.remove("d-none");
    //     robotCheckInput.required = true
    // }
};

//注册页面邮箱输入框失去焦点自动判断是否已经注册
signUpMail.onblur = function () {
    signUpInfo.classList.add("d-none")
    signUpInfo.classList.remove("text-success");
    signUpInfo.classList.remove("text-danger");
    mailBlock = this;
    if (mailBlock.value.length > 0) {
        $.ajax({
            url: serverAddress+"user/emailCheck",
            type: "POST",
            xhrFields:{
                withCredentials:true
            },
            data: {
                "email": mailBlock.value
            },
            success: function (data) {
                if (data.exist) {
                    //已注册
                    signUpInfo.innerText = "该邮箱已注册！";
                    signUpInfo.classList.add("text-danger");
                    mailExist = 1;
                } else {
                    //未注册
                    signUpInfo.innerText = "该邮箱未注册！";
                    signUpInfo.classList.add("text-success");
                    mailExist = 0;
                }
                signUpInfo.classList.remove('d-none')
            }
        })
    }
};

signUpBtn.onclick = function () {
//    todo 忘了要做啥，先留着
};

PasswdLockAreas = document.getElementsByClassName("lockedArea")
for (var i in PasswdLockAreas) {
    PasswdLockAreas[i].onclick = lockOrUnlock
}

function lockOrUnlock() {

    console.log(this, this.getAttribute("data-target"))
    targetId = this.getAttribute("data-target")
    targetElement = document.getElementById(targetId)
    if (targetElement.type == "password") {
        targetElement.type = "text"
        this.classList.add("unlocked")
    } else {
        targetElement.type = "password"
        this.classList.remove("unlocked")
    }
}

// 注册页面 密码输入框失去焦点，调用自动检测方法
signUpWdsub.onblur = passwdAndWdsubCheck;
signUpPassword.onblur = passwdAndWdsubCheck;

// 定义方法区
// 注册：密码和确定密码失去焦点时自动判断是否相同
function passwdAndWdsubCheck() {
    signUpInfo.classList.add("d-none")
    signUpInfo.innerText = ""
    signUpBtn.classList.remove("disabled")

    console.log("blur action")
    console.log(signUpPassword.value.length)
    if (signUpPassword.value.length < 6) {
        signUpInfo.classList.remove("d-none")
        signUpInfo.innerText = "密码长度过短"
        signUpBtn.classList.add("disabled")
        isSame = 0;
    } else if (signUpWdsub.value != signUpPassword.value) {
        signUpInfo.classList.remove("d-none")
        signUpInfo.innerText = "两次密码输入不一致"
        signUpBtn.classList.add("disabled")
        isSame = 0;
    } else {
        isSame = 1;
    }
};

// 发送验证码计时函数
function TimeJump() {
    time = time - 1;
    if (time == 0) {
        time = 60;
        sendtokenBtn.classList.remove('disabled');
        sendtokenBtn.innerText = "重新发送";
        clearInterval(timeInterval1);
    } else {
        sendtokenBtn.innerText = '重新发送(' + time + ')';
    }
}








