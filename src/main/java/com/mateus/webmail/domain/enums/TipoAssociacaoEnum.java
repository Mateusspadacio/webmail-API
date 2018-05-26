package com.mateus.webmail.domain.enums;

public enum TipoAssociacaoEnum {

	BLOQUEADO(1), CONTATO(2);
	
	
	private Integer codigo;
	
	private TipoAssociacaoEnum(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public static TipoAssociacaoEnum toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for (TipoAssociacaoEnum e : TipoAssociacaoEnum.values()) {
			if (e.getCodigo() == codigo) {
				return e;
			}
		}
		
		throw new IllegalArgumentException("Codigo invalido " + codigo);
	}
	
}
