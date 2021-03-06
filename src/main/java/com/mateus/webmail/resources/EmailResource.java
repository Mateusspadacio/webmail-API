package com.mateus.webmail.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mateus.webmail.domain.UsuarioEmail;
import com.mateus.webmail.dto.EmailDTO;
import com.mateus.webmail.dto.NewEmailDTO;
import com.mateus.webmail.dto.SubEmailDTO;
import com.mateus.webmail.service.EmailService;

@RestController
@RequestMapping(value="/emails")
public class EmailResource {
	
	@Autowired
	private EmailService emailService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<EmailDTO>> find(
			@RequestParam(name="page", defaultValue="0") Integer page,
			@RequestParam(name="size", defaultValue="24") Integer size,
			@RequestParam(name="direction", defaultValue="DESC") String direction,
			@RequestParam(name="field", defaultValue="id.email.dataCriacao") String field) {
		Page<UsuarioEmail> emails = emailService.find(page, size, direction, field);
		Page<EmailDTO> emailsDTO = emailService.usuarioEmailToPageEmailDTO(emails);
		return ResponseEntity.ok().body(emailsDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> send(@Valid @RequestBody NewEmailDTO newEmailDTO) {
		emailService.save(newEmailDTO);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/subemail", method=RequestMethod.POST)
	public ResponseEntity<Void> sendSubEmail(@RequestBody SubEmailDTO subEmailDTO) {
		emailService.saveSubEmail(subEmailDTO);
		return ResponseEntity.ok().build();
	}
}
