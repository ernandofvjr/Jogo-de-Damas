package regras;

import componentes.Tabuleiro;

public abstract class Regra {
	private Tabuleiro tabuleiro;

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}	
}