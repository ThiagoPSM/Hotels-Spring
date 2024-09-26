package com.thiago.hotel_service.exception;

import com.thiago.hotel_service.exception.dto.errorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class restResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(hotelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<errorDTO> hotelNotFoundException(hotelNotFoundException exc){
        errorDTO message = errorDTO.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exc.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(resourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<errorDTO> resourceNotFoundException(resourceNotFoundException exc){
        errorDTO message = errorDTO.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exc.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(requestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<errorDTO> requestException(requestException exc){
        errorDTO message = errorDTO.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(exc.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(e -> {
            String fieldName = ((FieldError)e).getField();
            String message = e.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
