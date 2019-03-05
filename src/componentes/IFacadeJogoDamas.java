package componentes;

import Excecoes.DamasException;

public interface IFacadeJogoDamas {
	/**
	 * Apaga todos os dados do jogo.
	 */
	void zerarJogo();

	/**
	 * Cria um tabuleiro de tamanho TxT
	 * 
	 * @param idTabuleiro
	 *            Identificador do tabuleiro a ser criado
	 * 
	 * @param dimensao
	 *            Dimensao que o tabuleiro tera (ex: para uma dimensao = 8 o
	 *            tabuleiro tera 8 linhas x 8 colunas)
	 * 
	 * @throws DamasException
	 *             Excecao que pode ser lancada em caso de nao atendimento aos
	 *             casos de teste
	 */
	void criarTabuleiro(String idTabuleiro, int tamanho) throws DamasException;

	/**
	 * Metodo que cria e inicia uma partida
	 * 
	 * @param id
	 *            Identificador da partida
	 * 
	 * @param idTabuleiro
	 *            Identificador do tabuleiro a ser utilizado na partida
	 * 
	 * @param jogador1
	 *            Nome do jogador 1
	 * 
	 * @param jogador2
	 *            Nome do jogador 2
	 * 
	 * @param jogadorPedraBranca
	 *            Valor inteiro que identifica o jogador que devera jogar com as
	 *            pecas brancas (e consequentemente movimentar a primeira peca
	 *            do jogo) Esse valor pode ser somente 1 ou 2 - numeros que se
	 *            referem ao primeiro e segundo jogadores, respectivamente
	 * 
	 * @param regraMovimento
	 *            Identificador da regra de movimento que devera ser utilizada
	 *            na partida
	 * 
	 * @param permiteSopro
	 *            Identificador da regra de sopro que devera ser utilizada na
	 *            partida
	 * 
	 * @throws DamasException
	 *             Excecao que pode ser lancada em caso de nao atendimento aos
	 *             casos de teste
	 * 
	 */
	void iniciarPartida(String id, String idTabuleiro, String jogador1, String jogador2, int jogadorPedraBranca,
			String regraMovimento, boolean permiteSopro) throws DamasException;

	/**
	 * Metodo ativa o posicionamento manual para fins de testes
	 * 
	 * @param idPartida
	 *            Identidficador da partida que ser� utilizada
	 */
	void ativarPosicionamentoManual(String idPartida);

	/**
	 * Metodo que posiciona uma peca em um determinado lugar no tabuleiro de
	 * acordo com as coordenadas
	 * 
	 * @param idPartida
	 *            Identificador da partida
	 * @param cor
	 *            Cor da peca que ser� posicionada (branca ou escura)
	 * @param linha
	 *            Linha onde a peca ser� posicionada
	 * @param coluna
	 *            Coluna onde a peca ser� posicionada
	 * 
	 * @throws DamasException
	 *             Excecao que pode ser lancada em caso de nao atendimento aos
	 *             casos de teste
	 */
	void posicionarPeca(String idPartida, int cor, int linha, String coluna) throws DamasException;

	/**
	 * Metodo respov�vel por realizar o movimento (jogada) de um jogador
	 * 
	 * @param numeroJogador
	 *            Identificador do jogador da vez
	 * @param linhaOrigem
	 *            Linha de origem da peca a ser deslocada
	 * @param colunaOrigem
	 *            Coluna de origem da peca a ser deslocada
	 * @param linhaDestino
	 *            Linha de destino da peca a ser deslocada
	 * @param colunaDestino
	 *            Coluna de destino da peca a ser deslocada
	 * @throws DamasException
	 *             Excecao que pode ser lancada em caso de nao atendimento aos
	 *             casos de teste
	 */
	void movimentoJogador(int numeroJogador, int linhaOrigem, String colunaOrigem, int linhaDestino,
			String colunaDestino) throws DamasException;

	/**
	 * M�todo que retorna o vencedor da partida
	 * 
	 * @param id
	 *            Identificador da partida
	 * @return Nome do vencedor da partida
	 * @throws DamasException
	 *             Excecao que pode ser lancada em caso de nao atendimento aos
	 *             casos de teste
	 */
	String getVencedorPartida(String id) throws DamasException;

