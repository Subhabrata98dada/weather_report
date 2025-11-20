package com.qsp.listiner;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.qsp.event.ClientSaveEvent;

@Component
public class AddClientListener {
	
	@EventListener
	@Async
	public void addClient(ClientSaveEvent event) {
		System.out.println("Client saved "+event.getEmailid());
		System.out.println(Thread.currentThread().getName());
	}
	
	@EventListener
	@Async
	public void notifyClient(ClientSaveEvent event) {
		System.out.println("Nofication sent to client "+event.getEmailid());
		System.out.println(Thread.currentThread().getName());
	}
	
	@EventListener
	@Async
	public void auditAddClient(ClientSaveEvent event) {
		System.out.println("Audit table updated "+event.getEmailid());
		System.out.println(Thread.currentThread().getName());
	}
}
