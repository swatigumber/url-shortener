package com.klyo.url_shortener.exception;

import com.klyo.url_shortener.dto.ErrorResponseDto;
import jakarta.validation.ConstraintDefinitionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShortCodeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleShortURLNotFound(ShortCodeNotFoundException exception){
        return new ErrorResponseDto(HttpStatus.NOT_FOUND.value() , exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        String message =  exception.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return new ErrorResponseDto(HttpStatus.BAD_REQUEST.value() , message);
    }



}
