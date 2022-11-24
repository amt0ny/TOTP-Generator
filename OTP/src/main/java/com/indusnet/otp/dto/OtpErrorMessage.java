package com.indusnet.otp.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
/*
 * This class was made to return Error message when we are not able to generate OTP
 */
public class OtpErrorMessage {
	
	    private LocalDateTime timestamp;
	    
	    private Integer statusCode;
	    private int errorCode;
	    
	    private String message;
	    private String error;
	    private Long traceID;
	    
	    private String trace;
	    private String path;

	
}
