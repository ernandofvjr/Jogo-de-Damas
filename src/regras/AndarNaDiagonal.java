package regras;

import Excecoes.DamasException;
import componentes.Tabuleiro;

public class AndarNaDiagonal extends Regra implements ChecarTres {
	
	public AndarNaDiagonal(Tabuleiro tabuleiro){
		setTabuleiro(tabuleiro);
	}
	/**
	 * checa se a peça está andando na diagonal
	 */
	public boolean checar(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) throws DamasException{
		
		int direcaoLinha = linhaDestino - linhaOrigem;
		int direcaoColuna = colunaDestino - colunaOrigem;
		
		
		if(direcaoLinha == 0 || direcaoColuna == 0){
			throw new DamasException("Casas subsequentes nao podem ser ocupadas");			
		}
		return true;
	}
}