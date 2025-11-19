package com.qsp.serviceimplement;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qsp.requestdto.ClientSaveDto;
import com.qsp.service.MailOtpService;



@Service
public class MailOtpServiceImpliment implements MailOtpService{
	
	@Autowired
	@Qualifier("otpholder")
	private Map<String,Object[]> otpholder;
	
	@Autowired
	private Random random;

	@Override
	public boolean sentOtp(String emailid,ClientSaveDto dto) {
		
		try {
			Integer otp=random.nextInt(100000,999999);
			Object value[]= {dto,otp+"",LocalDateTime.now().plusMinutes(3)};
			otpholder.put(emailid,value);
			System.out.println(otp);
			return true;
		} catch (Exception e) {
			throw new RuntimeException("Invalid email id :"+emailid);
		}
	}

	@Override
	public boolean validateOtp(String emailid, String otp) {
		Object[] value=otpholder.get(emailid);
		if(value==null) 
			throw new RuntimeException("Reregister again");
		else if(LocalDateTime.now().isAfter((LocalDateTime)value[2]))
			throw new RuntimeException("Otp expired");
		String inmemoryotp=(String)value[1];
		return otp.trim().equals(inmemoryotp);
	}

}
