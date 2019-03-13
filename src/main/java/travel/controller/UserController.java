package travel.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import travel.obj.User;
import travel.service.UserService;
import travel.util.Pagination;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    private UserService userService=null;
    private int errTime = 0;

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
        message.put("errTime", this.errTime);
        return message;
    }

    @RequestMapping("getErrTime")
    @ResponseBody
    public Map getErrTime(){
        Map message = new HashMap();
        int sessionErrTime = (int)session.getAttribute("errTime");
        this.errTime = (sessionErrTime > this.errTime) ? sessionErrTime : errTime;
        message.put("errTime", this.errTime);

        return message;
    }


    @RequestMapping("signIn")
    @ResponseBody
    public Map signIn(User user){
        Map message = new HashMap<>();
        user.setMail(user.getMail().toLowerCase());

        User trueUser = userService.signIn(user);
        if (trueUser != null) {
            message.put("result", "success");
            message.put("message", "登陆成功");
            message.put("nextSite", "../home/");
            session.setAttribute("user", trueUser);
        } else {
            message.put("result", "fail");
            this.errTime += 1;
            session.setAttribute("errTime", this.errTime);
            message.put("message", "邮箱或密码错误");
            message.put("errtime", this.errTime);
        }

        System.out.println("signIn : "+message);
        return message;
    }

    @RequestMapping("signUp")
    @ResponseBody
    public Map signUp(User user) {
        Map message = new HashMap<>();
        user.setMail(user.getMail().toLowerCase());
        if (userService.emailCheck(user.getMail())==0){
            message.put("result", "fail");
            message.put("message", "邮箱已存在");
        }
        else{
            int res = userService.signUp(user);
            if (res == 1) {
                message.put("result", "success");
                message.put("message", "注册成功");
            }else {
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
        email = email.toLowerCase();
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


}
