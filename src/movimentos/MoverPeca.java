package movimentos;

import componentes.Peca;
import componentes.Tabuleiro;

public class MoverPeca {
	private Tabuleiro tabuleiro;
	
	public MoverPeca(Tabuleiro t){
		this.tabuleiro = t;
	}
	/**
	 * método que retira a peça da sua origem e a coloca no destino
	 * @param linhaOrigem
	 * @param colunaOrigem
	 * @param linhaDestino
	 * @param colunaDestino
	 */
	public void mover(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino){
		Peca peca = tabuleiro.getTabuleiro().get(linhaOrigem)[colunaOrigem];
		tabuleiro.getTabuleiro().get(linhaOrigem)[colunaOrigem] = null;
		tabuleiro.getTabuleiro().get(linhaDestino)[colunaDestino] = peca;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}	
}