package regras;

import java.util.ArrayList;

import Excecoes.DamasException;
import componentes.Peca;
import componentes.Tabuleiro;

public class VerificarSopro extends Regra implements ChecarDois {
	
	public VerificarSopro(Tabuleiro tabuleiro){
		setTabuleiro(tabuleiro);
	}
	/**
	 * verifica coordenadas do sopro
	 * @param linhaDestino
	 * @param colunaDestino
	 * @param cor
	 * @return vetor com sopros
	 * @throws DamasException 
	 */
	public ArrayList<Integer> checarCoordenadas(int linhaDestino, int colunaDestino, String cor) throws DamasException{
		VerificarSePodeComer podeComer = new VerificarSePodeComer(getTabuleiro());
		VerificarLimite checarLimite = new VerificarLimite(getTabuleiro());	
		ArrayList<Integer> coordenadaDestino = new ArrayList<Integer>();
		
		//superior esquerda
		if(checarLimite.checar(linhaDestino-2, colunaDestino-2)){
			if(podeComer.checar(linhaDestino, colunaDestino, linhaDestino-2, colunaDestino-2)){	
				coordenadaDestino.add(linhaDestino-2);
				coordenadaDestino.add(colunaDestino-2);
			}
		}		
		//superior direita
		if(checarLimite.checar(linhaDestino-2, colunaDestino+2)){			
			if(podeComer.checar(linhaDestino, colunaDestino, linhaDestino-2, colunaDestino+2)){				
				coordenadaDestino.add(linhaDestino-2);
				coordenadaDestino.add(colunaDestino+2);
			}
		}		
		//inferior esquerda
		if(checarLimite.checar(linhaDestino+2, colunaDestino-2)){
			if(podeComer.checar(linhaDestino, colunaDestino, linhaDestino+2, colunaDestino-2)){				
				coordenadaDestino.add(linhaDestino+2);
				coordenadaDestino.add(colunaDestino-2);
			}
		}		
		//inferior direita
		if(checarLimite.checar(linhaDestino+2, colunaDestino+2)){
			if(podeComer.checar(linhaDestino, colunaDestino, linhaDestino+2, colunaDestino+2)){				
				coordenadaDestino.add(linhaDestino+2);
				coordenadaDestino.add(colunaDestino+2);
			}
		}	
		return coordenadaDestino;
	}
	
	public boolean checar(int linhaDestino, int colunaDestino, String cor) throws DamasException{
		VerificarSePodeComer podeComer = new VerificarSePodeComer(getTabuleiro());
		VerificarLimite checarLimite = new VerificarLimite(getTabuleiro());	
		Peca peca = getTabuleiro().getTabuleiro().get(linhaDestino)[colunaDestino];
		
		if(peca.isDama() == true || peca.getCor().equals("branca")){			
			//superior esquerda
			if(checarLimite.checar(linhaDestino-2, colunaDestino-2)){
				if(podeComer.checar(linhaDestino, colunaDestino, linhaDestino-2, colunaDestino-2)){
					return true;
				}
			}
			//superior direita
			if(checarLimite.checar(linhaDestino-2, colunaDestino+2)){
				if(podeComer.checar(linhaDestino, colunaDestino, linhaDestino-2, colunaDestino+2)){				
					return true;
				}	
			}
		}		
		
		
		if(peca.isDama() == true || peca.getCor().equals("escura")){
			//inferior esquerda
			if(checarLimite.checar(linhaDestino+2, colunaDestino-2)){
				if(podeComer.checar(linhaDestino, colunaDestino, linhaDestino+2, colunaDestino-2)){				
					return true;
				}
			}		
			//inferior direita
			if(checarLimite.checar(linhaDestino+2, colunaDestino+2)){
				if(podeComer.checar(linhaDestino, colunaDestino, linhaDestino+2, colunaDestino+2)){				
					return true;
				}
			}
		}
		return false;
	}
}