	/**
	 * Verifica se a casa do tabuleiro est� livre ou ocupada a partir das
	 * coordenadas passadas (Ex: linha = B, coluna = 4).
	 *
	 * @param linha
	 *            Letra que representa a linha do tabuleiro (Ex: B)
	 * @param coluna
	 *            N�mero que representa a coluna do tabuleiro
	 * 
	 * @return Nome do vencedor da partida
	 *
	 * @throws DamasException
	 *             Exce��o que pode ser lan�ada em caso de descomprimento das
	 *             regras pr�-estabelicidas para a aplica��o
	 *
	 */
	boolean isOcupada(String idPartida, int linha, String coluna) throws DamasException;

	/**
	 * Obtem o nome da cor de uma determinada casa do tabuleiro informado
	 * 
	 * @param idPartida
	 *            Identificador da partida
	 * 
	 * @param linha
	 *            Linha de consulta onde a peca devera estar
	 * 
	 * @param coluna
	 *            Coluna de consulta onde a peca devera estar
	 * 
	 * @return Cor da casa (podendo ser somente "branca" ou "escura")
	 * 
	 * @throws DamasException
	 *             Excecao que pode ser lancada em caso de nao atendimento aos
	 *             casos de teste
	 */
	String getCorCasa(String idPartida, int linha, String coluna) throws DamasException;

	/**
	 * Obtem o nome da cor de uma determinada peca em uma determinada casa do
	 * tabuleiro informado
	 * 
	 * @param idPartida
	 *            Identificador do tabuleiro
	 * 
	 * @param linha
	 *            Linha de consulta onde a peca devera estar
	 * 
	 * @param coluna
	 *            Coluna de consulta onde a peca devera estar
	 * 
	 * @return Cor da peca (podendo ser somente "branca" ou "escura")
	 * 
	 * @throws DamasException
	 *             Excecao que pode ser lancada em caso de nao atendimento aos
	 *             casos de teste
	 */
	String getCorPeca(String idPartida, int linha, String coluna) throws DamasException;

	/**
	 * M�todo respons�vel por encerrar a partida sem salvar seu estado
	 * 
	 * @param id
	 *            Identificador da partida
	 *            
	 * @throws DamasException
	 *             Excecao que pode ser lancada em caso de nao atendimento aos
	 *             casos de teste
	 */
	void encerrarPartidaSemSalvar(String idPartida) throws DamasException;

	/**
	 * M�todo que descarta (exclui) uma partida
	 * 
	 * @param id
	 *            Identificador da partida
	 *            
	 * @throws DamasException
	 *             Excecao que pode ser lancada em caso de nao atendimento aos
	 *             casos de teste            
	 */
	void descartarPartida(String idPartida) throws DamasException;

	/**
	 * M�todo que finaliza o jogo e salva o estado da partida
	 * 
	 * @param idPartida
	 *            Identificador da partida
	 */
	void finalizarESalvarJogo(String idPartida) throws DamasException;

	/**
	 * M�todo que requer a continua��o de uma partida salva previamente
	 * 
	 * @param idPartida
	 *            Identificador da partida
	 * 
	 * @throws DamasException
	 *             Excecao que pode ser lancada em caso de nao atendimento aos
	 *             casos de teste
	 */
	void continuarPartida(String idPartida) throws DamasException;

	/**
	 * M�todo que verifica se uma pe�a � dama ou n�o
	 * 
	 * @param idPartida
	 *            Identificador da partida
	 *            
	 * @param linha
	 *            Linha onde se encontra a pe�a
	 *            
	 * @param coluna
	 *            Coluna onde se encontra a pe�a
	 * 
	 * @return � dama ou n�o
	 * 
	 * @throws DamasException
	 *             Excecao que pode ser lancada em caso de nao atendimento aos
	 *             casos de teste
	 */
	boolean isDama(String idPartida, int linha, String coluna) throws DamasException;

	/**
	 * M�todo que define a vez da jogada para determinado jogador
	 * 
	 * @param idPartida
	 *            Identificador da partida
	 *            
	 * @param numeroJogador
	 *            Identificador do jogador na partida
	 *            
	 * @throws DamasException
	 *             Excecao que pode ser lancada em caso de nao atendimento aos
	 *             casos de teste
	 */
	void setVez(String idPartida, int numeroJogador) throws DamasException;
}
