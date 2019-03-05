package regras;

import Excecoes.DamasException;
import componentes.Tabuleiro;

public class SeEstaMovendoUmaPeca extends Regra implements ChecarDois{
	
	public SeEstaMovendoUmaPeca(Tabuleiro tabuleiro){
		setTabuleiro(tabuleiro);
	}
	/**
	 * verifica se está movendo uma peça
	 * @throws DamasException 
	 */
	
	public boolean checar(int linhaOrigem, int colunaOrigem, String cor) throws DamasException{		
		if(getTabuleiro().getTabuleiro().get(linhaOrigem)[colunaOrigem] != null){
			if(getTabuleiro().getTabuleiro().get(linhaOrigem)[colunaOrigem].getCor().equals(cor)){
				return true;
			}
			else{
				throw new DamasException("A peca pertence ao adversario");
			}			
		}
		return false;
	}
}