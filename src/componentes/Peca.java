package componentes;

public class Peca {
	
	private String cor;
	private boolean isDama;
	
	public Peca(String cor){
		isDama = false;
		this.cor = cor;
	}

	public String getCor() {
		return cor;
	}
	public boolean isDama() {
		return isDama;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public void setDama(boolean isDama) {
		this.isDama = isDama;
	}	
}
