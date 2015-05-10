package com.bls.core.user;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class UserDuplicateException extends WebApplicationException {

    public UserDuplicateException() {
        super(Response.status(Response.Status.BAD_REQUEST).build());
    }

    public UserDuplicateException(User user) {
        super(Response
                .status(Response.Status.BAD_REQUEST)
                .entity("E-mail " + user.getEmail() + " already used.")
                .build());
    }
}
