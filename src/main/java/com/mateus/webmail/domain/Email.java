package com.mateus.webmail.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Email implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private Date dataCriacao;
	private Date dataAlteracao;

	@OneToMany(mappedBy = "email", cascade = CascadeType.ALL)
	private List<SubEmail> subEmailList = new ArrayList<>();

	@OneToMany(mappedBy = "id.email", cascade = CascadeType.ALL)
	private List<UsuarioEmail> usuarioEmails = new ArrayList<>();

	public Email() {
	}

	public Email(String titulo, Date dataCriacao, Date dataAlteracao) {
		super();
		this.titulo = titulo;
		this.dataCriacao = dataCriacao;
		this.dataAlteracao = dataAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public List<SubEmail> getSubEmailList() {
		return subEmailList;
	}

	public void setSubEmailList(List<SubEmail> subEmailList) {
		this.subEmailList = subEmailList;
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
		Email other = (Email) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
