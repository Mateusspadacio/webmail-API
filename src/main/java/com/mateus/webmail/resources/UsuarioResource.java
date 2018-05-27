package com.mateus.webmail.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mateus.webmail.domain.Usuario;
import com.mateus.webmail.dto.UsuarioDTO;
import com.mateus.webmail.service.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<UsuarioDTO>> findAllByLike(
			@RequestParam(name="page", defaultValue="0") Integer page,
			@RequestParam(name="direction", defaultValue="ASC") String direction,
			@RequestParam(name="size", defaultValue="24") Integer size,
			@RequestParam(name="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(name="nome", defaultValue="") String nome) {
		Page<Usuario> usuarios = usuarioService.findAllByLike(page, size, direction, orderBy, nome);
		Page<UsuarioDTO> usuariosDTO = usuarios.map(e -> new UsuarioDTO(e.getId(), e.getEmail(), e.getNome(), e.getDataNascimento()));
		return ResponseEntity.ok().body(usuariosDTO);
	}
}
