package com.indusnet.otp.exception;

import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.indusnet.otp.dto.OtpErrorMessage;

@ControllerAdvice
public class GlobalException {
	
	int errorCode = 0;
	
	@ExceptionHandler(OtpException.class)
	public ResponseEntity<OtpErrorMessage> exceptionHandle(OtpException otpException, WebRequest webRequest){
		errorCode++;
		
		System.out.println("A global Exception called");
		OtpErrorMessage otpErrorMessage = new OtpErrorMessage(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), errorCode, 
				otpException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), Instant.now().toEpochMilli(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
				webRequest.getDescription(false));
		
		return new ResponseEntity<>(otpErrorMessage,HttpStatus.BAD_REQUEST);
	}
}
