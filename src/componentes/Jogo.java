package componentes;

import java.util.ArrayList;

import Excecoes.DamasException;

public class Jogo {
	
	private ArrayList<Partida> partidas = new ArrayList<Partida>();
	private ArrayList<Tabuleiro> tabuleiros = new ArrayList<Tabuleiro>();
	
	public Partida buscarPartida(String idPartida){
		for(Partida p: partidas){
			if(p.getIdPartida().equals(idPartida)){
				return p;
			}
		}
		return null;
	}
	public void removerPartida(String idPartida){
		for(Partida p: partidas){
			if(p.getIdPartida().equals(idPartida)){
				partidas.remove(p);
			}
		}		
	}
	
	public void criarTabuleiro(String idTabuleiro, int tamanho) throws DamasException{
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.setTamanho(tamanho);
		tabuleiro.setIdTabuleiro(idTabuleiro);
		tabuleiro.definirTamanho();
		tabuleiro.adicionarPecas();
		tabuleiros.add(tabuleiro);
	}
	public void encerrarPartidaSemSalvar(String idPartida){
		
		Partida partida = new Partida();		
		
		for (Partida par : partidas) {
			if(par.getIdPartida().equals(idPartida)){
				partida = par;
			}
		}
		partida.getTabuleiro().zerarTabuleiro();
		partida.getTabuleiro().adicionarPecas();		
		
	}
	public void adicionarPartida(Partida partida){
		partidas.add(partida);
	}
	
	public Partida iniciarPartida(String idPartida, String idTabuleiro, String jogador1, String jogador2, int jogadorPedraBranca, String regraMovimento, boolean permiteSopro) throws DamasException{
		
		Partida partida = new Partida();
		Tabuleiro tabuleiro = new Tabuleiro();
		
		if(jogador1.length() == 0 || jogador1.equals(""))
			throw new DamasException("Nome do jogador deve ser definido");
		if(jogador2.length() == 0 || jogador1.equals(""))
			throw new DamasException("Nome do jogador deve ser definido");
		if(regraMovimento == null || regraMovimento.equals(""))
			throw new DamasException("regra de movimentacao nao definida");
		if(!regraMovimento.equals("regraMovimento1") && !regraMovimento.equals("regraMovimento2"))
			throw new DamasException("regra de movimentacao invalida");		
		if(idPartida == null || idPartida.equals("")){
			throw new DamasException("O ID da partida informado e invalido");
		}		
	
		int temp = 0;
		for (Partida par : partidas) {
			if(par.getIdPartida().equals(idPartida)){
				partida = par;
			}
			else{
				temp++;				
			}
		}
		if(temp == partidas.size()){
			partidas.add(partida);
		}
		
		for (Tabuleiro tab : tabuleiros) {
			if(tab.getIdTabuleiro().equals(idTabuleiro)){
				tabuleiro = tab;
			}
		}
		
		partida.iniciarPartida(idPartida, tabuleiro, jogador1, jogador2, jogadorPedraBranca, regraMovimento, permiteSopro);
		return partida;
	}
}
