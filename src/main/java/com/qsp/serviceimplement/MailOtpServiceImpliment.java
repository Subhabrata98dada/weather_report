package com.qsp.serviceimplement;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.qsp.event.ClientSaveEvent;
import com.qsp.exceptionhandling.AddClientException;
import com.qsp.requestdto.ClientSaveDto;
import com.qsp.service.MailOtpService;



@Service
public class MailOtpServiceImpliment implements MailOtpService{
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	@Qualifier("otpholder")
	private Map<String,Object[]> otpholder;
	
	@Autowired
	private Random random;
	
	@Autowired
    private JavaMailSender mailSender;

	@Override
	public String sentOtp(String emailid,ClientSaveDto dto) {
		
		try {
			Integer otp=random.nextInt(100000,999999);
			Object value[]= {dto,otp+"",LocalDateTime.now().plusMinutes(3)};
			otpholder.put(emailid,value);
			SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom("testsubhabrata736@gmail.com");
	        message.setTo(emailid);
	        message.setSubject("OTP");
	        message.setText("OTP for gmail verification : "+otp);
	        mailSender.send(message);
			return "Otp sent to email :"+emailid;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AddClientException("Invalid email id :"+emailid);
		}
	}

	@Override
	public String validateOtp(String emailid, String otp) {
		Object[] value=otpholder.get(emailid);
		if(value==null) 
			throw new AddClientException("Reregister again");
		else if(LocalDateTime.now().isAfter((LocalDateTime)value[2]))
			throw new AddClientException("Otp expired");
		String inmemoryotp=(String)value[1];
		if(otp.trim().equals(inmemoryotp)) {
			publisher.publishEvent(new ClientSaveEvent(emailid));
			return "Client saved";
		}
		else {
			throw new AddClientException("Invalid otp");
		}
	}

}
