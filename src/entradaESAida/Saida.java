package entradaESAida;

import java.util.ArrayList;

import componentes.Peca;
import componentes.Tabuleiro;

public class Saida {
	
	private Tabuleiro tabuleiro;
	
	public Saida(Tabuleiro tabuleiro){
		this.tabuleiro = tabuleiro;
	}
	public ArrayList<Peca[]> getEstadoAtual(){
		return tabuleiro.getTabuleiro();
	}
	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
}