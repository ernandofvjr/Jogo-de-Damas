package regras;

import Excecoes.DamasException;
import componentes.Tabuleiro;

public class CasaDesocupada extends Regra implements ChecarUm {
	
	public CasaDesocupada(Tabuleiro tabuleiro){
		setTabuleiro(tabuleiro);
	}
	/**
	 * checa se a casa está desocupada
	 */
	public boolean checar(int linhaDestino, int colunaDestino) throws DamasException{
		if(getTabuleiro().getTabuleiro().get(linhaDestino)[colunaDestino] == null){
			return true;
		}
		
		return false;
	}
}