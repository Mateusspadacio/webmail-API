package com.mateus.webmail.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String email;
	private String nome;
	private String senha;
	private Date dataNascimento;

	@OneToMany(mappedBy = "usuario")
	private List<Grupo> grupos = new ArrayList<>();

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Associado> associados = new ArrayList<>();

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<EmailEnviado> emailsEnviados = new ArrayList<>();

	@OneToMany(mappedBy = "id.usuario", cascade = CascadeType.ALL)
	private List<UsuarioEmail> usuarioEmails = new ArrayList<>();

	public Usuario() {
	}

	public Usuario(String email, String nome, String senha, Date dataNascimento) {
		super();
		this.email = email;
		this.nome = nome;
		this.senha = senha;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Associado> getAssociados() {
		return associados;
	}

	public void setAssociados(List<Associado> associados) {
		this.associados = associados;
	}

	public List<EmailEnviado> getEmailsEnviados() {
		return emailsEnviados;
	}

	public void setEmailsEnviados(List<EmailEnviado> emailsEnviados) {
		this.emailsEnviados = emailsEnviados;
	}

	public List<UsuarioEmail> getUsuarioEmails() {
		return usuarioEmails;
	}

	public void setUsuarioEmails(List<UsuarioEmail> usuarioEmails) {
		this.usuarioEmails = usuarioEmails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
