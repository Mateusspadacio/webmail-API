package com.mateus.webmail.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mateus.webmail.domain.Usuario;

public class SubEmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String texto;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dataEnvio;
	private UsuarioDTO usuario;
	
	public SubEmailDTO() {
	}
	
	public SubEmailDTO(Integer id, String texto, Date dataEnvio, UsuarioDTO usuario) {
		super();
		this.id = id;
		this.texto = texto;
		this.dataEnvio = dataEnvio;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
	
}
