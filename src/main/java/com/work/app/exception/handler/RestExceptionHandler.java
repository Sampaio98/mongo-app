package com.work.app.exception.handler;

import com.work.app.dto.DefaultErrorDTO;
import com.work.app.dto.RequiredFieldErrorDTO;
import com.work.app.exception.IntegrityViolationException;
import com.work.app.exception.LocaleIntegrationException;
import com.work.app.exception.ObjectNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<?> handleObjectNotFoundException(ObjectNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(new DefaultErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> messages = e.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return ResponseEntity.status(BAD_REQUEST).body(new RequiredFieldErrorDTO(messages));
    }

    @ExceptionHandler(LocaleIntegrationException.class)
    public ResponseEntity<?> handleLocaleIntegrationException(LocaleIntegrationException e) {
        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(new DefaultErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(IntegrityViolationException.class)
    public ResponseEntity<?> handleIntegrityViolationException(IntegrityViolationException e) {
        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(new DefaultErrorDTO(e.getMessage()));
    }

}
