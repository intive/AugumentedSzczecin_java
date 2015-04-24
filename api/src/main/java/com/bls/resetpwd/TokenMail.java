package com.bls.resetpwd;

import com.bls.core.user.ResetPasswordToken;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class TokenMail {
    static final String from = "patronage2015resetpwd@gmail.com";
    static final String host = "localhost";

    static MimeMessage message;
    static final String subject = "Forgotten password reset";
    private final String text;

    static final Properties properties = System.getProperties();
    static final Session session = Session.getDefaultInstance(properties);


    public TokenMail(ResetPasswordToken token) {
        text = "Token: " + token.getToken();

        properties.setProperty("mail.smtp.host", host);
        message = new MimeMessage(session);

        try {
            message.setFrom(from);
            message.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void to(final String email) throws MessagingException {
        message.setRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));
        Transport.send(message);
    }
}