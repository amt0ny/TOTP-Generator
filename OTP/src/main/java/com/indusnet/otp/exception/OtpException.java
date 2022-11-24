package com.indusnet.otp.exception;

import lombok.NoArgsConstructor;
/*
 * This class was created to throw exception with Error response message
 */
@NoArgsConstructor
public class OtpException extends RuntimeException {

	public OtpException (String message) {
		super(message);
		
	}
	
}
