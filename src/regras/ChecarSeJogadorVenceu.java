package regras;

import Excecoes.DamasException;
import componentes.Peca;
import componentes.Tabuleiro;

public class ChecarSeJogadorVenceu extends Regra {
	
	public ChecarSeJogadorVenceu(Tabuleiro tabuleiro){
		setTabuleiro(tabuleiro);
	}
	public int checar() throws DamasException{
		
		VerificarSopro verificarSopro = new VerificarSopro(getTabuleiro());
		VerificarSePodeMover verificarSePodeMover = new VerificarSePodeMover(getTabuleiro());
		int pecasBrancas = 0;
		int pecasEscuras = 0;
		
		for(int i = 0; i < getTabuleiro().getTamanho(); i++){
			for( int j = 0; j < getTabuleiro().getTamanho(); j++){
				if(getTabuleiro().getTabuleiro().get(i)[j] != null){
					Peca peca = getTabuleiro().getTabuleiro().get(i)[j];
					if(peca.getCor().equals("branca")){
						if(verificarSopro.checar(i, j, peca.getCor()) == true || verificarSePodeMover.checar(i, j)){
							pecasBrancas++;
						}	
					}
					else{
						if(verificarSopro.checar(i, j, peca.getCor()) == true || verificarSePodeMover.checar(i, j)){
							pecasEscuras++;
						}	
					}									
				}
			}
		}
		
		if(pecasEscuras == 0){
			return 1;
		}
		else if(pecasBrancas == 0){
			return 2;
		}
		else{
			return 0;
		}		
	}
}
