package com.mateus.webmail.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.mateus.webmail.domain.enums.TipoAssociacaoEnum;

@Entity
public class Associado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@OneToOne
	@JoinColumn(name="usuario_associado_id")
	private Usuario usuarioAssociado;
	
	private Integer tipoAssociacao;
	
	
	public Associado() {
	}


	public Associado(Usuario usuario, Usuario usuarioAssociado, TipoAssociacaoEnum tipoAssociacao) {
		super();
		this.usuario = usuario;
		this.usuarioAssociado = usuarioAssociado;
		this.tipoAssociacao = tipoAssociacao.getCodigo();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Usuario getUsuarioAssociado() {
		return usuarioAssociado;
	}


	public void setUsuarioAssociado(Usuario usuarioAssociado) {
		this.usuarioAssociado = usuarioAssociado;
	}


	public TipoAssociacaoEnum getTipoAssociacao() {
		return TipoAssociacaoEnum.toEnum(tipoAssociacao);
	}


	public void setTipoAssociacao(TipoAssociacaoEnum tipoAssociacao) {
		this.tipoAssociacao = tipoAssociacao.getCodigo();
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
		Associado other = (Associado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
