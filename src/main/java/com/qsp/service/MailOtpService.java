package com.qsp.service;

public interface MailOtpService {
	public String sentOtp(String emailid);

	public Boolean validateOtp(String emailid, String otp);
}
