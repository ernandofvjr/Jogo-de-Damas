package regras;

import componentes.Tabuleiro;

public class VerificarLimite extends Regra implements ChecarUm {
	
	public VerificarLimite(Tabuleiro tabuleiro){
		setTabuleiro(tabuleiro);
	}
	/**
	 * verifica se está no limite dos tabuleiros
	 */
	public boolean checar(int linha, int coluna){
		if(linha >= 0 && linha < getTabuleiro().getTamanho()){
			if(coluna >= 0 && coluna < getTabuleiro().getTamanho()){
				return true;
			}
		}
		return false;
	}
}