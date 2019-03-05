package componentes;

import Excecoes.DamasException;
import componentes.IFacadeJogoDamas;;

public class Adapter implements IFacadeJogoDamas{

	private Partida partida = new Partida();
	private Jogo jogo = new Jogo();
	
	public void zerarJogo() {
		this.jogo = new Jogo();
	}
	
	public void criarTabuleiro(String idTabuleiro, int tamanho) throws DamasException {				
		jogo.criarTabuleiro(idTabuleiro, tamanho);
	}
	
	public void iniciarPartida(String id, String idTabuleiro, String jogador1, String jogador2, int jogadorPedraBranca,	String regraMovimento, boolean permiteSopro) throws DamasException {
		this.partida = jogo.iniciarPartida(id, idTabuleiro, jogador1, jogador2, jogadorPedraBranca, regraMovimento, permiteSopro);
	}
	
	public void ativarPosicionamentoManual(String idPartida) {		
		partida.ativarPosicionamentoManual();
		partida.getTabuleiro().zerarTabuleiro();
	}	
	
	public void posicionarPeca(String idPartida, int cor, int linha, String coluna) throws DamasException {
		partida.posicionarPeca(idPartida, cor, linha, coluna);
	}
	
	public void movimentoJogador(int numeroJogador, int linhaOrigem, String colunaOrigem, int linhaDestino, String colunaDestino) throws DamasException {	
		partida.movimentoJogador(numeroJogador, linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
	}
	
	public String getVencedorPartida(String id) throws DamasException {
		return partida.getVencedor();
	}
	
	public boolean isOcupada(String idPartida, int linha, String coluna) throws DamasException {		
		return partida.isOcupada(idPartida, linha, coluna);
	}
	
	public String getCorCasa(String idPartida, int linha, String coluna) throws DamasException {
		
		int linhaO = partida.getTabuleiro().traduzirLinha(linha);
		int colunaO = partida.getTabuleiro().traduzirColuna(coluna);;
		
		if( idPartida == null || idPartida.equals("")){
			throw new DamasException("O ID da partida informado e invalido");
		}
		if(!idPartida.equals(partida.getIdPartida())){
			throw new DamasException("O ID da partida informado nao existe");
		}		
		return partida.getTabuleiro().getCorCasa(idPartida, linhaO, colunaO);
	}
	
	public String getCorPeca(String idPartida, int linha, String coluna) throws DamasException {
		
		int linhaO = partida.getTabuleiro().traduzirLinha(linha);
		int colunaO = partida.getTabuleiro().traduzirColuna(coluna);		
		
		if(idPartida == null || idPartida.equals("")){
			throw new DamasException("O ID da partida informado nao e valido");
		}
		if(!(idPartida.equals(partida.getIdPartida()))){
			throw new DamasException("O ID da partida informado nao existe");
		}
		return partida.getTabuleiro().getCorPeca(linhaO, colunaO);
	}
	
	public void encerrarPartidaSemSalvar(String idPartida) throws DamasException {		
		jogo.encerrarPartidaSemSalvar(idPartida);		
	}
	
	public void descartarPartida(String idPartida) throws DamasException {
		jogo.removerPartida(idPartida);		
	}
	
	public void finalizarESalvarJogo(String idPartida) throws DamasException {		
		jogo.adicionarPartida(partida);		
	}
	
	public void continuarPartida(String idPartida) throws DamasException {
		this.partida = jogo.buscarPartida(idPartida);	
	}
	
	public boolean isDama(String idPartida, int linha, String coluna) throws DamasException {
		int linhaO = partida.getTabuleiro().traduzirLinha(linha);
		int colunaO = partida.getTabuleiro().traduzirColuna(coluna);
		return partida.getTabuleiro().getTabuleiro().get(linhaO)[colunaO].isDama();
	}
	
	public void setVez(String idPartida, int numeroJogador) throws DamasException {	
		partida.setVez(numeroJogador);
	}
}
