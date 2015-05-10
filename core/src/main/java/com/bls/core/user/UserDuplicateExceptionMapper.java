package com.bls.core.user;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserDuplicateExceptionMapper implements ExceptionMapper<UserDuplicateException> {

    @Override
    public Response toResponse(UserDuplicateException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity("E-mail already used.").type("text/plain").build();
    }
}
