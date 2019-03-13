brand = document.getElementById('brand')
signIn = document.getElementById('signIn')
signUp = document.getElementById('signUp')
jump2signIn = document.getElementById('jump2signIn')
jump2signUp = document.getElementById('jump2signUp')
sendtokenBtn = document.getElementById("sendtoken")

signInBtn = document.getElementById('signInBtn')
signUpBtn = document.getElementById('signUpBtn')



window.onload=function(){
	// console.log("page on load")
	// todo: ajax获取session中的errTime

    
	
}
window.onbeforeunload=function(){
	// todo: ajax上传更新session中的errTime
	
	// return '..'
}

// 点击‘立刻注册’时的动画效果
jump2signUp.onclick = function() {
	brand.classList.remove('brand-jump')
	brand.classList.add('brand-jump-back')

	signIn.classList.remove('block-show')
	signIn.classList.add('block-hide')
	setTimeout(function() {
		signUp.style.display = ''
		signUp.classList.remove('block-hide')
		signUp.classList.add('block-show')
		signIn.style.display = 'none'
	}, 300)
}

// 点击‘返回登陆’时的动画效果
jump2signIn.onclick = function() {
	brand.classList.remove("brand-jump-back")
	brand.classList.add('brand-jump')

	signUp.classList.remove('block-show')
	signUp.classList.add('block-hide')
	setTimeout(function() {
		signIn.style.display = ''
		signIn.classList.remove('block-hide')
		signIn.classList.add('block-show')
		signUp.style.display = 'none'
	}, 300)

}

// input输入框
// sign In 部分
signInEmail = document.getElementById("username")
signInPassword = document.getElementById('password')
rebotCheckInput = document.getElementById('rebotCheckInput')
// sign Up 部分
signUpName = document.getElementById("name")
signUpPassword = document.getElementById("passwd")
signUpWdsub = document.getElementById("wdsub")
signUpMail = document.getElementById("mail")
signUpToken = document.getElementById("token")
robotCheckArea = document.getElementById('robotCheckArea')

// 其他变量
time = 60;
errTime=0;
isSame=0;

// 发送验证码部分
sendtokenBtn.onclick = function() {
	if(signUpMail.value.length <1 ){
        signUpMail.classList.add("border-danger");
        alert("请输入邮箱");
		setTimeout('signUpMail.classList.remove("border-danger")',1000)
	}else{
		// todo: ajax向服务器请求发送验证码
		
		
		sendtokenBtn.innerHTML = '已发送';
        sendtokenBtn.classList.add('disabled');
		timeInterval1 = setInterval(TimeJump,100);
		alert('我们已经向您邮箱发送了验证码，请注意查收')
	}
}

// 表单和错误信息框
signInForm = document.getElementById("signInForm");
signUpForm = document.getElementById("signUpForm");
signInInfo = document.getElementById("info");
signUpInfo = document.getElementById("info2");

// signIn表单提交时事件
signInForm.onsubmit = function () {
    console.log("signIn form submit");
	
	
	// ajax 登陆
	
	// 获取结果
	
	// 成功时
	
	// 报错时
	signInInfo.innerText="用户名或密码错误"
	signInInfo.classList.remove("d-none")
	
	errTime = errTime + 1
	if (errTime == 3) {
	    rebotCheckInput.value = ""
	}
	if (errTime > 2) {
	    robotCheckArea.classList.remove("d-none");
	    rebotCheckInput.required = true
	}
}

// 注册表单提交时事件
signUpForm.onsubmit = function () {
    console.log("signUp form submit")
	if(isSame==1){
		// ajax 注册
		// 获取结果
		// 成功情况
		// 失败情况
		signUpInfo.innerText="注册失败"
		signUpInfo.classList.remove("d-none")
		
		
	}else{
		// 注册页两次密码不一致。
	}
}

// 点击登陆按钮，监控错误次数，适时提供验证码功能
signInBtn.onclick = function () {
	signInInfo.classList.add("d-none")
	signInInfo.innerText = ""

    errTime = errTime + 1
    if (errTime == 3) {
        rebotCheckInput.value = ""
    }
    if (errTime > 2) {
        robotCheckArea.classList.remove("d-none");
        rebotCheckInput.required = true
    }
};

signUpBtn.onclick = function(){
	signUpInfo.classList.add("d-none")
	signUpInfo.innerText = ""
}


PasswdLockAreas = document.getElementsByClassName("lockedArea")
for (var i in PasswdLockAreas){
	PasswdLockAreas[i].onclick=lockOrUnlock
}

function lockOrUnlock(){
	
	console.log(this,this.getAttribute("data-target"))
	targetId = this.getAttribute("data-target")
	targetElement = document.getElementById(targetId)
	if(targetElement.type == "password"){
		targetElement.type = "text"
		this.classList.add("unlocked")
	}else{
		targetElement.type = "password"
		this.classList.remove("unlocked")
	}
}

// 注册页面 密码输入框失去焦点，调用自动检测方法
signUpWdsub.onblur = passwdAndWdsubCheck;
signUpPassword.onblur = passwdAndWdsubCheck;

// 定义方法区
// 注册：密码和确定密码失去焦点时自动判断是否相同
function passwdAndWdsubCheck () {
	signUpInfo.classList.add("d-none")
	signUpInfo.innerText=""
	signUpBtn.classList.remove("disabled")
	
	console.log("blur action")
	console.log(signUpPassword.value.length)
	if(signUpPassword.value.length<6){
		signUpInfo.classList.remove("d-none")
		signUpInfo.innerText="密码长度过短"
		signUpBtn.classList.add("disabled")
		isSame=0;
	}else if (signUpWdsub.value != signUpPassword.value) {
        signUpInfo.classList.remove("d-none")
		signUpInfo.innerText="两次密码输入不一致"
		signUpBtn.classList.add("disabled")
		isSame=0;
    }else{
		isSame=1;
	}
};

// 发送验证码计时函数
function TimeJump(){
	time=time-1;
	if (time == 0) {
		time = 60;
        sendtokenBtn.classList.remove('disabled');
        sendtokenBtn.innerText = "重新发送";
		clearInterval(timeInterval1);
	} else{
		sendtokenBtn.innerText = '重新发送('+time+')';
	}
}








