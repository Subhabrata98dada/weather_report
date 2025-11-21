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
import com.qsp.event.ClientSaveEvent;
import com.qsp.repository.AuditRepository;
import com.qsp.requestdto.ClientSaveDto;

@Component
public class AddClientListener {
	
	@Autowired
	private JavaMailSender mailsender;
	
	@Autowired
	@Qualifier("otpholder")
	private Map<String,Object[]> otpholder;
	
	@Autowired
	private AuditRepository auditrepo;
	
	@EventListener
	@Async
	public void notifyClient(ClientSaveEvent event) {
		SimpleMailMessage message = new SimpleMailMessage();
		Object object[]=otpholder.get(event.getEmailid());
		String name=((ClientSaveDto)object[0]).getName();
        message.setFrom("testsubhabrata736@gmail.com");
        message.setTo(event.getEmailid());
        message.setSubject("Weather app alert");
        StringBuilder builder=new StringBuilder();
        builder.append("Dear "+ name+"\n Thanks for your subscription to "
        		+ "Jhatka_weather service");
        message.setText(builder.toString());
        mailsender.send(message);
	}
	
	@EventListener
	@Async
	public void addClient(ClientSaveEvent event) {
		System.out.println("Nofication sent to client "+event.getEmailid());
		System.out.println(Thread.currentThread().getName());
	}
	
	@EventListener
	@Async
	public void auditAddClient(ClientSaveEvent event) {
		 Audit audit= Audit.builder()
		.auditid(UUID.randomUUID().toString())
		.user(event.getEmailid())
		.action("REGISTER")
		.message("New user subscribed")
		.build();
//		 auditrepo.save(audit);
	}
	
}
