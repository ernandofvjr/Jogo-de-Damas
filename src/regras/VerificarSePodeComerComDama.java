package regras;

import java.util.ArrayList;

import componentes.Tabuleiro;

public class VerificarSePodeComerComDama extends Regra{
	
	private ArrayList<Integer> pecaAlvo;
	
	public VerificarSePodeComerComDama(Tabuleiro tabuleiro){
		setTabuleiro(tabuleiro);
	}
	/**
	 * verifica se pode comer com dama
	 * @param linhaOrigem
	 * @param colunaOrigem
	 * @param linhaDestino
	 * @param colunaDestino
	 * @return
	 */
	public ArrayList<Integer> checar(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino){
		pecaAlvo = new ArrayList<Integer>();
		
		int direcaoLinha = linhaDestino - linhaOrigem;
		int direcaoColuna = colunaDestino - colunaOrigem;
		
		int linha = linhaOrigem;
		int coluna = colunaOrigem;
		
		
		if(direcaoLinha < 0 && direcaoColuna < 0){			
			
			while(true){
				linha--;
				coluna--;
				if(linha == linhaDestino && coluna == colunaDestino){
					
					break;
				}
				if(getTabuleiro().getTabuleiro().get(linha)[coluna]!=null){
					pecaAlvo.add(linha);
					pecaAlvo.add(coluna);
					pecaAlvo.add(direcaoLinha);
					pecaAlvo.add(direcaoColuna);
				}								
			}			
		}
		else if(direcaoLinha < 0 && direcaoColuna > 0){			
			
			while(true){
				linha--;
				coluna++;
				if(linha == linhaDestino && coluna == colunaDestino){
					break;
				}
				if(getTabuleiro().getTabuleiro().get(linha)[coluna]!=null){
					pecaAlvo.add(linha);
					pecaAlvo.add(coluna);
					pecaAlvo.add(direcaoLinha);
					pecaAlvo.add(direcaoColuna);
				}								
			}
		}
		else if(direcaoLinha > 0 && direcaoColuna < 0){			
			
			while(true){
				linha++;
				coluna--;
				if(linha == linhaDestino && coluna == colunaDestino){
					break;
				}
				if(getTabuleiro().getTabuleiro().get(linha)[coluna]!=null){
					pecaAlvo.add(linha);
					pecaAlvo.add(coluna);
					pecaAlvo.add(direcaoLinha);
					pecaAlvo.add(direcaoColuna);
				}								
			}
		}
		else{		
			
			while(true){
				linha++;
				coluna++;
				if(linha == linhaDestino && coluna == colunaDestino){
					break;
				}
				if(getTabuleiro().getTabuleiro().get(linha)[coluna]!=null){
					pecaAlvo.add(linha);
					pecaAlvo.add(coluna);
					pecaAlvo.add(direcaoLinha);
					pecaAlvo.add(direcaoColuna);
				}								
			}
		}
		return pecaAlvo;
	}
}
