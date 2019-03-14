package travel.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import travel.obj.Mail;
import travel.service.MailService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("mail")
public class MailController extends BaseController {

    Logger logger = Logger.getLogger(MailController.class);
    private MailService mailService;

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping(value = "sendTest")
    @ResponseBody
    public void sendEmail(HttpServletRequest request) {
        Mail mail = new Mail();
        mail.setFromAddress("mail@wgh0807.cn");
        mail.setToAddress("liangkangfa@qq.com;");
        mail.setSubject("测试邮件标题");
        mail.setContent("测试邮件内容");
        mailService.sendAttachMail(mail);

        Map message = new HashMap();
        message.put("state", "success");
    }



}
