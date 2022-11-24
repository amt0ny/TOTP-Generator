package com.indusnet.otp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
/*
 * This class is for data that we need for registration first than to generate OTP
 */
public class UserModel {

	/*
	 * phone number will be used to generate and validate OTP
	 */
	private String phoneNumber;
	private String userName;
	private String emailID;
	
}
