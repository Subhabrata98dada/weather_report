package com.qsp.listiner;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.qsp.entity.Audit;
import com.qsp.entity.Client;
import com.qsp.event.ClientSaveEvent;
import com.qsp.modelmapper.ClientMapper;
import com.qsp.repository.AuditRepository;
import com.qsp.repository.ClientRepostiry;
import com.qsp.requestdto.ClientSaveDto;

@Component
public class AddClientListener {

	@Autowired
	private JavaMailSender mailsender;

	@Autowired
	@Qualifier("otpholder")
	private Map<String, Object[]> otpholder;

	@Autowired
	private AuditRepository auditrepo;

	@Autowired
	private ClientRepostiry clientrepo;

	@Autowired
	private ClientMapper clientMapper;

	@EventListener
	@Async
	public void notifyClient(ClientSaveEvent event) {
		SimpleMailMessage message = new SimpleMailMessage();
		String name = event.getDto().getName();
		message.setFrom("testsubhabrata736@gmail.com");
		message.setTo(event.getDto().getEmail());
		message.setSubject("Weather app alert");
		StringBuilder builder = new StringBuilder();
		builder.append("Dear " + name + "\n Thanks for your subscription to " + "Jhatka_weather service");
		message.setText(builder.toString());
		mailsender.send(message);
	}

	@EventListener
	@Async
	public void addClient(ClientSaveEvent event) {
		ClientSaveDto dto = event.getDto();
		Client client = clientMapper.clientSaveDtoToClient(dto, new Client());
		clientrepo.save(client);
	}

	@EventListener
	@Async
	public void auditAddClient(ClientSaveEvent event) {
		Audit audit = Audit.builder().auditid(UUID.randomUUID().toString()).user(event.getDto().getEmail())
				.action("REGISTER").message("New user subscribed").build();
		auditrepo.save(audit);
	}

}
