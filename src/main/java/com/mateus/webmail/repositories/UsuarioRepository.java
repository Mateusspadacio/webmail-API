package com.mateus.webmail.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mateus.webmail.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Transactional(readOnly = true)
	public Page<Usuario> findByNomeLike(String nome, Pageable pageRequest);
	
	@Transactional(readOnly = true)
	public Usuario findByEmail(String email);
	
	@Transactional(readOnly = true)
	public List<Usuario> findByEmailIn(List<String> emails);
}
