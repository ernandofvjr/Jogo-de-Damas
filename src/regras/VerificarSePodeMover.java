package regras;

import Excecoes.DamasException;
import componentes.Tabuleiro;

public class VerificarSePodeMover extends Regra {
	
	
	public VerificarSePodeMover(Tabuleiro tabuleiro){
		setTabuleiro(tabuleiro);
	}
	
	public boolean checar(int linhaDestino, int colunaDestino) throws DamasException{
		CasaDesocupada casaDesocupada = new CasaDesocupada(getTabuleiro());
		VerificarLimite verificarLimite = new VerificarLimite(getTabuleiro());
		if(verificarLimite.checar(linhaDestino-1, colunaDestino-1)){
			if(casaDesocupada.checar(linhaDestino-1, colunaDestino-1)){
				return true;
			}
		}
		if(verificarLimite.checar(linhaDestino+1, colunaDestino-1)){
			if(casaDesocupada.checar(linhaDestino+1, colunaDestino-1)){
				return true;
			}
		}
		if(verificarLimite.checar(linhaDestino-1, colunaDestino+1)){
			if(casaDesocupada.checar(linhaDestino-1, colunaDestino+1)){
				return true;
			}
		}
		if(verificarLimite.checar(linhaDestino+1, colunaDestino+1)){
			if(casaDesocupada.checar(linhaDestino+1, colunaDestino+1)){
				return true;
			}
		}
		return false;		
	}
}
