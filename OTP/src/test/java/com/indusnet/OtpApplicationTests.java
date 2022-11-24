package com.indusnet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.indusnet.otp.controller.OtpController;
import com.indusnet.otp.dto.OtpResponseMessage;
import com.indusnet.otp.model.UserModel;
import com.indusnet.otp.service.IOtpService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OtpApplicationTests {

	@InjectMocks
	OtpController otpController;

	
	@Autowired
	private IOtpService userService;



	/**
	* This method will get call first then other method will get call.
	*/
	@BeforeEach
	void setUp(){
	this.otpController = new OtpController(userService);

	}


	/**
	* This Test case for generate OTP
	* if TOTP generated successfully then test case will be passed.
	*/

	@Test
	@Order(1)
	void otpGenerateTest() {
	MockHttpServletRequest request = new MockHttpServletRequest();
	        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	        UserModel otpModel = new UserModel("9793485434", "Pritam", "mygmail@gmail.com");

	        ResponseEntity<?> responseEntity = otpController.getOtp(otpModel);
	        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	      }

	/**
	* This Test case is to check "Validation" Api
	* If validation is successful then the test case will be passed.
	*/

	@Test
	@Order(2)
	void otpValidateTest() {
	MockHttpServletRequest request = new MockHttpServletRequest();
	        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	        UserModel otpModel = new UserModel("9793485434", "Pritam", "mygmail@gmail.com");
	        String otp = userService.generateOtp(otpModel);
	        ResponseEntity<OtpResponseMessage>  responseEntity = otpController.getOtp(otpModel);
	        ResponseEntity<OtpResponseMessage> responseEntity2 = otpController.validation(responseEntity.getBody().getMyOtp());
	               
	        assertThat(responseEntity2.getStatusCodeValue()).isEqualTo(200
	        		);
	        assertThat(responseEntity2.getStatusCode()).isEqualTo(HttpStatus.OK);
	      }

	/**
	* This Test case is to test the re-send API
	* If OTP re-sent successfully and not exceeded number of attempt(3)
	* then the test case will be passed.
	*/

	@Test
	@Order(3)
	void otpResendTest() {
	MockHttpServletRequest request = new MockHttpServletRequest();
	        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	        otpValidateTest();
	        ResponseEntity<OtpResponseMessage> responseEntity2 = (ResponseEntity<OtpResponseMessage>) otpController.resendOtp();
	               
	        assertThat(responseEntity2.getStatusCodeValue()).isEqualTo(200);
	        assertThat(responseEntity2.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	void otpLimitTest() {
//		otpGenerateTest();
		int count = 0;
		for (int i = 0; i < 5; i++) {
			count++;
			otpResendTest();
		}
		
		assertEquals(4, count);
	}

	}




