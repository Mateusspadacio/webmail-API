package com.mateus.webmail.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mateus.webmail.domain.SubEmail;
import com.mateus.webmail.domain.enums.TipoEmailEnum;

public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Titulo obrigatório")
	private String titulo;
	@NotNull
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dataCriacao;
	@NotNull
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dataAlteracao;
	private Integer tipoEmail;
	private boolean isCriador;
	private List<SubEmailDTO> subEmail = new ArrayList<>();
	
	public EmailDTO() {
	}

	public EmailDTO(Integer id, String titulo, Date dataCriacao, Date dataAlteracao, TipoEmailEnum tipoEmail, boolean isCriador, List<SubEmailDTO> subEmail) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.dataCriacao = dataCriacao;
		this.dataAlteracao = dataAlteracao;
		this.subEmail = subEmail;
		this.tipoEmail = tipoEmail.getCodigo();
		this.isCriador = isCriador;
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

	public TipoEmailEnum getTipoEmail() {
		return TipoEmailEnum.toEnum(tipoEmail);
	}

	public void setTipoEmail(TipoEmailEnum tipoEmail) {
		this.tipoEmail = tipoEmail.getCodigo();
	}

	public boolean isCriador() {
		return isCriador;
	}

	public void setCriador(boolean isCriador) {
		this.isCriador = isCriador;
	}

	public List<SubEmailDTO> getSubEmail() {
		return subEmail;
	}

	public void setSubEmail(List<SubEmailDTO> subEmailList) {
		this.subEmail = subEmailList;
	}

}
