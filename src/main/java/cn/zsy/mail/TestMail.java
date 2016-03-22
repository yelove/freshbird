package cn.zsy.mail;

import java.io.IOException;
import java.security.Security;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class TestMail {

	public static void main(String args[]) {
		try {
			send_email();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("restriction")
	public static void send_email() throws IOException, AddressException, MessagingException {

		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		Properties props = System.getProperties();
		// props.setProperty("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		// props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.store.protocol", "smtp");
		props.setProperty("mail.smtp.host", "184.107.164.138");
		props.setProperty("mail.smtp.port", "25");
		// props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtp.ssl.enable", "true");
		// props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.auth.login.disable", "true");

//		String to = "1466336983@qq.com";
		String subject = "subject 测试邮件";
		String content = "content  我是大能猫";
		// Properties properties = new Properties();
		//
		// Security.addProvider(new Provider());
		// props.setProperty("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		// props.setProperty("mail.smtp.port", "465");
		// props.setProperty("mail.smtp.socketFactory.port", "465");

		// properties.put("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		// properties.put("mail.smtp.port", "465");
		// properties.put("mail.smtp.socketFactory.port", "465");
		// properties.put("mail.smtp.host", "smtp.exmail.qq.com");
		// properties.put("mail.smtp.auth", "true");
		Authenticator authenticator = new Email_Authenticator("357307667@qq.com", "wczqvuolszdtcaie");
		javax.mail.Session sendMailSession = javax.mail.Session.getDefaultInstance(props, authenticator);
		sendMailSession.setDebug(true);
//		MimeMessage mailMessage = new MimeMessage(sendMailSession);
//		mailMessage.setFrom(new InternetAddress("357307667@qq.com"));
//		// Message.RecipientType.TO属性表示接收者的类型为TO
//		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
//		mailMessage.setSubject(subject, "UTF-8");
//		mailMessage.setSentDate(new Date());
//		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
//		Multipart mainPart = new MimeMultipart();
//		// 创建一个包含HTML内容的MimeBodyPart
//		BodyPart html = new MimeBodyPart();
//		html.setContent(content.trim(), "text/html; charset=utf-8");
//		mainPart.addBodyPart(html);
//		mailMessage.setContent(mainPart);

		JavaMailSenderImpl sendImpl = new JavaMailSenderImpl();
		sendImpl.setHost("184.107.164.138");
		sendImpl.setUsername("app");
		sendImpl.setPassword("YunEn");
		MimeMessage mailMessage = sendImpl.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
		messageHelper.setTo("1466336983@qq.com");
		messageHelper.setFrom("AtHomeApp@ichano.com");
		messageHelper.setSubject(subject);
		messageHelper.setText(content);
		sendImpl.send(mailMessage);
//		Transport.send(mailMessage);
	}

}
