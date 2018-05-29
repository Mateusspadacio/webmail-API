package com.mateus.webmail.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NewEmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EmailDTO email;
	private List<String> emailsDestinatarios = new ArrayList<>();
	
	public NewEmailDTO() {
	}

	public NewEmailDTO(EmailDTO email, List<String> emailsDestinatarios) {
		super();
		this.email = email;
		this.emailsDestinatarios = emailsDestinatarios;
	}

	public EmailDTO getEmail() {
		return email;
	}

	public void setEmail(EmailDTO email) {
		this.email = email;
	}

	public List<String> getEmailsDestinatarios() {
		return emailsDestinatarios;
	}

	public void setEmailsDestinatarios(List<String> emailsDestinatarios) {
		this.emailsDestinatarios = emailsDestinatarios;
	}
	
}
