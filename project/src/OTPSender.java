import java.util.Random;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class OTPSender {
    public static final String ACCOUNT_SID = "AC4adda3199f8f33b6b2b75c11a3aaf26c";
    public static final String AUTH_TOKEN = "cf1bfd87f7b91eb9c547de37c7bb9995";
    public static final String TWILIO_PHONE_NUMBER = "+12705801545";

    public String sendOTP(String phone) {
        // Initialize Twilio client with Account SID and Auth Token
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Generate OTP
        String otp = generateOTP();

        // Set up message
        String messageBody = "Your OTP is: " + otp;
        Message.creator(
                        new PhoneNumber("+2" + phone),
                        new PhoneNumber(TWILIO_PHONE_NUMBER),
                        messageBody)
                .create();
        return otp;
    }

    private static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return Integer.toString(otp);
    }
}