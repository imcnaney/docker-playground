package com.oi1p.errorhandler;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.jboss.logging.Logger;

@Provider
public class ErrorHandler implements ExceptionMapper<Exception> {

  private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class.getName());

  @Inject
  ObjectMapper objectMapper;

  @Override
  public Response toResponse(Exception exception) {
    LOGGER.error("Failed to handle request", exception);

    int code = 500;
    String error = "Server Error";
    if (exception instanceof WebApplicationException) {
      code = ((WebApplicationException) exception).getResponse().getStatus();
      if (exception.getMessage() != null) {
        error = exception.getMessage();
      }
    }

    ObjectNode exceptionJson = objectMapper.createObjectNode();
    exceptionJson.put("code", code);
    exceptionJson.put("error", error);

    return Response.status(code).entity(exceptionJson).build();
  }

}