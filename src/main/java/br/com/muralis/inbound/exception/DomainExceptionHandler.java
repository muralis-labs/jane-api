package br.com.muralis.inbound.exception;

import br.com.muralis.core.domain.exception.DomainException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DomainExceptionHandler implements ExceptionMapper<DomainException> {
    @Override
    public Response toResponse(DomainException e) {
        return Response.status(e.getStatusCode()).entity(ExceptionMessage.of(e.getMessage())).build();
    }
}
