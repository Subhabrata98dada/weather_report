package com.qsp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.qsp.util.ClientType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NonNull
	private String name;
	@NonNull
	private String phoneNumber;
	@NonNull
	@Column(unique = true)
	private String email;
	@NonNull
	@Enumerated(EnumType.STRING)
	private ClientType usertype;
	@NonNull
	private Boolean active;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdon;
	
	@LastModifiedDate
	private LocalDateTime lastupdate;
}
