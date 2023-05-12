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
        String otpStr = Integer.toString(otp);

        // Return the string
        return otpStr;
    }

    public boolean sendEmail(String to, String subject, String text) {
        boolean flag = false;

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
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return flag;
    }

}











//import java.util.Random;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//public class OTPSender {
//    public static final String ACCOUNT_SID = "AC4adda3199f8f33b6b2b75c11a3aaf26c";
//    public static final String AUTH_TOKEN = "cf1bfd87f7b91eb9c547de37c7bb9995";
//    public static final String TWILIO_PHONE_NUMBER = "+12705801545";
//
//    public String sendOTP(String phone) {
//        // Initialize Twilio client with Account SID and Auth Token
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        // Generate OTP
//        String otp = generateOTP();
//
//        // Set up message
//        String messageBody = "Your OTP is: " + otp;
//        Message.creator(
//                        new PhoneNumber("+2" + phone),
//                        new PhoneNumber(TWILIO_PHONE_NUMBER),
//                        messageBody)
//                .create();
//        return otp;
//    }
//
//    private static String generateOTP() {
//        Random random = new Random();
//        int otp = 100000 + random.nextInt(900000);
//        return Integer.toString(otp);
//    }
//}