package travel.controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import travel.obj.Mail;
import travel.obj.User;
import travel.service.MailService;
import travel.service.UserService;
import travel.util.Pagination;
import travel.util.TokenGenerator;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    private UserService userService=null;
    private int errTime = 0;
    Logger logger = Logger.getLogger(MailController.class);
    private MailService mailService;

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Autowired
    public void setuserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("index")
    public void index() throws IOException {
        PrintWriter out = response.getWriter();
        out.println("index");
    }

    @RequestMapping("create")
    private String create(User user) {
        System.out.println(user);
        userService.create(user);
        return "redirect:/user/queryAll";
    }

    @RequestMapping("delete/{id}")
    private String delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/user/queryAll";
    }

    @RequestMapping("modify")
    private String modify(User user) {
        userService.modify(user);
        return "redirect:/user/queryAll";
    }

    @RequestMapping("queryAll")
    private String queryAll() {
        session.setAttribute("list", userService.queryAll());
        System.out.println(session.getAttribute("list"));
        return "redirect:/temp/user/list.jsp";
    }

    @RequestMapping("queryById/{id}")
    private String queryById(@PathVariable("id") Integer id) {
        session.setAttribute("user", userService.queryOne(id));
        return "redirect:/temp/user/edit.jsp";
    }


    @RequestMapping("setErrTime")
    @ResponseBody
    public Map setErrTime(@RequestParam int errTime){
        Map message = new HashMap();
        this.errTime = (errTime>this.errTime)?errTime:this.errTime;
        session.setAttribute("errTime",this.errTime);

        message.put("errTime", this.errTime);
        return message;
    }

    @RequestMapping("getErrTime")
    @ResponseBody
    public Map getErrTime(){
        Map message = new HashMap();
        if (session.getAttribute("errTime")==null){
            session.setAttribute("errTime",this.errTime);
        }
        int sessionErrTime = (int)session.getAttribute("errTime");

        this.errTime = (sessionErrTime > this.errTime) ? sessionErrTime : errTime;
        message.put("errTime", this.errTime);
        String token = checkRobot();
        if (token != null) {
            message.put("tokenImg", "img/tokenimg.png");
            message.put("token",token);
        }

        return message;
    }


    @RequestMapping("signIn")
    @ResponseBody
    public Map signIn(Integer errT,User user, String robotCheckInput){
        System.out.println("\n\n user signIn :\n errT: " + errT + "\n user :" + user + "\n robotCheckInput : " + robotCheckInput);

        this.errTime = Math.max(errT, this.errTime);
        Map message = new HashMap<>();
        user.setMail(user.getMail().toLowerCase());
        String sessionRebotCheck = (String)session.getAttribute("robotCheck");
//        有要求提交验证码
        if (sessionRebotCheck != null && !sessionRebotCheck.equalsIgnoreCase(robotCheckInput)) {
//            验证码有误
            System.out.println("验证码有误");

            message.put("result", "fail");
            message.put("message", "验证码有误");
            this.errTime += 1;
            message.put("errTime", this.errTime);
//            todo: 检测错误次数。达到3次则生成随机验证码图片，返回图片路径(生成图片在 checkRobot 方法中调用，暂未实现)
            String token = checkRobot();
            if (token != null) {
                message.put("tokenImg", "img/tokenimg.png");
//                仅供没有生成图片验证码时使用
                message.put("token",token);
            }
            return message;
        }

//        无验证码或验证码通过
        System.out.println("无验证码或验证码通过");
        User trueUser = userService.signIn(user);

        System.out.println("找到用户"+trueUser);

        if (trueUser != null) {
//            登陆成功
            System.out.println("登陆成功");
            message.put("result", "success");
            message.put("message", "登陆成功");
            message.put("nextSite", "home/");
            session.setAttribute("user", trueUser);
            session.removeAttribute("rebotCheck");
        } else {
//            登陆失败
            System.out.println("登陆失败");
            message.put("result", "fail");
            this.errTime += 1;
            session.setAttribute("errTime", this.errTime);
            message.put("message", "邮箱或密码错误");
            message.put("errTime", this.errTime);
            String token = checkRobot();
            if (token != null) {
                message.put("tokenImg", "img/tokenimg.png");
                message.put("token",token);
            }
        }

        System.out.println("signIn : "+message);
        return message;
    }

    @RequestMapping("signUp")
    @ResponseBody
    public Map signUp(User user,String emailToken) {
        System.out.println("\n\nsignUp model");
        System.out.println("data : user = "+user+"emailToken : "+emailToken);
        user.setMail(user.getMail().toLowerCase().trim());


        Map message = new HashMap<>();

        System.out.println("token check resule is "+user.getMail()+session.getAttribute(user.getMail())+(session.getAttribute(user.getMail()) == null || !((String)session.getAttribute(user.getMail())).equalsIgnoreCase(emailToken)));
        System.out.println("signUp model begin");
        if (session.getAttribute(user.getMail()) == null || !((String)session.getAttribute(user.getMail())).equalsIgnoreCase(emailToken)) {
            message.put("result", "fail");
            message.put("message", "验证码有误");
            return message;
        }
        System.out.println("signUp token check end");

        if (userService.emailCheck(user.getMail())==1){
            System.out.println("email exist");
            message.put("result", "fail");
            message.put("message", "邮箱已存在");
            return message;
        }
        else{
            System.out.println("email not exist");
            int res = userService.signUp(user);
            if (res == 1) {
                System.out.println("注册成功");
                message.put("result", "success");
                message.put("message", "注册成功");
            }else {
                System.out.println("注册失败");
                message.put("result", "fail");
                message.put("message", "其他问题");
            }
        }
        System.out.println("signUp : "+message);
        return message;
    }

    @RequestMapping(value = "emailCheck",method = RequestMethod.POST)
    @ResponseBody
    public Map emailCheck(@RequestParam String email){
        Map message = new HashMap();
        email = email.toLowerCase().trim();
        int result = userService.emailCheck(email);
        message.put("exist", (result == 1) ? Boolean.TRUE : Boolean.FALSE);
        return message;
    }


    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    @ResponseBody
    public Map changePassword(User user,String newPassword){
        Map message = new HashMap();
        int result = userService.changePassword(user, newPassword);
        if (result==1) {
            message.put("result", "success");
            message.put("message", "修改成功");
        }else{
            message.put("result", "fail");
            message.put("message", "旧密码不匹配");
        }

        return message;
    }

    @RequestMapping("currentUser")
    @ResponseBody
    public Map getCurrentUser() {
        Map data = new HashMap();

        data.put("currentUser", session.getAttribute("user"));

        return data;

    }

    @RequestMapping(value = "sendEmailToken",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map sendEmailToken(@RequestParam String mail) {
        mail = mail.toLowerCase().trim();
        Map message = new HashMap();
//      先自动生成邮箱验证码 token ,然后调用 sendTokenByEmail 方法向用户发送邮件
        String token = TokenGenerator.emailToken();
//        将验证码放入session中，创建时间线程，15分钟后删除emailToken
        System.out.println("\n\nsendTokenByEmail\n mail : "+mail+"\n emailToken:"+token);
        session.setAttribute(mail, token);
        System.out.println("set mail :"+mail+" token :"+token);
        System.out.println("next step sendTokenByEmail");
        try {
            sendTokenByEmail(mail,token);
            message.put("result", "success");
            message.put("sendStatus","验证码已发送,有效期15分钟");
        } catch (Exception e) {
            message.put("result", "fail");
            message.put("sendStatus", "邮箱不存在，请检查");
        }

        System.out.println("welcome to back");

//todo 验证码超时过期
//        try {
//            final Timer timer = new Timer();
//            timer.schedule(new TimerTask() {
//                String email = ;
//                @Override
//                public void run() {
//                    session.removeAttribute(email);
//                    System.out.println("邮箱验证码 已清理，token = "+token);
//                    timer.cancel();
//                }
//            },15*60*1000);
//        } catch (Exception e) {
////            e.printStackTrace();
//
//        }


        return message;

    }

//其他方法，不直接参与服务
//    发送邮箱验证码
    public void sendTokenByEmail(String targetMailAddress,String token) throws MessagingException {
        Mail mail = new Mail();
        mail.setFromAddress("mail@wgh0807.cn");
        mail.setToAddress(targetMailAddress);
        mail.setSubject("畅游tour 注册邮箱验证");
        String content = "<h1>畅游tour</h1><br/>尊敬的 "+targetMailAddress+" 用户：<br/>&nbsp;&nbsp;您正在注册 畅游tour 平台，" +
                "您本次的验证码是： "+token+" 。请不要泄露给他人，如不是您本人操作请忽略。系统邮件请勿回复。";
        mail.setContent(content);
        mailService.sendAttachMail(mail);
        logger.info("send mail to "+targetMailAddress);
    }

//    检测错误次数是否达到三次，如果达到则创建验证码
    public String checkRobot() {
        String token="";
        int errTime = (int)session.getAttribute("errTime");
        if (errTime >= 3) {
//          生成验证码
            token = TokenGenerator.getRobotToken();
//            todo: 生成验证码图片(未完成)

//            将token装入session会话中
            session.setAttribute("robotCheck",token);
        }
        return token;
    }


}
