package com.mateus.webmail.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mateus.webmail.domain.Email;
import com.mateus.webmail.domain.EmailEnviado;
import com.mateus.webmail.domain.SubEmail;
import com.mateus.webmail.domain.Usuario;
import com.mateus.webmail.domain.UsuarioEmail;
import com.mateus.webmail.domain.enums.TipoEmailEnum;
import com.mateus.webmail.dto.EmailDTO;
import com.mateus.webmail.dto.NewEmailDTO;
import com.mateus.webmail.dto.SubEmailDTO;
import com.mateus.webmail.dto.UsuarioDTO;
import com.mateus.webmail.exceptions.AuthorizationException;
import com.mateus.webmail.repositories.EmailEnviadoRepository;
import com.mateus.webmail.repositories.EmailRepository;
import com.mateus.webmail.repositories.UsuarioEmailRepository;
import com.mateus.webmail.repositories.UsuarioRepository;
import com.mateus.webmail.security.UserSS;

@Service
public class EmailService {

	@Autowired
	private UsuarioEmailRepository usuarioEmailRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private EmailEnviadoRepository emailEnviadoRepository;

	public Page<UsuarioEmail> find(Integer page, Integer size, String direction, String field) {
		UserSS userSS = UserService.authenticated();

		if (userSS == null) {
			throw new AuthorizationException("Usuário não autorizado");
		}

		PageRequest pageable = PageRequest.of(page, size, Direction.valueOf(direction), field);
		Page<UsuarioEmail> usuarioEmailList = usuarioEmailRepository.findEmails(userSS.getId(), pageable);

		return usuarioEmailList;
	}

	public void save(NewEmailDTO newEmailDTO) {
		UserSS userSS = UserService.authenticated();

		if (userSS == null) {
			throw new AuthorizationException("Usuário não autorizado");
		}
		
		Optional<Usuario> usuario = usuarioRepository.findById(userSS.getId());
		Email email = new Email(newEmailDTO.getEmail().getTitulo(), newEmailDTO.getEmail().getDataCriacao(), 
				newEmailDTO.getEmail().getDataAlteracao());
		List<Usuario> usuarios = usuarioRepository.findByEmailIn(newEmailDTO.getEmailsDestinatarios());
		usuarios.add(usuario.get());
		
		SubEmailDTO subEmailDTO = newEmailDTO.getEmail().getSubEmail().get(0);
		email.getSubEmailList().add(new SubEmail(subEmailDTO.getTexto(), subEmailDTO.getDataEnvio(),
				email, usuario.get()));
		
		List<UsuarioEmail> usuariosEmail = usuarios.stream().map(u -> new UsuarioEmail(email, u, 
				TipoEmailEnum.CAIXA_ENTRADA, u.getId() != usuario.get().getId() ? false : true)).collect(Collectors.toList());
		email.getUsuarioEmails().addAll(usuariosEmail);
		
		emailRepository.save(email);
		saveEmailEnviado(email, usuario.get());
	}
	
	public void saveSubEmail(SubEmailDTO subEmailDTO) {
		UserSS userSS = UserService.authenticated();

		if (userSS == null) {
			throw new AuthorizationException("Usuário não autorizado");
		}
		
		Optional<Email> email = emailRepository.findById(subEmailDTO.getEmail().getId());
		Optional<Usuario> usuario = usuarioRepository.findById(userSS.getId());
		SubEmail newSubEmail = new SubEmail(subEmailDTO.getTexto(), subEmailDTO.getDataEnvio(), email.get(), usuario.get());
		email.get().getSubEmailList().add(newSubEmail);
		emailRepository.save(email.get());
	}
	
	private void saveEmailEnviado(Email email, Usuario usuario) {
		EmailEnviado emailEnviado = new EmailEnviado(email, usuario);
		emailEnviadoRepository.save(emailEnviado);
	}

	public List<SubEmailDTO> subEmailListToDTO(List<SubEmail> lista) {
		List<SubEmailDTO> listaDTO = lista.stream().map(
				se -> new SubEmailDTO(se.getId(), se.getTexto(), se.getDataEnvio(), new UsuarioDTO(se.getUsuario())))
				.collect(Collectors.toList());
		return listaDTO;
	}

	public Page<EmailDTO> usuarioEmailToPageEmailDTO(Page<UsuarioEmail> page) {
		Page<EmailDTO> emailsDTO = page.map(ue -> new EmailDTO(ue.getEmail().getId(), ue.getEmail().getTitulo(),
				ue.getEmail().getDataCriacao(), ue.getEmail().getDataAlteracao(), ue.getTipoEmail(), ue.isCriador(),
				subEmailListToDTO(ue.getEmail().getSubEmailList())));

		return emailsDTO;
	}
}
