package com.indusnet.otp.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indusnet.otp.exception.OtpException;
import com.indusnet.otp.model.UserModel;
import com.indusnet.otp.util.OtpUtil;
@Service
public class OtpService implements IOtpService{

	
	@Autowired
	private OtpUtil otpUtil;
	
	int resendCount = 0;
	String mainOtp = null;
	UserModel userModel = null;
	/*
	 * Initializing OtpUtil
	 */
	public OtpService(OtpUtil otpUtil2) {
		this.otpUtil = otpUtil2;
	}

	/**
	 * This method is to generate OTP with the help of generateTOTP256
	 */
	@Override
	public String generateOtp(UserModel user) {
		userModel = user;
		String key = ""+LocalDateTime.now().getNano()+user.getPhoneNumber();
		String newOtp = otpUtil.generateTOTP256(key, "120", "6");
		mainOtp = newOtp;
		return newOtp;
	}

	/**
	 * This method is to validate OTP
	 */
	@Override
	public String validateOtp(String otp) {
		
		if (mainOtp.equals(otp)) {
			return "Otp is valid";
		}
		else {
			 throw new OtpException("Otp is not valid");
		}
	}

	/**
	 * This method is responsible to re-send the OTP
	 * also it will prevent to generate OTP more than 3 times.
	 */
	@Override
	public String resend() throws OtpException{
		resendCount++;
		if (resendCount > 3) {
			throw new OtpException("Limit exceeded");
			
		}
		return generateOtp(userModel);
		
	}
	
	


	

}
