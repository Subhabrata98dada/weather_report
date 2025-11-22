package com.qsp.serviceimplement;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.qsp.event.ClientSaveEvent;
import com.qsp.exceptionhandling.AddClientException;
import com.qsp.repository.ClientRepostiry;
import com.qsp.requestdto.ClientSaveDto;
import com.qsp.service.ClientService;
import com.qsp.service.MailOtpService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImplement implements ClientService {

	private final ClientRepostiry clientrepo;

	private final MailOtpService mailOtpService;

	@Qualifier("createclientdto")
	private final Map<String, ClientSaveDto> createclientdto;

	private final ApplicationEventPublisher publisher;

	@Override
	public String sendOtpRegister(ClientSaveDto dto) {
		dto.setEmail(dto.getEmail().trim());
		if (clientrepo.existsByEmail(dto.getEmail())) {
			throw new AddClientException("Emial already exist");
		}

		String emailId = dto.getEmail().trim();
		mailOtpService.sentOtp(emailId);
		createclientdto.put(emailId, dto);
		return "Otp sent to email " + emailId;
	}

	@Override
	public String validateOtpRegister(String email, String otp) {
		if (mailOtpService.validateOtp(email.trim(), otp)) {
			publisher.publishEvent(new ClientSaveEvent(createclientdto.get(email)));
			return "User saved";
		}
		return "Invalid otp";
	}

}
