package regras;

import componentes.Tabuleiro;

public class ChecarTrajetoDaDama extends Regra {
	
	public ChecarTrajetoDaDama(Tabuleiro tabuleiro){
		this.setTabuleiro(tabuleiro);
	}
	/**
	 * checa se a dama pode mover
	 * @param linhaOrigem
	 * @param colunaOrigem
	 * @param linhaDestino
	 * @param colunaDestino
	 * @return boolean
	 */
	public boolean checar(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino){
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
					return false;
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
					return false;
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
					return false;
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
					return false;
				}								
			}
		}
		return true;		
	}
}
