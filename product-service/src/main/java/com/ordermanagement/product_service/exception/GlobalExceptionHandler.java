package com.ordermanagement.product_service.exception;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

	public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException ex,
			HttpServletRequest request){
		
		 String message = ex.getBindingResult()
	                .getFieldErrors()
	                .stream()
	                .map(err -> err.getField() + ": " + err.getDefaultMessage())
	                .collect(Collectors.joining(", "));
		 
		 ErrorResponse error = new ErrorResponse(
	                HttpStatus.BAD_REQUEST.value(),
	                message,
	                request.getRequestURI()
	        );
		
		 return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(
            ProductNotFoundException ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
	
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Something went wrong",
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	    @ExceptionHandler(InsufficientStockException.class)
	    public ResponseEntity<ErrorResponse> handleStockError(
	            InsufficientStockException ex,
	            HttpServletRequest request) {

	        ErrorResponse error = new ErrorResponse(
	                HttpStatus.BAD_REQUEST.value(),
	                ex.getMessage(),
	                request.getRequestURI()
	        );

	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }

}
