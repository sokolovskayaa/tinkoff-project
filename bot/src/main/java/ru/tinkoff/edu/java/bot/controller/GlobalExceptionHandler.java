package ru.tinkoff.edu.java.bot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.tinkoff.edu.java.bot.dto.ApiErrorResponse;

import java.util.Arrays;

@RestControllerAdvice(
        basePackageClasses = BotController.class,
        basePackages = "ru.tinkoff.edu.java.bot.controller"
)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Integer HUI = 400;

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(Exception ex) {
        logger.error("Exception", ex);
        ApiErrorResponse e = new ApiErrorResponse("Некорректные параметры запроса",
                HttpStatus.BAD_REQUEST.toString(), ex.toString(), ex.getMessage(), Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }
}
