package com.indusnet.otp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/*
 * This class holds Success Response message when an OTP is generated Successfully
 */
public class OtpResponseMessage {
	
	 	 
		 private Integer StatusCode;
		 private String TraceID;
		 private String Message;
		 private int MessageTypeID;
		 private String myOtp;
		 

}
