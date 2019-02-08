package universeKsisSMTP_POP3;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestMail {
    static final String ENCODING = "UTF-8";

    public static void main(String args[]) throws MessagingException, UnsupportedEncodingException {
	String subject = "vlad privet";
	String content = "kak dela?";
	String smtpHost = "smtp.gmail.com";
	String address = "*****"; 
	String login = "*****";
	String password = "******";
	String smtpPort = "465";
	sendSimpleMessage(login, password, address, address, content, subject, smtpPort, smtpHost);
    }

    public static void sendSimpleMessage(String login, String password, String from, String to, String content,
	    String subject, String smtpPort, String smtpHost) throws MessagingException, UnsupportedEncodingException {
	Authenticator auth = new MyAuthenticator(login, password);

	Properties props = System.getProperties();
	props.put("mail.smtp.user", login);
	props.put("mail.smtp.host", smtpHost);
	props.put("mail.smtp.port", smtpPort);
	// props.put("mail.smtp.starttls.enable", "true");
	// props.put("mail.smtp.debug", "true");
	props.put("mail.smtp.auth", "true");
	// props.put("mail.smtp.socketFactory.port", smtpPort);
	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	// props.put("mail.smtp.socketFactory.fallback", "false");
	Session session = Session.getDefaultInstance(props, auth);

	Message msg = new MimeMessage(session);
	msg.setFrom(new InternetAddress(from));
	msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	msg.setSubject(subject);
	msg.setText(content);
	Transport.send(msg);
    }
}

class MyAuthenticator extends Authenticator {
    private String user;
    private String password;

    MyAuthenticator(String user, String password) {
	this.user = user;
	this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
	String user = this.user;
	String password = this.password;
	return new PasswordAuthentication(user, password);
    }
}