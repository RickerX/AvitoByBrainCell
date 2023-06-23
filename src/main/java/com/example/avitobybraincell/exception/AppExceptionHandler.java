package com.example.avitobybraincell.exception;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(AdvertNotFoundException.class)
    public ResponseEntity<Object> handlerAdvertNotFoundException(RuntimeException e, WebRequest request) {
        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<Object> handlerCommentNotFoundException(RuntimeException e, WebRequest request) {
        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserUnauthorizedException.class)
    public ResponseEntity<Object> handlerUserUnauthorizedException(RuntimeException e, WebRequest request) {
        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
}
