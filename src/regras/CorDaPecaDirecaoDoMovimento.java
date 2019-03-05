package regras;

import Excecoes.DamasException;

public class CorDaPecaDirecaoDoMovimento extends Regra implements ChecarDois {
	
	/**
	 * verifica se a pe�a est� andando na dire��o certa
	 * @throws DamasException 
	 */
	public boolean checar(int linhaOrigem, int linhaDestino, String cor) throws DamasException{
		if(cor.equals("escura")){
			
			if(linhaDestino == linhaOrigem+1 || linhaDestino == linhaOrigem+2){
				return true;
			}
			throw new DamasException("A peca pertence ao adversario");
		}
		else{
			if(linhaDestino == linhaOrigem-1 || linhaDestino == linhaOrigem-2){
				return true;
			}
			throw new DamasException("A peca pertence ao adversario");
		}
	}
}