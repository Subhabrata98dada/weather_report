package com.qsp.service;

import com.qsp.requestdto.ClientSaveDto;

public interface ClientService {
	public String sendOtpRegister(ClientSaveDto dto);
	public String validateOtpRegister(String email,String otp);
}
