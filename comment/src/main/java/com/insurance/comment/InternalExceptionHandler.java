package com.insurance.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
@Log4j2
public class InternalExceptionHandler {
    private final ObjectMapper objectMapper;

    public InternalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler({ServletRequestBindingException.class, ResourceAccessException.class})
    public ResponseEntity<RestResponse> handleServletRequestBindingException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.badRequest().body(new RestResponse("Error", ex.getMessage()));
    }

    @ExceptionHandler({HttpClientErrorException.class, HttpServerErrorException.class})
    public ResponseEntity handleServletRequestBindingException(HttpStatusCodeException ex, HttpServletRequest request, HttpServletResponse response) {
        try {
            ObjectNode objectNode = objectMapper.readValue(ex.getResponseBodyAsString(), ObjectNode.class);
            return ResponseEntity.badRequest().body(objectNode);
        } catch (IOException e) {
            log.error("Internal Error Occurred", e);
            return ResponseEntity.badRequest().body(new RestResponse("Error", ex.getResponseBodyAsString()));
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response) {
        ObjectNode errorsNode = objectMapper.createObjectNode();

        ex.getBindingResult().getFieldErrors().
                forEach(objectError -> errorsNode.put(objectError.getField(), objectError.getDefaultMessage()));
        ObjectNode configErrors = objectMapper.createObjectNode();
        configErrors.put("code", "INPUT_ERROR");
        configErrors.putPOJO("inputErrors", errorsNode);
        return ResponseEntity.badRequest().body(configErrors);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<RestResponse> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request, HttpServletResponse response) {
        log.error("exception in service response", ex);
        return ResponseEntity.status(ex.getStatus()).body(new RestResponse(ex.getStatus().toString(), ex.getReason()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResponse> handle(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        ex.printStackTrace();
        log.error("internal error occurred. message: " + ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RestResponse("Error", RestApiMessage.ERROR_500_OCCURRED.getValue()));
    }
}