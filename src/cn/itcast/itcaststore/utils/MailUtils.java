package cn.itcast.itcaststore.utils;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
/**
 * 发送邮件的工具类
 */
public class MailUtils {
	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session
		Properties props = new Properties();
		// 设置邮件传输协议为SMTP
		props.setProperty("mail.transport.protocol", "SMTP");
		// 设置SMTP服务器地址
		props.setProperty("mail.host", "smtp.sohu.com");
		// 设置SMTP服务器是否需要用户验证，需要验证设置为true
		props.setProperty("mail.smtp.auth", "true");
		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("itcast_duhong", "1234567890");
			}
		};
		Session session = Session.getInstance(props, auth);
		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("itcast_duhong@sohu.com")); // 设置发送者
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者
		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");
		message.setContent(emailMsg, "text/html;charset=utf-8");
		// 3.创建 Transport用于将邮件发送
		Transport.send(message);
	}
}
