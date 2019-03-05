package componentes;

public class Jogador {
	private String nome;
	private String corDaPeca;
	private int numeroDoJogador;
	
	public Jogador(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	public String getCorDaPeca() {
		return corDaPeca;
	}
	public int getNumeroDoJogador() {
		return numeroDoJogador;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCorDaPeca(String corDaPeca) {
		this.corDaPeca = corDaPeca;
	}
	public void setNumeroDoJogador(int numeroDoJogador) {
		this.numeroDoJogador = numeroDoJogador;
	}	
}
