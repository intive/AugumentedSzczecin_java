package com.bls.service;

import javax.mail.MessagingException;

import com.bls.core.user.User;

public interface TokenSendService {
    void sendTo(User user) throws MessagingException;
}
