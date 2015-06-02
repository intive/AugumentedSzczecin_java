package com.bls.service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.bls.core.resetpwd.ResetPasswordToken;
import com.bls.core.user.User;

public class TokenMail implements TokenSendService {
    static final String username = "patronage2015resetpwd@gmail.com";
    static final String password = "9AAe3UDJdLntnK6A";

    Message message;

    public TokenMail(ResetPasswordToken token) throws UnsupportedEncodingException, MessagingException {

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


            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setSubject("Augmented password change");
            message.setText(token.getToken());

    }

    public void sendTo(final User user) throws MessagingException {
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(user.getEmail()));
        Transport.send(message);
        System.out.println("Sent e-mail to: " + user.getEmail());
    }
}
