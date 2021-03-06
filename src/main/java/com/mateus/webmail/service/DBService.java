package com.mateus.webmail.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mateus.webmail.domain.Associado;
import com.mateus.webmail.domain.Email;
import com.mateus.webmail.domain.EmailEnviado;
import com.mateus.webmail.domain.Grupo;
import com.mateus.webmail.domain.Participante;
import com.mateus.webmail.domain.SubEmail;
import com.mateus.webmail.domain.Usuario;
import com.mateus.webmail.domain.UsuarioEmail;
import com.mateus.webmail.domain.enums.TipoAssociacaoEnum;
import com.mateus.webmail.domain.enums.TipoEmailEnum;
import com.mateus.webmail.repositories.AssociadoRepository;
import com.mateus.webmail.repositories.EmailRepository;
import com.mateus.webmail.repositories.GrupoRepository;
import com.mateus.webmail.repositories.UsuarioEmailRepository;
import com.mateus.webmail.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private UsuarioEmailRepository usuarioEmailRepository;

	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	private AssociadoRepository associadoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	public void initDataBase() throws ParseException {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Usuario user1 = new Usuario("mateus@gmail.com", "Mateus", pe.encode("123"), sdf.parse("24/10/1996"));
		Usuario user2 = new Usuario("joao@gmail.com", "João", pe.encode("123"), sdf.parse("10/08/2000"));
		Usuario user3 = new Usuario("maria@gmail.com", "Maria", pe.encode("123"), sdf.parse("15/05/2005"));
		
		Grupo grupo1 = new Grupo("Faculdade", user3);
		Grupo grupo2 = new Grupo("Amigos", user1);

		Participante part1 = new Participante(user1, grupo1);
		Participante part2 = new Participante(user2, grupo1);
		Participante part3 = new Participante(user2, grupo2);

		grupo1.getParticipantes().addAll(Arrays.asList(part1, part2));
		grupo2.getParticipantes().addAll(Arrays.asList(part3));

		user3.getGrupos().add(grupo1);
		user1.getGrupos().add(grupo2);

		usuarioRepository.saveAll(Arrays.asList(user1, user2, user3));
		grupoRepository.saveAll(Arrays.asList(grupo1, grupo2));

		Email email1 = new Email("Trabalho APS", sdf.parse("20/05/2018"), null);
		Email email2 = new Email("Trabalho Amaury", sdf.parse("15/05/2018"), null);
		Email email3 = new Email("Teste", sdf.parse("05/04/2017"), null);
		
		email1 = emailRepository.save(email1);
		email2 = emailRepository.save(email2);
		email3 = emailRepository.save(email3);

		EmailEnviado emailEnviado1 = new EmailEnviado(email1, user2);
		EmailEnviado emailEnviado2 = new EmailEnviado(email2, user2);
		EmailEnviado emailEnviado3 = new EmailEnviado(email3, user2);
		user2.getEmailsEnviados().addAll(Arrays.asList(emailEnviado1, emailEnviado2, emailEnviado3));

		SubEmail subEmail1 = new SubEmail("Já fiz minha parte", new Date(System.currentTimeMillis()), email1, user2);
		SubEmail subEmail2 = new SubEmail("Já enviei minha parte pra Erica", new Date(System.currentTimeMillis()),
				email1, user3);

		email1.getSubEmailList().addAll(Arrays.asList(subEmail1, subEmail2));

		UsuarioEmail userEmail1 = new UsuarioEmail(email1, user1, TipoEmailEnum.CAIXA_ENTRADA, false);
		UsuarioEmail userEmail2 = new UsuarioEmail(email1, user2, TipoEmailEnum.CAIXA_ENTRADA, true);
		UsuarioEmail userEmail3 = new UsuarioEmail(email1, user3, TipoEmailEnum.LIXEIRA, false);
		UsuarioEmail userEmail4 = new UsuarioEmail(email2, user2, TipoEmailEnum.LIXEIRA, false);
		UsuarioEmail userEmail5 = new UsuarioEmail(email3, user2, TipoEmailEnum.LIXEIRA, false);
		email1.getUsuarioEmails().addAll(Arrays.asList(userEmail1, userEmail2, userEmail3));
		user1.getUsuarioEmails().add(userEmail1);
		user2.getUsuarioEmails().add(userEmail2);
		user3.getUsuarioEmails().add(userEmail3);
		
		emailRepository.saveAll(Arrays.asList(email1, email2, email3));
		usuarioEmailRepository.saveAll(Arrays.asList(userEmail1, userEmail2, userEmail3, userEmail4, userEmail5));
		usuarioRepository.saveAll(Arrays.asList(user2));

		Associado associado1 = new Associado(user1, user2, TipoAssociacaoEnum.CONTATO);
		user1.getAssociados().add(associado1);

		associadoRepository.saveAll(Arrays.asList(associado1));
	}

}
