package com.gouijane.facebook.publishagent.utils.contants;

import com.gouijane.facebook.publishagent.exception.FileNotPresentOrEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionManagement {

    @ExceptionHandler(FileNotPresentOrEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleFileNotPresentOrEmptyException(FileNotPresentOrEmptyException exception){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}
