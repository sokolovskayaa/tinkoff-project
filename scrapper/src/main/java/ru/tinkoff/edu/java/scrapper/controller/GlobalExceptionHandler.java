package ru.tinkoff.edu.java.scrapper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.tinkoff.edu.java.scrapper.dto.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.exception.ChatNotFoundException;

import java.util.Arrays;

@RestControllerAdvice(
        basePackageClasses = ScrapperController.class,
        basePackages = "ru.tinkoff.edu.java.scrapper.controller"
)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ChatNotFoundException.class})
    protected ResponseEntity<ApiErrorResponse> handleChatNotFoundException(ChatNotFoundException ex) {
        logger.error("ChatNotFoundException", ex);
        ApiErrorResponse e = new ApiErrorResponse("Чат не существует",
                "404", ex.toString(), ex.getMessage(), Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).toList());
        return ResponseEntity.status(404).body(e);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(Exception ex) {
        logger.error("IllegalArgumentException", ex);
        ApiErrorResponse e = new ApiErrorResponse("Некорректные параметры запроса",
                "400", ex.toString(), ex.getMessage(), Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).toList());
        return ResponseEntity.status(400).body(e);
    }
}
