package com.bls.resetpwd;

import com.bls.core.user.ResetPasswordToken;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class TokenMail {
    static final String username = "patronage2015resetpwd@gmail.com";
    static final String password = "9AAe3UDJdLntnK6A";

    Message message;

    public TokenMail(ResetPasswordToken token) throws UnsupportedEncodingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setSubject("Augmented password change");
            message.setText(token.getToken());

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void to(final String email) throws MessagingException {
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
        Transport.send(message);
    }
}