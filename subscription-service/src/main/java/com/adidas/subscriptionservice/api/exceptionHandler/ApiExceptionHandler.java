package com.adidas.subscriptionservice.api.exceptionHandler;

import com.adidas.subscriptionservice.domain.exception.DomainException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 17/09/21 00:09
 */

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var fields = new ArrayList<Problem.Field>();
        var problem = new Problem();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            var name = ((FieldError) error).getField();
            var message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            fields.add(new Problem.Field(name, message));
        }

        problem.setStatus(status.value());
        problem.setDateTime(LocalDateTime.now());
        problem.setFields(fields);
        problem.setTitle("One or more fields are not correctly.");


        return handleExceptionInternal(ex, problem, headers, status, request);
    }


    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomain(DomainException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var problem = new Problem();

        problem.setStatus(status.value());
        problem.setDateTime(LocalDateTime.now());
        problem.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }
}
