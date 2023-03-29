package ru.tinkoff.edu.java.scrapper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.tinkoff.edu.java.scrapper.dto.response.ApiErrorResponse;
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
                HttpStatus.NOT_FOUND.toString(), ex.toString(), ex.getMessage(), Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).toList());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(Exception ex) {
        logger.error("IllegalArgumentException", ex);
        ApiErrorResponse e = new ApiErrorResponse("Некорректные параметры запроса",
                HttpStatus.BAD_REQUEST.toString(), ex.toString(), ex.getMessage(), Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }
}
