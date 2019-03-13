package travel.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail implements Serializable {
    private String emailHost;//发件人邮箱服务器
    private String emailForm;//发件人邮箱
    private String emailUserName;//发件人用户名
    private String emailPassword;//发件人密码
    private String toEmails;//收件人邮箱，多个邮箱以";"间隔
    private String subject;//邮件主题
    private String content;//邮件内容
    private Map<String, String > pictures;//附加图片，为空时无图片
    private Map<String, String > attachments;//邮件中的附件，为空时无附件。map中的key为附件ID，value为附件地址
    private String fromAddress;//发件人地址
    private String toAddress;//收件人地址，可以为很多个
    private String[] attachFileNames;


}
