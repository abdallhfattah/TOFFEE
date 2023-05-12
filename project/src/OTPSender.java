import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class OTPSender {
    private String from = "omar1752003@gmail.com";

    public static String generateOTP() {
        // Generate a random 6-digit number
        int otp = (int) (Math.random() * 900000) + 100000;

        // Convert the number to a string
        // Return the string
        return Integer.toString(otp);
    }

    public void sendEmail(String to, String subject, String text) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        String username = "omar1752003@gmail.com";
        String password = "yatlowkdcejgoqsb";

        //session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}