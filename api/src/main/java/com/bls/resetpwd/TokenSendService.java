package com.bls.resetpwd;

import com.bls.core.user.User;

import javax.mail.MessagingException;

public interface TokenSendService {
    void sendTo(User user) throws MessagingException;
}
