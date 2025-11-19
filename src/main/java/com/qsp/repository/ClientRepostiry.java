package com.qsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qsp.entity.Client;

@Repository
public interface ClientRepostiry extends JpaRepository<Client,Integer>{
	boolean existsByEmail(String email);
}
