package com.qsp.schedule;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RefreshOtpHolderSchedule {

	@Qualifier("otpholder")
	private final Map<String, Object[]> otpholder;

	@Scheduled(cron = "0 0/3 * * * *")
	public void refreshOtpHolder() {
		LocalDateTime now = LocalDateTime.now();
		otpholder.keySet().removeIf(key -> {
			LocalDateTime expiry = (LocalDateTime) otpholder.get(key)[1];
			return now.isAfter(expiry);
		});
	}
}
