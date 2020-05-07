package com.resliv.stitov.controller;

import com.resliv.stitov.exceptions.CustomValidation;
import com.resliv.stitov.exceptions.EntityAlreadyExists;
import com.resliv.stitov.exceptions.ErrorMessage;
import com.resliv.stitov.exceptions.NoSuchEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchEntityException(NoSuchEntityException e){
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExists.class)
    public ResponseEntity<ErrorMessage> handleEntityAlreadyExists(EntityAlreadyExists e){
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomValidation.class)
    public ResponseEntity<ErrorMessage> handleCustomValidationException(CustomValidation e){
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorMessage> handleNPException(NullPointerException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleOthersException(Exception e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
