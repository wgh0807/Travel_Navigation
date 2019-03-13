package travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import travel.obj.Mail;
import travel.service.MailService;

import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Date;

@Service
public class MailServiceImpl implements MailService {

    private JavaMailSender mailSender;
    private MimeMessage mimeMessage;
    private static Logger logger = Logger.getLogger(MailServiceImpl.class);

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    public void setMimeMessage(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
    }

    @Override
    public void sendAttachMail(Mail mailModel) {
        try {
            MimeMessageHelper mailMessage = new MimeMessageHelper(this.mimeMessage, true, "UTF-8");
            mailMessage.setFrom(mailModel.getFromAddress());//设置邮件发送者
            mailMessage.setSubject(mailModel.getSubject());//设置邮件主题
            mailMessage.setSentDate(new Date());//设置邮件发送时间
            mailMessage.setText(mailModel.getContent(),true);//设置邮件正文，true表示以html形式发送
            String[] toAddresses = mailModel.getToAddress().split(";");//得到要发送地址数组
            for (String toAddress:toAddresses) {
                mailMessage.setTo(toAddress);
//                for (String fileName : mailModel.getAttachFileNames()) {
//                    mailMessage.addAttachment(fileName,new File(fileName));
//                }

                this.mailSender.send(this.mimeMessage);
                logger.info("sendEmail To : "+toAddress);
            }

        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }
}
