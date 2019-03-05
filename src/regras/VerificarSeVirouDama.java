package regras;

import componentes.Tabuleiro;

public class VerificarSeVirouDama extends Regra implements ChecarDois {
	
	public VerificarSeVirouDama(Tabuleiro tabuleiro){
		this.setTabuleiro(tabuleiro);
	}
	/**
	 * verifica se a peça virou dama
	 */
	public boolean checar(int linhaDestino, int colunaDestino, String cor) {
		if(cor.equals("branca")){
			if(linhaDestino == 0){
				return true;
			}
		}
		else{
			if(linhaDestino == getTabuleiro().getTamanho()-1){
				return true;
			}
		}		
		return false;
	}
}