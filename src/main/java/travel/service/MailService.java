package travel.service;

import travel.obj.Mail;

import javax.mail.MessagingException;

public interface MailService {
    void sendAttachMail(Mail mailModel) throws MessagingException;
}
