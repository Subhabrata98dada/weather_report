package com.qsp.service;

import com.qsp.requestdto.ClientSaveDto;

public interface MailOtpService {
	public boolean sentOtp(String emailid,ClientSaveDto dto);
	public boolean validateOtp(String emailid,String otp);
}
