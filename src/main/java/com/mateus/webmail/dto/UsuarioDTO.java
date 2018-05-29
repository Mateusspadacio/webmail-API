package com.mateus.webmail.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mateus.webmail.domain.Usuario;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Email obrigatório")
	@Email
	private String email;
	@NotEmpty(message="Senha invalida")
	@Length(min=5, max=30)
	private String senha;
	@NotEmpty(message="Nome obrigatório")
	@Length(min=5)
	private String nome;
	@NotNull
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataNascimento;
	
	public UsuarioDTO() {
	}
	
	public UsuarioDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.email = usuario.getEmail();
		this.nome = usuario.getNome();
		this.dataNascimento = usuario.getDataNascimento();
	}
	
	public UsuarioDTO(Integer id, String email, String nome, Date dataNascimento) {
		super();
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
