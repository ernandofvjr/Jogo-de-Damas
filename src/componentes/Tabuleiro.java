package componentes;

import java.util.ArrayList;

import Excecoes.DamasException;
/**
 * 
 * 
 *
 */
public class Tabuleiro{
	
	private ArrayList<Peca[]> tabuleiro = new ArrayList<Peca[]>();
	private int[] coordenadaLinha;
	private String[] coordenadaColuna = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	private String idTabuleiro;
	private int tamanho;	
	
	/**
	 * 
	 * @param linha
	 * @param coluna
	 * @return cor da peça
	 */
	public String getCorPeca(int linha, int coluna) throws DamasException{
		Peca peca = tabuleiro.get(linha)[coluna];
		if(peca != null){
			return peca.getCor();
		}
		else{
			throw new DamasException("Nao ha peca nas coordenadas informadas");
		}
	}
	/**
	 * Adiciona os arrays no tabuleiro de acordo com o tamanho
	 */
	public void definirTamanho(){
		
		if(tamanho > 5 && tamanho%2==0 && tamanho < 27){
			for(int i = 1; i<=tamanho; i++){
				Peca[] linha = new Peca[tamanho];
				tabuleiro.add(linha);
			}
			int tam = tamanho;
			coordenadaLinha = new int[tamanho];
			for(int j = 0; j<tamanho; j++){
				coordenadaLinha[j] = tam;
				tam--;
			}
		}
	}
	/**
	 * verifica a quantidade de peças de um cor se dama = true ele verifica só a quantidade de peças que são damas se for false verifica todas
	 * @param cor
	 * @param dama
	 * @return quantidade de pecas
	 */
	public int verificarQtdPecas(String cor, boolean dama){
		int cont = 0;
		for(int i = 0; i<tamanho; i++){
			for( int j = 0; j<tamanho; j++){
				Peca peca = tabuleiro.get(i)[j];
				if(peca != null && peca.getCor().equals(cor)){
					if(dama == false)
						cont++;
					else{
						if(peca.isDama() == true && dama == true){
							cont++;
						}
					}
				}
			}
		}
		return cont;
	}
	public void clonar(Tabuleiro tab){
		for(int i = 0; i < tamanho; i++){
			for(int j = 0; j < tamanho; j++){				
				tab.tabuleiro.get(i)[j] = this.tabuleiro.get(i)[j];				
			}
		}
	}
	/**
	 * E método adicona as peças no tabuleiro de cada jogador no inicio da partida
	 */
	public void adicionarPecas(){
		
		
		for(int i = 0; i<tamanho; i++){
			int var;
			if(i%2==0 || i == 0){
				var = 1;
			}
			else{
				var = 0;
			}
			Peca[] linha = tabuleiro.get(i);
			int metade = tamanho/2;
			for(int j = var; j<tamanho; j+=2){
				if(i+1 != metade && i+1 != metade+1){
					if(i+1 < metade)
						linha[j] = new Peca("escura");
					else
						linha[j] = new Peca("branca");
				}
			}
		}
	}
	/**
	 * remove todas as peças do tabuleiro
	 */
	public void zerarTabuleiro(){
		for(int i=0; i<tamanho;i++){
			for(int j = 0; j < tamanho;j++){
				tabuleiro.get(i)[j] = null;
			}
		}
	}
	/**
	 * 
	 * @param linha
	 * @param coluna
	 * @return cor da casa
	 */
	public String getCorCasa(String idPartida, int linha, int coluna){
		
		String cor;
		
		if(linha%2!=0){
			if(coluna%2==0){
				cor = "escura";
			}
			else{
				cor = "branca";
			}
		}
		else{
			if(coluna%2!=0){
				cor = "escura";
			}
			else{
				cor = "branca";
			}			
		}		
		return cor;
	}
	/**
	 * 
	 * @param linha
	 * @return linha traduzida
	 */
	public int traduzirLinha(int linha) throws DamasException{
		if(linha < -2){
			throw new DamasException("A linha informada nao e valida");
		}
		if(linha > tamanho || linha < 1){
			throw new DamasException("A linha informada nao esta nos limites do tabuleiro");
		}		
		if(linha <= tamanho){
			for(int i = 0; i<tamanho; i++){
				if(coordenadaLinha[i] == linha){
					return i;
				}
			}
		}
		return -1;
	}
	/**
	 * 
	 * @param letra
	 * @returncoluna traduzida
	 */
	public int traduzirColuna(String letra) throws DamasException{
		
		if(letra == null || letra.equals("")){
			throw new DamasException("A coluna informada nao e valida");
		}
		int coluna = -1;
		for(int i = 0; i<tamanho; i++){
			if(coordenadaColuna[i].equals(letra.toUpperCase()))
				coluna = i;
		}
		if(coluna == -1){
			throw new DamasException("A coluna informada nao e valida");
		}
		return coluna;
	}
	/**
	 * esse método pega a letra que o usuário digitou e converte para o indice no array
	 * @param letra
	 * @return indice da coluna no array
	 */
	public int traduzirC(String letra){		
		int coluna = -1;
		for(int i = 0; i<tamanho; i++){
			if(coordenadaColuna[i].equals(letra.toUpperCase()))
				coluna = i;
		}		
		return coluna;
	}
	/**
	 * esse método pega o número da coluna que o usuário digitou e converte para o indice no array
	 * @param linha
	 * @return
	 */
	public int traduzirL(int linha){
		
		if(linha <= tamanho){
			for(int i = 0; i<tamanho; i++){
				if(coordenadaLinha[i] == linha){
					return i;
				}
			}
		}
		return -1;
	}
	/**
	 * transforma uma peça em dama
	 * @param linha
	 * @param coluna
	 * 
	 * 
	 */
	public void vaiSerDama(int linha, int coluna){
		if(tabuleiro.get(linha)[coluna] != null){
			tabuleiro.get(linha)[coluna].setDama(true);
		}
	}
	
	public ArrayList<Peca[]> getTabuleiro() {
		return tabuleiro;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTabuleiro(ArrayList<Peca[]> tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	public void setTamanho(int tamanho) throws DamasException {
		if(tamanho<6 || tamanho >26 || tamanho%2!=0){
			throw new DamasException("Tamanho de tabuleiro invalido");
		}
		this.tamanho = tamanho;
	}
	public String getIdTabuleiro() {
		return idTabuleiro;
	}
	public void setIdTabuleiro(String idTabuleiro) {
		this.idTabuleiro = idTabuleiro;
	}
}
