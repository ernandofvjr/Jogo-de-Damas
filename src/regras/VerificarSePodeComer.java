package regras;

import Excecoes.DamasException;
import componentes.Peca;
import componentes.Tabuleiro;

public class VerificarSePodeComer extends Regra implements ChecarTres {
	
	public VerificarSePodeComer(Tabuleiro tabuleiro){
		setTabuleiro(tabuleiro);
	}
	/**
	 * verifica se a peça pode capturar
	 * @throws DamasException 
	 */
	public boolean checar(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) throws DamasException{
		
		CasaDesocupada regra = new CasaDesocupada(getTabuleiro());
		
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
			
			if(!(regra.checar(linhaIntermediaria, colunaIntermediaria))){
				if(regra.checar(linhaDestino, colunaDestino)){
					Peca pecaMovida = getTabuleiro().getTabuleiro().get(linhaOrigem)[colunaOrigem];
					Peca pecaComida = getTabuleiro().getTabuleiro().get(linhaIntermediaria)[colunaIntermediaria];
					if(!(pecaMovida.getCor().equals(pecaComida.getCor()))){
						return true;
					}
				}
			}			
		}
		return false;
	}
}