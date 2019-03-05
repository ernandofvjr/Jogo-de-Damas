package componentes;

import java.util.ArrayList;

import Excecoes.DamasException;
import movimentos.ComerComDama;
import movimentos.ComerPeca;
import movimentos.MoverPeca;
import regras.AndarNaDiagonal;
import regras.CasaDesocupada;
import regras.ChecarTrajetoDaDama;
import regras.CorDaPecaDirecaoDoMovimento;
import regras.PecaComumAndarUmaCasa;
import regras.SeEstaMovendoUmaPeca;
import regras.VerificarLimite;
import regras.VerificarSePodeComer;
import regras.VerificarSePodeComerComDama;
import regras.VerificarSeVirouDama;
import regras.VerificarSopro;

public class ControleDeMovimento {
	
	private Tabuleiro tabuleiro;
	private AndarNaDiagonal andarNaDiagonal;
	private CasaDesocupada casaDesocupada;
	private CorDaPecaDirecaoDoMovimento corDaPecaDirecaoDoMovimento;
	private PecaComumAndarUmaCasa pecaComumAndarUmaCasa;
	private SeEstaMovendoUmaPeca seEstaMovendoUmaPeca;
	private VerificarSePodeComer verificarSePodeComer;
	private VerificarSopro verificarSopro;
	private MoverPeca moverPeca;
	private ComerPeca comerPeca;
	private VerificarSeVirouDama verificarSeVirouDama;
	private VerificarLimite verificarLimite;
	private ChecarTrajetoDaDama checarTrajetoDaDama;
	private VerificarSePodeComerComDama verificarSePodeComerComDama;
	private ComerComDama comerComDama;	
	
	public ControleDeMovimento(Tabuleiro tabuleiro){
		this.tabuleiro = tabuleiro;
		this.andarNaDiagonal = new AndarNaDiagonal(tabuleiro);
		this.casaDesocupada = new CasaDesocupada(tabuleiro);
		this.corDaPecaDirecaoDoMovimento = new CorDaPecaDirecaoDoMovimento();
		this.pecaComumAndarUmaCasa = new PecaComumAndarUmaCasa();
		this.seEstaMovendoUmaPeca = new  SeEstaMovendoUmaPeca(tabuleiro);
		this.verificarSePodeComer = new VerificarSePodeComer(tabuleiro);
		this.verificarSopro = new VerificarSopro(tabuleiro);
		this.verificarLimite = new VerificarLimite(tabuleiro);
		this.moverPeca = new MoverPeca(tabuleiro);
		this.comerPeca = new ComerPeca(tabuleiro);
		this.verificarSeVirouDama = new VerificarSeVirouDama(tabuleiro);
		this.checarTrajetoDaDama = new ChecarTrajetoDaDama(tabuleiro);
		this.verificarSePodeComerComDama = new VerificarSePodeComerComDama(tabuleiro);
		this.comerComDama = new ComerComDama(tabuleiro);
	}
	/**
	 * ele retorna um vetor com valores de 0 a 1 para saber se a peça foi movida ou capturou alguma peça
	 * @param linhaDeOrigem
	 * @param colunaDeOrigem
	 * @param linhaDeDestino
	 * @param colunaDeDestino
	 * @param cor
	 * @param regraMovimento
	 * @return vetor 
	 * @throws DamasException
	 */
	
