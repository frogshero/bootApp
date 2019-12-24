package com.example.logDemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult> handleException(HttpServletRequest request,
                                                           HttpStatus status, String
                                                                       errorMessage, Throwable ex) {
        ApiResult res = new ApiResult();
        res.setCode(ex.getMessage());
        res.setMessage(errorMessage);
        return ResponseEntity.status(400).body(res);
    }

}
