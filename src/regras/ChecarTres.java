package regras;

import Excecoes.DamasException;

public interface ChecarTres {
	
	public boolean checar(int linha, int coluna, int linha2, int coluna2) throws DamasException;

}
