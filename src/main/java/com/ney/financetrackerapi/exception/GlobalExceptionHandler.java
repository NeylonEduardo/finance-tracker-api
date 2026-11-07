package com.ney.financetrackerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomMessageError> handleValidationException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        CustomMessageError error = new CustomMessageError(
                exception.getStatusCode().value(),
                LocalDateTime.now(),
                message,
                "The submitted data is invalid."
        );

        return ResponseEntity
                .status(error.status())
                .body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomMessageError> handleValidationException2(
            HttpMessageNotReadableException exception,
            WebRequest request) {

        CustomMessageError error = new CustomMessageError(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity
                .status(error.status())
                .body(error);
    }

    @ExceptionHandler({
            AccountNotFoundException.class,
            CategoryNotFoundException.class,
            IncompatibleCategoryException.class
    })
    public ResponseEntity<CustomMessageError> customMessageErrors(
            RuntimeException exception,
            WebRequest request
    ) {
        CustomMessageError messageError = new CustomMessageError(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity
                .status(messageError.status())
                .body(messageError);
    }
}