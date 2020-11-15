package com.fonseca.breweryapi.controller.advice;

import com.fonseca.breweryapi.client.brewerydb.exception.BreweryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(BreweryNotFoundException.class)
    public void breweryNotFound() {
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<String> handleMethodArgumentNotValidException(BindException ex, WebRequest request) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::buildErrorMessage)
                .collect(Collectors.toList());
    }

    private String buildErrorMessage(FieldError fieldError) {
        return fieldError.getField() + " : " + fieldError.getDefaultMessage() + " : rejected value [" + fieldError.getRejectedValue() + "]";
    }
}
