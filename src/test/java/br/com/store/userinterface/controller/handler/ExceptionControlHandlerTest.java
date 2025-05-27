package br.com.store.userinterface.controller.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("ExceptionControlHandler")
public class ExceptionControlHandlerTest extends Assertions {
    private final ExceptionControlHandler handler = new ExceptionControlHandler();

    @Test
    @DisplayName("ExceptionControlHandler.handle - Throwable")
    public void throwable() {
        var exception = new Exception();
        var result = handler.handle(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals(exception, result.getBody());
    }
}
