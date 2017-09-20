package send.email;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class JavaMailSendUtil {

	public void sendMail(String subject, String from, String[] to, String test,
			String[] filenames, String mineType) {
		try {
			Properties props = new Properties();
			String smtp = "smtp.163.com";// 设置发送邮件所用到的smtp
			String servername = "wang90yong@163.com";
			String serverpassword = "wy19890611";
			Session mailSession = null;
			MimeMessage mimeMsg = null;
			props = java.lang.System.getProperties();
			props.put("mail.smtp.host", smtp);// 设置SMTP主机
			props.put("mail.smtp.auth", "true");// 是否到服务器用户名和密码验证
			// 到服务器验证发送的用户名和密码是否正确
			SmtpAuthenticator myEmailAuther = new SmtpAuthenticator(servername,
					serverpassword);
			// 设置邮件会话 注意这里讲认证信息放进了Session的创建参数里
			mailSession = javax.mail.Session.getInstance(props,
					(Authenticator) myEmailAuther);
			// 设置传输协议
			Transport transport = mailSession.getTransport("smtp");
			// 设置from、to等信息
			mimeMsg = new MimeMessage(mailSession);
			if (from != null && !from.equals("")) {
				InternetAddress sendFrom = new InternetAddress(from);
				mimeMsg.setFrom(sendFrom);// 设置发送人地址
			}
			InternetAddress[] sendTo = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				System.out.println("发送到：" + to[i]);
				sendTo[i] = new InternetAddress(to[i]);
			}
			// 设置抄送，可以多人抄送
			mimeMsg.setRecipient(MimeMessage.RecipientType.CC,
					new InternetAddress("2323895133@qq.com"));
			// 设置发送，可以多人发送
			mimeMsg.setRecipients(MimeMessage.RecipientType.TO, sendTo);
			//设置主题
			mimeMsg.setSubject(subject, "gb2312");
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(test, mineType);

			// 附件传输格式
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
			for (int i = 0; i < filenames.length; i++) {
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				String filename = filenames[i].split(";")[0];
				System.out.println(filename);
				// String displatname=filenames[i].split(";")[1];
				// 得到数据源
				FileDataSource fds = new FileDataSource(filename);
				// BodyPart添加附件本身
				messageBodyPart.setDataHandler(new DataHandler(fds));
				// BodyPart添加附件文件名
				messageBodyPart.setFileName(MimeUtility.encodeText(filename));
				multipart.addBodyPart(messageBodyPart);
			}
			mimeMsg.setContent(multipart);
			// 设置信件头的发送日期
			mimeMsg.setSentDate(new Date());
			// 保存邮件
			mimeMsg.saveChanges();
			// 发送邮件
			Transport.send(mimeMsg);
			transport.close();
			System.out.println("消息发送成功");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		PropertyConfigurator
				.configure("D:\\code\\wang\\myUtil\\src\\resource\\log4j.properties ");
		Logger logger = Logger.getLogger(JavaMailSendUtil.class);
		logger.debug(" debug ");
		logger.error(" error ");

		String title = "测试邮件";
		String from = "wang90yong@163.com";
		String sendTo[] = { "3109572272@qq.com", "t-yong.wang@pcitc.com" };
		String content = "王勇 just test java send mail!!!!<br><a>My blog</a>";
		String fileNames[] = { "C:\\Users\\Administrator\\Desktop\\sdk-pub.png" };
		JavaMailSendUtil test = new JavaMailSendUtil();
		try {
			test.sendMail(title, from, sendTo, content, fileNames,
					"text/html;charset=gb2312");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}