package com.cemp.bci.users.exception;

import com.cemp.bci.users.dto.UserErrorResponse;
import io.jsonwebtoken.security.WeakKeyException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(UserErrorResponse userErrorResponse, HttpStatus status) {
        return new ResponseEntity<>(userErrorResponse, status);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String msg = "Cuerpo para la solicitud es requerido.";
        UserErrorResponse userErrorResponse =
                new UserErrorResponse(HttpStatus.BAD_REQUEST.value(), msg);
        return buildResponseEntity(userErrorResponse, status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request) {
        return buildResponseEntity(new UserErrorResponse(HttpStatus.NOT_FOUND.value(),
                        ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityException(DataIntegrityViolationException ex) {
        return buildResponseEntity(new UserErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<Object> handleUserException(ApplicationException ex) {
        return buildResponseEntity(new UserErrorResponse(ex.getCodigo(), ex.getDetail()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        return buildResponseEntity(new UserErrorResponse(ex.getCodigo(), ex.getDetail()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    protected ResponseEntity<Object> handleConflicException(ConflictException ex) {
        return buildResponseEntity(new UserErrorResponse(ex.getCodigo(), ex.getDetail()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return buildResponseEntity(new UserErrorResponse(ex.getCodigo(), ex.getDetail()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WeakKeyException.class)
    protected ResponseEntity<Object> handleWeakKeyException(WeakKeyException ex) {
        return buildResponseEntity(new UserErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        return buildResponseEntity(new UserErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
