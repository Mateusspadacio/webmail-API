package com.mateus.webmail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mateus.webmail.domain.EmailEnviado;

@Repository
public interface EmailEnviadoRepository extends JpaRepository<EmailEnviado, Integer> {
}
