package com.mateus.webmail.domain.enums;

public enum TipoEmailEnum {
	
	CAIXA_ENTRADA(1), LIXEIRA(2), IMPORTANTE(3);
	
	private Integer codigo;
	
	private TipoEmailEnum(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public static TipoEmailEnum toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for (TipoEmailEnum e : TipoEmailEnum.values()) {
			if (e.getCodigo() == codigo) {
				return e;
			}
		}
		
		throw new IllegalArgumentException("Codigo invalido " + codigo);
	}

}
