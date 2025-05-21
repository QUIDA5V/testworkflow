package com.vip.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApplicationErrorHandler {
    private static final String MESSAGES = "fields";

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(HttpServletRequest request, Exception ex) {
        log.error(String.format("handleException - Not controlled exception: {}", ex.getMessage()), ex);
        JSONObject response = new JSONObject();
        JSONArray messages = new JSONArray();
        messages.put("Temporary password is not valid");
        response.put(MESSAGES, messages);
        log.error("Session: {}, Request: {}, user_uuid: {}, Message: {}",request.getSession().getId(), request.getAttribute("requestId"), request.getAttribute("user_uuid"), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
