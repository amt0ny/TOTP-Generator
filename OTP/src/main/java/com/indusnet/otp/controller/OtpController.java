package com.indusnet.otp.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.indusnet.otp.dto.OtpResponseMessage;
import com.indusnet.otp.model.UserModel;
import com.indusnet.otp.service.IOtpService;
import com.indusnet.otp.util.OtpUtil;

@RestController
public class OtpController {

	@Autowired
	private IOtpService iOtpService;

	@Autowired
	private OtpUtil otpUtil;
	
	OtpResponseMessage otpResponseMessage;

	private UserModel user2;

	public OtpController(IOtpService iOtpService) {
		super();
		this.iOtpService = iOtpService;
	}


	int count = 0;

	/*
	 * API responsible to generate and return OTP 
	 * This API also send success details with OTP in response
	 */
	@PostMapping("/getOtp")
	public ResponseEntity<OtpResponseMessage> getOtp(@RequestBody UserModel user) {
		user2 = user;
		String traceID = "" + Instant.now().toEpochMilli();
		count++;

		OtpResponseMessage otpResponseMessage1 = new OtpResponseMessage(HttpStatus.OK.value(), traceID,
				"OTP generated Successfully", count, iOtpService.generateOtp(user));

		return new ResponseEntity<>(otpResponseMessage1, HttpStatus.OK);

	}

	/*
	 * This API is responsible to valid OTP
	 */
	@PostMapping("/validOtp/{otp}")
	public ResponseEntity<OtpResponseMessage> validation(@PathVariable String otp) {
		String msg = iOtpService.validateOtp(otp);
		OtpResponseMessage otpResponseMessage2 = new OtpResponseMessage(HttpStatus.OK.value(),""+Instant.now().getEpochSecond(),
				msg, count, otp);

		return new ResponseEntity<> (otpResponseMessage2,HttpStatus.OK);
	}

	
	/*
	 * This API will be used to re-send OTP
	 */
	@GetMapping("/resendOtp")
	public ResponseEntity<?> resendOtp() {
		String traceID = "" + Instant.now().toEpochMilli();
		count++;
		String otps = iOtpService.resend();

		OtpResponseMessage otpResponseMessage3 = new OtpResponseMessage(HttpStatus.OK.value(), traceID,
				"OTP generated Successfully", count, otps);

		return new ResponseEntity<>(otpResponseMessage3, HttpStatus.OK);
	}

}
