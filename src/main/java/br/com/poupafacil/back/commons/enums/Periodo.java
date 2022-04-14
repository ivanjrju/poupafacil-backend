package br.com.poupafacil.back.commons.enums;

public enum Periodo {

	MENSAL(1),
	TRIMESTRAL(3),
	SEMESTRAL(6),
	ANUAL(12);
	
	private int tempo;
	
	Periodo(int tempo) {
		this.tempo = tempo;
	}
	
	public int getTempo() {
		return tempo;
	}	
}
