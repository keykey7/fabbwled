package ch.bbw.fabbwled.lands.exception;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class DefaultErrorHandler {

    @ExceptionHandler
    @SneakyThrows
    public void defaultInterceptor(Exception e) {
        log.warn("exception at application boundary", e);
        throw e;
    }
}
