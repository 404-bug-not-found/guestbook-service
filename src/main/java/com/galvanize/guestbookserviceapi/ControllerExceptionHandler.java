package com.galvanize.guestbookserviceapi;

import com.galvanize.guestbookserviceapi.exception.CommentExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(CommentExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handleCommentExistException() {
    }
}
