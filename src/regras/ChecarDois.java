package regras;

import Excecoes.DamasException;

public interface ChecarDois {
	
	public boolean checar(int linha, int coluna, String cor) throws DamasException;

}
