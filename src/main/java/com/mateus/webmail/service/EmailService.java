package com.mateus.webmail.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mateus.webmail.domain.Email;
import com.mateus.webmail.domain.SubEmail;
import com.mateus.webmail.domain.UsuarioEmail;
import com.mateus.webmail.dto.EmailDTO;
import com.mateus.webmail.dto.SubEmailDTO;
import com.mateus.webmail.dto.UsuarioDTO;
import com.mateus.webmail.exceptions.AuthorizationException;
import com.mateus.webmail.repositories.UsuarioEmailRepository;
import com.mateus.webmail.security.UserSS;

@Service
public class EmailService {

	@Autowired
	private UsuarioEmailRepository usuarioEmailRepository;
	
	public Page<UsuarioEmail> find(Integer page, Integer size, String direction, String field) {
		UserSS userSS = UserService.authenticated();
		
		if (userSS == null) {
			throw new AuthorizationException("Usuário não autorizado");
		}

		PageRequest pageable = PageRequest.of(page, size, Direction.valueOf(direction), field);
		Page<UsuarioEmail> usuarioEmailList = usuarioEmailRepository.findEmails(userSS.getId(), pageable);
		
		return usuarioEmailList;
	}
	
	public List<SubEmailDTO> subEmailListToDTO(List<SubEmail> lista) {
		List<SubEmailDTO> listaDTO = lista.stream().map(se -> new SubEmailDTO(se.getId(), se.getTexto(), se.getDataEnvio(), new UsuarioDTO(se.getUsuario())))
				.collect(Collectors.toList());
		return listaDTO;
	}
	
	public Page<EmailDTO> usuarioEmailToPageEmailDTO(Page<UsuarioEmail> page) {
		Page<EmailDTO> emailsDTO = page.map(ue -> new EmailDTO(ue.getEmail().getId(), ue.getEmail().getTitulo(), ue.getEmail().getDataCriacao(), ue.getEmail().getDataAlteracao(), 
					ue.getTipoEmail(), ue.isCriador(), subEmailListToDTO(ue.getEmail().getSubEmailList())));
		
		return emailsDTO;
	}
}
