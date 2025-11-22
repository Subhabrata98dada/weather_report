package com.qsp.serviceimplement;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.qsp.exceptionhandling.EmailOtpException;
import com.qsp.service.MailOtpService;

@Service
public class MailOtpServiceImpliment implements MailOtpService {

	@Autowired
	@Qualifier("otpholder")
	private Map<String, Object[]> otpholder;

	@Autowired
	private Random random;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public String sentOtp(String emailid) {
		Integer otp = random.nextInt(100000, 999999);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("testsubhabrata736@gmail.com");
		message.setTo(emailid);
		message.setSubject("OTP alert from weather app");
		message.setText("OTP for gmail verification : " + otp);
		mailSender.send(message);
		otpholder.put(emailid, new Object[] { otp + "", LocalDateTime.now().plusMinutes(3) });
		return otp + "";
	}

	@Override
	public Boolean validateOtp(String emailid, String otp) {
		Object[] value = otpholder.get(emailid);
		if (value == null)
			throw new EmailOtpException("Reregister again");
		else if (LocalDateTime.now().isAfter((LocalDateTime) value[1])) {
			otpholder.remove(emailid);
			throw new EmailOtpException("Otp expired");
		}
		String inmemoryotp = (String) value[0];
		if (otp.trim().equals(inmemoryotp)) {
			otpholder.remove(emailid);
			return true;
		} else {
			throw new EmailOtpException("Invalid otp");
		}
	}

}
