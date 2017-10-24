package com.mycompany.usc.test.controller;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author pbharat
 */
@ControllerAdvice
public class ErrorController {
    private static final Logger log = LoggerFactory.getLogger(ErrorController.class);
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleAllException(Exception ex) {
        log.error("Something went wrong", ex);
        return new ResponseEntity(new HashMap<String, String>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
