package com.decarli.solriso_system.model.exceptions.handler;

import com.decarli.solriso_system.model.exceptions.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorMessage> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, BindingResult result){
            ErrorMessage error = new ErrorMessage(request, HttpStatus.BAD_REQUEST, "The fields entered are invalid", result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(error);
        }

        @ExceptionHandler(EntityNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ResponseEntity<ErrorMessage> handlerNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
            ErrorMessage error = new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
}
