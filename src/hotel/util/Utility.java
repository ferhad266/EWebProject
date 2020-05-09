package hotel.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Utility {
    public static boolean sendMail(String to, String subject, String text) {
        boolean result = false;
        final String username = "ferhad.musayev.00@gmail.com";
        final String password = "heyder1973";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ferhad.musayev.00@gmail.com"));
            message.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
//            message.setRecipient(Message.RecipientType.CC,
//                    InternetAddress.parse(""));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            result = true;
            System.out.println("Mail has been sended!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
