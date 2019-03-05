package movimentos;

import java.util.ArrayList;

import componentes.Peca;
import componentes.Tabuleiro;

public class ComerComDama {
	private Tabuleiro tabuleiro;
	
	public ComerComDama(Tabuleiro tabuleiro){
		this.tabuleiro = tabuleiro;
	}
	/**
	 * método que captura uma peça com dama
	 * @param linhaOrigem
	 * @param colunaOrigem
	 * @param pecaAlvo
	 * @param cor
	 */
	public void comer(int linhaOrigem, int colunaOrigem, ArrayList<Integer> pecaAlvo, String cor){
		
		int linhaPecaAlvo = pecaAlvo.get(0);
		int colunaPecaAlvo = pecaAlvo.get(1);
		
		if(pecaAlvo.get(2) < 0 && pecaAlvo.get(3) < 0){			
			tabuleiro.getTabuleiro().get(linhaPecaAlvo)[colunaPecaAlvo] = null;
			linhaPecaAlvo--;
			colunaPecaAlvo--;
			Peca pecaTemp = new Peca("branca");
			pecaTemp = tabuleiro.getTabuleiro().get(linhaOrigem)[colunaOrigem];
			tabuleiro.getTabuleiro().get(linhaOrigem)[colunaOrigem] = null;
			tabuleiro.getTabuleiro().get(linhaPecaAlvo)[colunaPecaAlvo] = pecaTemp;			
		}
		else if(pecaAlvo.get(2) < 0 && pecaAlvo.get(3) > 0){
			tabuleiro.getTabuleiro().get(linhaPecaAlvo)[colunaPecaAlvo] = null;
			linhaPecaAlvo--;
			colunaPecaAlvo++;
			Peca pecaTemp = new Peca("branca");
			pecaTemp = tabuleiro.getTabuleiro().get(linhaOrigem)[colunaOrigem];
			tabuleiro.getTabuleiro().get(linhaOrigem)[colunaOrigem] = null;
			tabuleiro.getTabuleiro().get(linhaPecaAlvo)[colunaPecaAlvo] = pecaTemp;
		}
		else if(pecaAlvo.get(2) > 0 && pecaAlvo.get(3) < 0){
			tabuleiro.getTabuleiro().get(linhaPecaAlvo)[colunaPecaAlvo] = null;
			linhaPecaAlvo++;
			colunaPecaAlvo--;
			Peca pecaTemp = new Peca("branca");
			pecaTemp = tabuleiro.getTabuleiro().get(linhaOrigem)[colunaOrigem];
			tabuleiro.getTabuleiro().get(linhaOrigem)[colunaOrigem] = null;
			tabuleiro.getTabuleiro().get(linhaPecaAlvo)[colunaPecaAlvo] = pecaTemp;
		}
		else{
			tabuleiro.getTabuleiro().get(linhaPecaAlvo)[colunaPecaAlvo] = null;
			linhaPecaAlvo++;
			colunaPecaAlvo++;
			Peca pecaTemp = new Peca("branca");
			pecaTemp = tabuleiro.getTabuleiro().get(linhaOrigem)[colunaOrigem];
			tabuleiro.getTabuleiro().get(linhaOrigem)[colunaOrigem] = null;
			tabuleiro.getTabuleiro().get(linhaPecaAlvo)[colunaPecaAlvo] = pecaTemp;
		}
	}

}
