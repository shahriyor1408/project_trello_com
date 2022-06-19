package uz.meta.service;

import lombok.val;
import uz.meta.config.Config;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MailService {

    public void sendMessage(String subject, String body, String receiver) {

        try {
            Properties prop = new Properties();
            prop.load(new FileReader("src/main/resources/application.properties"));
            val userName = Config.getenv("USER_NAME");
            val password = Config.getenv("PASSWORD");

            Session session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            message.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(body, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Message send");
        } catch (IOException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
