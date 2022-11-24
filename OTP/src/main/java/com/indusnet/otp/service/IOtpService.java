package com.indusnet.otp.service;

import com.indusnet.otp.model.UserModel;
/*
 * This Service is contains unimplemented methods 
 */
public interface IOtpService {
	

		public String generateOtp(UserModel user);
		public String validateOtp(String otp);
		public String resend();
		

	}



