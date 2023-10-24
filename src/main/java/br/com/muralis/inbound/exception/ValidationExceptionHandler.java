package br.com.muralis.inbound.exception;

import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException e) {
        var field = e.getMessage().split(":")[0];
        var message = e.getMessage().split(":")[1];
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ExceptionMessageWithField.of(field, message)).build();
    }

}
