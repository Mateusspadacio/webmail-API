package com.mateus.webmail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mateus.webmail.domain.UsuarioEmail;

@Repository
public interface UsuarioEmailRepository extends JpaRepository<UsuarioEmail, Integer>{

}
