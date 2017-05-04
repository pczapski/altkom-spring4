package pl.altkom.shop;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
// @Profile(Profiles.PROD)
public class MailSenderService {

	@Value("${mail.port}")
	private int port;
	@Value("${mail.host}")
	private String host;
	@Value("${mail.userName}")
	private String userName;
	@Value("${mail.password}")
	private String password;

	private JavaMailSenderImpl mailSender;

	@PostConstruct
	private void init() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("altkomspring");
		mailSender.setPassword("altkom123");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		this.mailSender = mailSender;
	}

	public void send(String to, String title, String message) {
		try {
			MimeMessage mailMessage = mailSender.createMimeMessage();
			MimeMessageHelper mail = new MimeMessageHelper(mailMessage, true);

			mail.setFrom("altkomspring@gmail.com", "Spring app");
			mail.setTo(to);
			mail.setSubject(title);
			mail.setText(message, true);
			// FileSystemResource file = new FileSystemResource("C:\\miro.txt");
			// message.addAttachment(file.getFilename(), file);

			mailSender.send(mailMessage);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws Exception {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("altkomspring");
		mailSender.setPassword("altkom123");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		mailSender.setJavaMailProperties(props);

	}
}
