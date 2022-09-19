package com.quickgrab.exception;





import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.quickgrab.dto.ApiResponse;

@RestControllerAdvice

public class GlobalExceptionHandler {

 @ExceptionHandler(ResourceNotFoundException.class)

 public ResponseEntity<ApiResponse> handleNotFoundException(ResourceNotFoundException ex)

 {

 String message = ex.getMessage();

 ApiResponse apiResponse = new ApiResponse(message, false);

 return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);

 }
 
 @ExceptionHandler(MethodArgumentNotValidException.class)
 public ResponseEntity<Map<String, String>> handleInvalidArguement(MethodArgumentNotValidException ex) {
	 Map<String, String> resp = new HashMap<>();
	 ex.getBindingResult().getFieldErrors().forEach(error -> {

		 String fieldName =((FieldError)error).getField();

			String message = error.getDefaultMessage();

			resp.put(fieldName,message);

	     
	    });
	 return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
 }
 
}