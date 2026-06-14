package com.workflow.api.exception;

import com.workflow.api.dto.common.ExceptionResponse;
import com.workflow.api.exception.auth.EmailAlreadyExistsException;
import com.workflow.api.exception.auth.InvalidCredentialsException;
import com.workflow.api.exception.auth.UnauthorizedException;
import com.workflow.api.exception.common.TagAlreadyExistsException;
import com.workflow.api.exception.common.TagNotFoundException;
import com.workflow.api.exception.common.TaskNotFoundException;
import com.workflow.api.exception.common.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handlerEmailAlreadyExists(
            EmailAlreadyExistsException ex
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ExceptionResponse(
                        HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT.name(),
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionResponse> handlerUnauthorized(
            UnauthorizedException ex
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse(
                        HttpStatus.UNAUTHORIZED.value(),
                        HttpStatus.UNAUTHORIZED.name(),
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handlerInvalidCredentials(
            InvalidCredentialsException ex
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse(
                        HttpStatus.UNAUTHORIZED.value(),
                        HttpStatus.UNAUTHORIZED.name(),
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerUsernameNotFound(
            UserNotFoundException ex
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.name(),
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public  ResponseEntity<ExceptionResponse> handlerTaskNotFound(
            TaskNotFoundException ex
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.name(),
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(TagNotFoundException.class)
    public  ResponseEntity<ExceptionResponse> handlerTagNotFound(
            TagNotFoundException ex
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.name(),
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(TagAlreadyExistsException.class)
    public  ResponseEntity<ExceptionResponse> handlerTagNotFound(
            TagAlreadyExistsException ex
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ExceptionResponse(
                        HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT.name(),
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerNotFound(
            NoHandlerFoundException ex
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.name(),
                        "Rota não encontrada"
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handlerValidation(
            MethodArgumentNotValidException ex
    ) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Dados inválidos");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.name(),
                        message
                ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleJsonError(
            HttpMessageNotReadableException ex
    ) {
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(
                        400,
                        "BAD_REQUEST",
                        "Valor inválido para um dos campos"
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneric(
            Exception ex
    ) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        HttpStatus.INTERNAL_SERVER_ERROR.name(),
                        "Erro interno do servidor"
                ));
    }
}
