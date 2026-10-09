package com.ticketapp.userservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.HandlerExceptionResolver;

@RestControllerAdvice //controller kısmında oluşucak bütün exceptionları merkezi olarak yönetir demek
public class GlobalExceptionHandler  {
    private final HandlerExceptionResolver handlerExceptionResolver;

    public GlobalExceptionHandler(HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleEmailExists(EmailAlreadyExistsException exception){

        ApiErrorResponse response= new ApiErrorResponse(HttpStatus.CONFLICT.value(), exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);





    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException exception){

        String message = exception
                .getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        ApiErrorResponse response= new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidCredentialException(InvalidCredentialsException exception){

        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }







}
