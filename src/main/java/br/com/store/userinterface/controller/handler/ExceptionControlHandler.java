package br.com.store.userinterface.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControlHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Throwable> handle(Throwable e) {
        return handle(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    private <T> ResponseEntity<T> handle(HttpStatus status, T body) {
        return ResponseEntity.status(status).body(body);
    }
}
