package com.mateus.webmail.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.mateus.webmail.domain.enums.TipoEmailEnum;

@Entity
public class UsuarioEmail implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuarioEmailPK id = new UsuarioEmailPK();

	private Integer tipoEmail;

	private boolean isCriador;

	public UsuarioEmail() {
	}

	public UsuarioEmail(Email email, Usuario usuario, TipoEmailEnum tipoEmail, boolean isCriador) {
		super();
		id.setEmail(email);
		id.setUsuario(usuario);
		this.tipoEmail = tipoEmail.getCodigo();
		this.isCriador = isCriador;
	}

	public UsuarioEmailPK getId() {
		return id;
	}

	public void setId(UsuarioEmailPK id) {
		this.id = id;
	}

	public TipoEmailEnum getTipoEmail() {
		return TipoEmailEnum.toEnum(tipoEmail);
	}

	public void setTipoEmail(Integer tipoEmail) {
		this.tipoEmail = tipoEmail;
	}

	public boolean isCriador() {
		return isCriador;
	}

	public void setCriador(boolean isCriador) {
		this.isCriador = isCriador;
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
		UsuarioEmail other = (UsuarioEmail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
