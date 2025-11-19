package com.qsp.service;

public interface EmailOtpService {
	String otpGenerate();
	boolean otpValidate(String email,String otp);
}
