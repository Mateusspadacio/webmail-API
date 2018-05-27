package com.mateus.webmail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mateus.webmail.domain.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {
	
}
