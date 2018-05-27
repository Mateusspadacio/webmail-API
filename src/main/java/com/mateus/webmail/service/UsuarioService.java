package com.mateus.webmail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mateus.webmail.domain.Usuario;
import com.mateus.webmail.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Page<Usuario> findAllByLike(Integer page, Integer size, String direction, String orderBy, String nome) {
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		return usuarioRepository.findByNomeLike("%"+ nome + "%", pageRequest);
	}
	
}
