package regras;

public class PecaComumAndarUmaCasa extends Regra implements ChecarTres {
	/**
	 * verifica se a peça comum está andando apenas uma casa
	 */
	public boolean checar(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino){
		if(linhaDestino == linhaOrigem +1 || linhaDestino == linhaOrigem -1){
			if(colunaDestino == colunaOrigem +1 || colunaDestino == colunaOrigem -1){
				return true;
			}
		}
		
		return false;
	}
}