	public Integer[] mover(int linhaDeOrigem, String colunaDeOrigem, int linhaDeDestino, String colunaDeDestino, String cor, String regraMovimento)throws DamasException{
		
		
		int linhaOrigem = this.tabuleiro.traduzirLinha(linhaDeOrigem);
		int colunaOrigem = this.tabuleiro.traduzirColuna(colunaDeOrigem);
		int linhaD = this.tabuleiro.traduzirL(linhaDeDestino);
		int colunaD = this.tabuleiro.traduzirC(colunaDeDestino);
		if(!verificarLimite.checar(linhaD, colunaD))
			throw new DamasException("A casa de destino nao existe neste tabuleiro");		
		if(!casaDesocupada.checar(linhaOrigem, colunaOrigem)){
			
				if(regraMovimento.equals("regraMovimento1")){
					if(this.tabuleiro.getTabuleiro().get(linhaOrigem)[colunaOrigem].isDama()){
						return pecaDamaAndar(linhaDeOrigem, colunaDeOrigem, linhaDeDestino, colunaDeDestino, cor);
					}
					else{
						return pecaComumAndarEComerParaQualquerLado (linhaDeOrigem, colunaDeOrigem, linhaDeDestino, colunaDeDestino, cor);
					}
					
				}
				else{					
					return pecaComumAndarEComerParaFrente(linhaDeOrigem, colunaDeOrigem, linhaDeDestino, colunaDeDestino, cor);
				}
		}
		else{
			throw new DamasException("A casa de origem esta vazia");
		}
	}
	/**
	 * esse método move a peça só pra sua frente
	 * @param linhaDeOrigem
	 * @param colunaDeOrigem
	 * @param linhaDeDestino
	 * @param colunaDeDestino
	 * @param cor
	 * @return vetor
	 * @throws DamasException
	 */
	public Integer[] pecaComumAndarEComerParaFrente(int linhaDeOrigem, String colunaDeOrigem, int linhaDeDestino, String colunaDeDestino, String cor) throws DamasException{
	
		Integer[] retorno = {0,0};
		
		int linhaOrigem = this.tabuleiro.traduzirLinha(linhaDeOrigem);
		int colunaOrigem = this.tabuleiro.traduzirColuna(colunaDeOrigem);
		
		int linhaDestino = this.tabuleiro.traduzirLinha(linhaDeDestino);
		int colunaDestino = this.tabuleiro.traduzirColuna(colunaDeDestino);		
		
		if(linhaDestino == -1 || colunaDestino == -1){
			throw new DamasException("A casa de destino nao existe neste tabuleiro");
		}		
		
		if(seEstaMovendoUmaPeca.checar(linhaOrigem, colunaOrigem, cor) && casaDesocupada.checar(linhaDestino, colunaDestino) && andarNaDiagonal.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){ 			
			
			if(pecaComumAndarUmaCasa.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino) && corDaPecaDirecaoDoMovimento.checar(linhaOrigem, linhaDestino, cor)){				
				
				moverPeca.mover(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
				virarDama(linhaDestino, colunaDestino, cor);
				retorno[0] = 1;
			}
			else if(verificarSePodeComer.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){				
				comerPeca.comer(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
				virarDama(linhaDestino, colunaDestino, cor);
				retorno[1] = 1;				
			}
		}
		else{
			if(seEstaMovendoUmaPeca.checar(linhaOrigem, colunaOrigem, cor) == false){
				throw new DamasException("A casa de origem esta vazia");
			}
			if(casaDesocupada.checar(linhaDestino, colunaDestino) == false){
				throw new DamasException("Casa ocupada");
			}
		}
		return retorno;
		
	}
	/**
	 * esse método move a peça pra qualquer lado
	 * @param linhaDeOrigem
	 * @param colunaDeOrigem
	 * @param linhaDeDestino
	 * @param colunaDeDestino
	 * @param cor
	 * @return vetor
	 * @throws DamasException
	 */
	public Integer[] pecaComumAndarEComerParaQualquerLado(int linhaDeOrigem, String colunaDeOrigem, int linhaDeDestino, String colunaDeDestino, String cor)throws DamasException{
		
		Integer[] retorno = {0,0};
		
		int linhaOrigem = this.tabuleiro.traduzirLinha(linhaDeOrigem);
		int colunaOrigem = this.tabuleiro.traduzirColuna(colunaDeOrigem);
		
		int linhaDestino = this.tabuleiro.traduzirLinha(linhaDeDestino);
		int colunaDestino = this.tabuleiro.traduzirColuna(colunaDeDestino);
		
		if(linhaDestino == -1 || colunaDestino == -1){
			throw new DamasException("A casa de destino nao existe neste tabuleiro");
		}		
		
		if(seEstaMovendoUmaPeca.checar(linhaOrigem, colunaOrigem, cor) && casaDesocupada.checar(linhaDestino, colunaDestino) && andarNaDiagonal.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){ 			
			if(pecaComumAndarUmaCasa.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){				
				moverPeca.mover(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);	
				virarDama(linhaDestino, colunaDestino, cor);
				retorno[0] = 1;
			}			
			else if(verificarSePodeComer.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){				
				comerPeca.comer(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
				virarDama(linhaDestino, colunaDestino, cor);
				retorno[1] = 1;
			}			
		}
		else{
			if(seEstaMovendoUmaPeca.checar(linhaOrigem, colunaOrigem, cor) == false){
				throw new DamasException("A casa de origem esta vazia");
			}
			if(casaDesocupada.checar(linhaDestino, colunaDestino) == false){
				throw new DamasException("Casa ocupada");
			}
		}
		return retorno;
	}
	/**
	 * Método que move uma dama mais de uma casa por vez
	 * @param linhaDeOrigem
	 * @param colunaDeOrigem
	 * @param linhaDeDestino
	 * @param colunaDeDestino
	 * @param cor
	 * @return vetor
	 * @throws DamasException 
	 */
	public Integer[] pecaDamaAndar(int linhaDeOrigem, String colunaDeOrigem, int linhaDeDestino, String colunaDeDestino, String cor) throws DamasException{
		
		Integer[] retorno = {0,0};	
		
		int linhaOrigem = this.tabuleiro.traduzirLinha(linhaDeOrigem);
		int colunaOrigem = this.tabuleiro.traduzirColuna(colunaDeOrigem);
		
		int linhaDestino = this.tabuleiro.traduzirLinha(linhaDeDestino);
		int colunaDestino = this.tabuleiro.traduzirColuna(colunaDeDestino);
		
		if(seEstaMovendoUmaPeca.checar(linhaOrigem, colunaOrigem, cor) && casaDesocupada.checar(linhaDestino, colunaDestino) && andarNaDiagonal.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){
			if(checarTrajetoDaDama.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){
				moverPeca.mover(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
				retorno[0] = 1;
			}
			else if(verificarSePodeComerComDama.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino).size() == 4){
				ArrayList<Integer> pecaAlvo = new ArrayList<Integer>();
				pecaAlvo = verificarSePodeComerComDama.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
				comerComDama.comer(linhaOrigem, colunaOrigem, pecaAlvo, cor);
				retorno[1] = 1;
			}
		}
		return retorno;
	}
	/**
	 * verifica se uma peça pode virar dama e caso sim atransforma em dama
	 * @param linhaDestino
	 * @param colunaDestino
	 * @param cor
	 */
	public void virarDama(int linhaDestino, int colunaDestino, String cor){
		if(verificarSeVirouDama.checar(linhaDestino, colunaDestino, cor)){
			this.tabuleiro.vaiSerDama(linhaDestino, colunaDestino);
		}
	}
	//retorna uma lista com a seguinte ordem: index 0 - total de peças brancas 1 - total de pecas pretas, 
	//os index seguidos serão as coordenadas das pecas em sopro, primeiro as brancas e depois as pretas.
	public ArrayList<Integer> checarSopro() throws DamasException{
		
		int pecaBranca = 0;
		int pecaPreta = 0;
		
		ArrayList<Integer> pecasEmSopro = new ArrayList<Integer>();
		
		for(int i=0; i< tabuleiro.getTamanho();i++){
			for(int j = 0; j < tabuleiro.getTamanho();j++){
				if(tabuleiro.getTabuleiro().get(i)[j] != null){
					if(tabuleiro.getTabuleiro().get(i)[j].getCor().equals("branca")){
						if(verificarSopro.checar(i, j, "branca")){
							pecasEmSopro.add(0, j);
							pecasEmSopro.add(0, i);
							pecaBranca++;
						}						
					}						
					else{
						if(verificarSopro.checar(i, j, "escura")){
							pecasEmSopro.add(i);
							pecasEmSopro.add(j);
							pecaPreta++;
						}						
					}									
				}
			}
		}
		pecasEmSopro.add(0, pecaPreta);
		pecasEmSopro.add(0, pecaBranca);
		return pecasEmSopro;		
	}	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	public boolean checarCoordenadas(ArrayList<Integer> lista, int linhaDeOrigem, String colunaDeOrigem, int linhaDeDestino, String colunaDeDestino, String cor) throws DamasException{
		
		int linhaOrigem = this.tabuleiro.traduzirLinha(linhaDeOrigem);
		int colunaOrigem = this.tabuleiro.traduzirColuna(colunaDeOrigem);
		
		int linhaDestino = this.tabuleiro.traduzirLinha(linhaDeDestino);
		int colunaDestino = this.tabuleiro.traduzirColuna(colunaDeDestino);
		
		int[] peca = new int[2];
		int countA = 0;
		int countB = 0;
		ArrayList<Integer> destino = new ArrayList<Integer>();		
		int indexA;
			
		if(cor.equals("branca")){			
			
			indexA = 2;			
			for(int i = 0; i < lista.get(0); i++){	
				
				peca[0] = lista.get(indexA);
				indexA++;
				peca[1] = lista.get(indexA);
				indexA++;
				
				destino = destinoDoSopro(peca[0], peca[1], cor);
				
				if(peca[0] == linhaOrigem && peca[1] == colunaOrigem){
					countB++;
				}
				for (int j = 0; j < destino.size(); j++) {
					if(destino.get(j).intValue() == linhaDestino && destino.get(j+1).intValue() == colunaDestino){
						countA++;						
					}
					j++;
				}
			}
		}
		else{
			
			indexA = lista.get(0) * 2 + 2;					
			for(int i = 0; i < lista.get(1); i++){
				
				peca[0] = lista.get(indexA);
				indexA++;
				peca[1] = lista.get(indexA);
				indexA++;
				
				destino = destinoDoSopro(peca[0], peca[1], cor);
				
				if(peca[0] == linhaOrigem && peca[1] == colunaOrigem){
					countB++;
				}
				for (int j = 0; j < destino.size(); j++) {					
					if(destino.get(j).intValue() == linhaDestino && destino.get(j+1).intValue() == colunaDestino){
						countA++;
					}
					j++;
				}				
			}
		}
		if(countA == 0 || countB == 0){
			return false;
		}
		return true;
	}
	public boolean checarSoproDeUmaPeca(int linhaDeOrigem, String colunaDeOrigem, String cor) throws DamasException{
		int linhaDestino = this.tabuleiro.traduzirLinha(linhaDeOrigem);
		int colunaDestino = this.tabuleiro.traduzirColuna(colunaDeOrigem);
		if(verificarSopro.checar(linhaDestino, colunaDestino, cor)){
			return true;
		}
		return false;		
	}
	public ArrayList<Integer> destinoDoSopro(int linhaDeOrigem, int colunaDeOrigem, String cor) throws DamasException{		
		return verificarSopro.checarCoordenadas(linhaDeOrigem, colunaDeOrigem, cor);		
	}
	public boolean pecaComumAndarEComerParaFrenteNaoTraduzido(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, String cor) throws DamasException{
		if(linhaDestino>0 && linhaDestino<tabuleiro.getTamanho() && colunaDestino>0 && colunaDestino<tabuleiro.getTamanho()){
			if(seEstaMovendoUmaPeca.checar(linhaOrigem, colunaOrigem, cor) && casaDesocupada.checar(linhaDestino, colunaDestino) && andarNaDiagonal.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){ 			
				if(pecaComumAndarUmaCasa.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino) && corDaPecaDirecaoDoMovimento.checar(linhaOrigem, linhaDestino, cor)){				
					return true;
				}			
				else if(linhaDestino<tabuleiro.getTamanho()-1 && colunaDestino<tabuleiro.getTamanho()-1 && verificarSePodeComer.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){				
					return true;
				}			
			}
			return false;
		}
		return false;
	}
	public boolean pecaComumAndarEComerParaQualquerLadoNaoTraduzido(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, String cor) throws DamasException{

		if(linhaDestino>0 && linhaDestino<tabuleiro.getTamanho() && colunaDestino>0 && colunaDestino<tabuleiro.getTamanho()){

			if(seEstaMovendoUmaPeca.checar(linhaOrigem, colunaOrigem, cor) && casaDesocupada.checar(linhaDestino, colunaDestino) && andarNaDiagonal.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){ 			
				if(pecaComumAndarUmaCasa.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){	
					return true;
				}			
				else if(linhaDestino<tabuleiro.getTamanho()-1 && colunaDestino<tabuleiro.getTamanho()-1 && verificarSePodeComer.checar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){				
					return true;
				}			
			}
			return false;
		}
		return false;
	}
}