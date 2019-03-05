package movimentos;

import componentes.Peca;
import componentes.Tabuleiro;

public class ComerPeca {
	private Tabuleiro tabuleiro;
	public ComerPeca(Tabuleiro t){
		this.tabuleiro = t;
	}
	/**
	 * método que remove a peça da sua origem e captura outra peça colocando ela após a peça capturada e removendo a peça do array
	 * @param linhaOrigem
	 * @param colunaOrigem
	 * @param linhaDestino
	 * @param colunaDestino
	 */
	public void comer(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino){
		int linhaIntermediaria = -1;
		int colunaIntermediaria = -1;
		if(linhaDestino == linhaOrigem+2 || linhaDestino == linhaOrigem-2){
			if(linhaDestino > linhaOrigem)
				linhaIntermediaria = linhaOrigem+1;
			else
				linhaIntermediaria = linhaOrigem-1;
			if(colunaDestino == colunaOrigem+2 || colunaDestino == colunaOrigem-2){
				if(colunaDestino > colunaOrigem)
					colunaIntermediaria = colunaOrigem+1;
				else
					colunaIntermediaria = colunaOrigem-1;
			}
			Peca pecaMovida = tabuleiro.getTabuleiro().get(linhaOrigem)[colunaOrigem];
			tabuleiro.getTabuleiro().get(linhaOrigem)[colunaOrigem] = null;
			tabuleiro.getTabuleiro().get(linhaIntermediaria)[colunaIntermediaria] = null;
			tabuleiro.getTabuleiro().get(linhaDestino)[colunaDestino] = pecaMovida;
			
		}
	}
}
