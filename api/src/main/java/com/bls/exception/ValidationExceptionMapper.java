package com.bls.exception;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    private static final int UNPROCESSABLE_ENTITY = 422;

    @Override
    public Response toResponse(ValidationException exception) {
        return Response.status(UNPROCESSABLE_ENTITY).entity(exception.getMessage()).build();
    }
}
