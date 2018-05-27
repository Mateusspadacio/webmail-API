package com.mateus.webmail.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mateus.webmail.domain.UsuarioEmail;

@Repository
public interface UsuarioEmailRepository extends JpaRepository<UsuarioEmail, Integer>{

	@Query("SELECT ue FROM UsuarioEmail ue WHERE ue.id.usuario.id = :id")
	public Page<UsuarioEmail> findEmails(@Param("id") Integer id, Pageable pageable);
	
}
