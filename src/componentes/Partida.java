package componentes;

import java.util.ArrayList;

import Excecoes.DamasException;
import regras.ChecarSeJogadorVenceu;
import regras.ContagemDePecas;
import regras.VerificarSeVirouDama;

public class Partida {
	
	private String idPartida;
	private int vez;
	private int jogadorPedraBranca;
	private Jogador jogador1;
	private Jogador jogador2;
	private Tabuleiro tabuleiro;
	private boolean sopro;
	private String regraMovimento;
	private ControleDeMovimento controleDeMovimento;
	private boolean alguemVenceu = false;
	private String vencedor = "ninguem";
	private boolean posicionamentoManual = false;
	private Integer[] contadorCincoJogadas = {0,0};
	
	public boolean isPosicionamentoManual() {
		return posicionamentoManual;
	}

	public void setPosicionamentoManual(boolean posicionamentoManual) {
		this.posicionamentoManual = posicionamentoManual;
	}

	public Partida(){
	}	
	
	public void iniciarPartida(String idPartida, Tabuleiro tabuleiro, String jogador1, String jogador2, int jogadorPedraBranca, String regraMovimento, boolean permiteSopro) throws DamasException{
		
		this.tabuleiro = tabuleiro;
		setIdPartida(idPartida);		
		this.controleDeMovimento = new ControleDeMovimento(this.tabuleiro);	
		this.jogador1 = new Jogador(jogador1);
		this.jogador2 = new Jogador(jogador2);
		this.jogadorPedraBranca = jogadorPedraBranca;
		
		if(jogadorPedraBranca == 1){
			this.jogador1.setCorDaPeca("branca");
			this.jogador1.setNumeroDoJogador(1);
			this.jogador2.setCorDaPeca("escura");
			this.jogador2.setNumeroDoJogador(2);
			this.vez = 1;
		}
		
		else{
			this.jogador1.setCorDaPeca("escura");
			this.jogador1.setNumeroDoJogador(2);
			this.jogador2.setCorDaPeca("branca");
			this.jogador2.setNumeroDoJogador(1);
			this.vez = 2;
		}
		
		this.regraMovimento = regraMovimento;
		if(permiteSopro == true){
			permiteSopro = false;
		}
		else {
			permiteSopro = true;
		}
		this.sopro = permiteSopro;		
		
	}
	public boolean checarMovimento(ArrayList<Integer> lista, int linhaOrigem, String colunaOrigem, int linhaDestino, String colunaDestino, String cor) throws DamasException{		
		colunaOrigem = colunaOrigem.toUpperCase();
		colunaDestino = colunaDestino.toUpperCase();
		if(controleDeMovimento.checarCoordenadas(lista, linhaOrigem, colunaOrigem,linhaDestino, colunaDestino, cor)){
			return true;
		}
		else{
			return false;
		}
	}	
	public void movimentoJogador(int numeroJogador, int linhaOrigem, String colunaOrigem, int linhaDestino, String colunaDestino) throws DamasException{
		
		colunaOrigem = colunaOrigem.toUpperCase();
		colunaDestino = colunaDestino.toUpperCase();
		
		if(vez == numeroJogador){
			
			if(vencedor.equals("ninguem")){
				
				if(linhaOrigem == linhaDestino && colunaOrigem == colunaDestino){
					System.out.println("voce precisa mover a peca");				
				}
				else{			
				String cor;			
				if(numeroJogador == 1){
					cor = jogador1.getCorDaPeca();
				}
				else{
					cor = jogador2.getCorDaPeca();
				}
				
				ArrayList<Integer> listaDasPecasSopradas = new ArrayList<Integer>();
				listaDasPecasSopradas = controleDeMovimento.checarSopro();		
				
				//verifica a regra do sopro se está ou não ativada. E toma as medidas para o jogador obedecer as regras.			
				
				//REGRA DO SOPRO ATIVADA = JOGADOR É OBRIGADO A COMER A PEÇA
				if(sopro){
					//Pecas Brancas
					if(cor.equals("branca")){
						//Se não existe peças brancas sopradas, passa a vez para o outro jogador
						if(listaDasPecasSopradas.get(0) == 0){
							if(controleDeMovimento.mover(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, cor, regraMovimento)[0] == 1){

								passarVez();
							}							
						}
						//Se existe
						else{
							if(!checarMovimento(listaDasPecasSopradas, linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, cor)){
								throw new DamasException("A regra de sopro definida nao permite soprar");
							}
							else{
								if(controleDeMovimento.mover(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, cor, regraMovimento)[1] == 1){
									if(!controleDeMovimento.checarSoproDeUmaPeca(linhaDestino, colunaDestino, cor)){
										passarVez();
									}
								}
								else{
									passarVez();
								}							
							}						
						}					
					}				
					//Pecas Pretas
					else{					
						//Se não existe peças pretas sopradas, passa a vez para o outro jogador
						if(listaDasPecasSopradas.get(1) == 0){		
							if(controleDeMovimento.mover(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, cor, regraMovimento)[0] == 1){
								passarVez();
							}													
						}					
						//Se existe
						else{						
							if(!checarMovimento(listaDasPecasSopradas, linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, cor)){							
								throw new DamasException("A regra de sopro definida nao permite soprar");
							}						
							else{
								if(controleDeMovimento.mover(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, cor, regraMovimento)[1] == 1){
									if(!controleDeMovimento.checarSoproDeUmaPeca(linhaDestino, colunaDestino, cor)){
										passarVez();
									}
								}
								else{
									passarVez();
								}
							}
						}
					}
				}
				
				//REGRA DO SOPRO DESATIVADA = JOGADOR PERDE O JOGO SE NÃO COMER ALGUMA PEÇA SOPRADA
				else{			
					//Pecas Brancas
					if(cor.equals("branca")){					
						//Se não existe peças brancas sopradas, passa a vez para o outro jogador
						if(listaDasPecasSopradas.get(0) == 0){
							if(controleDeMovimento.mover(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, cor, regraMovimento)[0] == 1){								
								passarVez();
							}
							else{
								throw new DamasException("Voce nao pode se mover por mais de uma casa vazia");
							}
						}
						//Se existe
						else{						
							if(!checarMovimento(listaDasPecasSopradas, linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, cor)){								
								if(numeroJogador == jogador1.getNumeroDoJogador()){
									vencedor = jogador2.getNome();
								}
								else{
									vencedor = jogador1.getNome();
								}
								alguemVenceu = true;
							}		
							else{
								if(controleDeMovimento.mover(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, cor, regraMovimento)[1] == 1){
									if(!controleDeMovimento.checarSoproDeUmaPeca(linhaDestino, colunaDestino, cor)){
										passarVez();
									}
								}
								else{
									passarVez();
								}							
							}						
						}
					}				
					//Pecas Pretas
					else{					
						//Se não existe peças pretas sopradas, passa a vez para o outro jogador
						if(listaDasPecasSopradas.get(1) == 0){
							if(controleDeMovimento.mover(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, cor, regraMovimento)[0] == 1){
								passarVez();
							}						
						}					
						//Se existe
						else{
							if(!checarMovimento(listaDasPecasSopradas, linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, cor)){							
								alguemVenceu = true;
								if(numeroJogador == jogador1.getNumeroDoJogador()){
									vencedor = jogador2.getNome();
								}
								else{
									vencedor = jogador1.getNome();
								}
							}						
							else{
								if(controleDeMovimento.mover(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, cor, regraMovimento)[1] == 1){
									if(!controleDeMovimento.checarSoproDeUmaPeca(linhaDestino, colunaDestino, cor)){
										passarVez();
									}
								}
								else{
									passarVez();
								}
							}						
						}
					}
				}
			}				
		}
		
		}
		else{
			throw new DamasException("Jogador, ainda nao e sua vez");
		}		
		checarVencedor();
		checarEmpate();
		
	}
	public void checarVencedor() throws DamasException{
		
		ChecarSeJogadorVenceu checarSeJogadorVenceu = new ChecarSeJogadorVenceu(tabuleiro);
		
		if(checarSeJogadorVenceu.checar() == 1){
			if(jogador1.getCorDaPeca().equals("branca")){
				alguemVenceu = true;
				vencedor = jogador1.getNome();
			}
			else{
				alguemVenceu = true;
				vencedor = jogador2.getNome();
			}			
		}
		
		else if(checarSeJogadorVenceu.checar() == 2){
			if(jogador1.getCorDaPeca().equals("escura")){
				alguemVenceu = true;
				vencedor = jogador1.getNome();
			}
			else{
				alguemVenceu = true;
				vencedor = jogador2.getNome();
			};
		}
	}
	public void checarEmpate(){
		
		ContagemDePecas contagemDePecas = new ContagemDePecas(tabuleiro);
		Integer[] somatorioPecas = contagemDePecas.contar();
		int damaBranca = somatorioPecas[0];
		int pecaBranca = somatorioPecas[1];
		int damaEscura = somatorioPecas[2];
		int pecaEscura = somatorioPecas[3];
		
		//2 damas contra 2 damas
		if(damaBranca == 2 && damaEscura == 2 && pecaBranca == 0 && pecaEscura == 0){
			if(contadorCincoJogadas[0] != 1){
				contadorCincoJogadas[0] = 1;				
			}
			if(contadorCincoJogadas[0] == 1){
				contadorCincoJogadas[1] = contadorCincoJogadas[1]+1;
			}
		}
		//2 damas contra uma
		if(damaBranca == 2 && damaEscura == 1 && pecaBranca == 0 && pecaEscura == 0|| damaBranca == 1 && damaEscura == 2 && pecaBranca == 0 && pecaEscura == 0){
			if(contadorCincoJogadas[0] != 2){
				contadorCincoJogadas[0] = 2;
			}
			if(contadorCincoJogadas[0] == 2){
				contadorCincoJogadas[1] = contadorCincoJogadas[1]+1;
			}
		}
		//2 damas contra uma dama e uma pedra
		if(damaBranca == 2 && damaEscura == 1 && pecaBranca == 0 && pecaEscura == 1 || damaBranca == 1 && damaEscura  == 2 && pecaBranca == 1 && pecaEscura == 0){
			if(contadorCincoJogadas[0] != 3){
				contadorCincoJogadas[0] = 3;
			}
			if(contadorCincoJogadas[0] == 3){
				contadorCincoJogadas[1] = contadorCincoJogadas[1]+1;
			}
		}
		//uma dama contra uma dama
		if(damaBranca == 1 && damaEscura == 1 && pecaBranca == 0 && pecaEscura == 0){
			if(contadorCincoJogadas[0] != 4){
				contadorCincoJogadas[0] = 4;
			}
			if(contadorCincoJogadas[0] == 4){
				contadorCincoJogadas[1] = contadorCincoJogadas[1]+1;
			}		
		}
		//uma dama contra uma dama e uma pedra
		if(damaBranca <= 1 && damaEscura <= 1 && pecaBranca == 0 && pecaEscura == 1 || damaBranca <= 1 && damaEscura <=1 && pecaBranca == 1 && pecaEscura == 0){
			if(contadorCincoJogadas[0] != 5){
				contadorCincoJogadas[0] = 5;
			}
			if(contadorCincoJogadas[0] == 5){
				contadorCincoJogadas[1] = contadorCincoJogadas[1]+1;
			}
		}	
		
		if(contadorCincoJogadas[1] > 9){
			alguemVenceu = true;
			vencedor = "Empate";
		}
	}
	public void passarVez(){
		if(vez == 1){
			vez = 2;
		}
		else{
			vez = 1;
		}
	}
	public void ativarPosicionamentoManual(){
		this.posicionamentoManual = !posicionamentoManual;
	}
	public void posicionarPeca(String idPartida, int cor, int linha, String coluna) throws DamasException {


		String c;
		if(cor == 1){
			c= "branca";
		}
		else{
			c = "escura";
		}
		int linhaO = tabuleiro.traduzirLinha(linha);
		int colunaO = tabuleiro.traduzirColuna(coluna);
		
		VerificarSeVirouDama checar = new VerificarSeVirouDama(tabuleiro);
		
		tabuleiro.getTabuleiro().get(linhaO)[colunaO] = new Peca(c);
		
		if(checar.checar(linhaO, colunaO, c)){
			controleDeMovimento.virarDama(linhaO, colunaO, c);
		}
	}	
	public boolean isOcupada (String idPartida, int linha, String coluna) throws DamasException{
		
		int linhaT = tabuleiro.traduzirLinha(linha);
		int colunaT = tabuleiro.traduzirColuna(coluna.toUpperCase());
		
		if(tabuleiro.getTabuleiro().get(linhaT)[colunaT] != null){
			return true;
		}
		return false;
	}
	public boolean isDama(int linha, String coluna) throws DamasException{
		int linhaT = tabuleiro.traduzirLinha(linha);
		int colunaT = tabuleiro.traduzirColuna(coluna.toUpperCase());
		return tabuleiro.getTabuleiro().get(linhaT)[colunaT].isDama();

	}
	public void finalizarJogo(){
		alguemVenceu = true;
	}
	public String getIdPartida() {
		return idPartida;
	}
	public int getVez() {
		return vez;
	}
	public int getJogadorPedraBranca() {
		return jogadorPedraBranca;
	}
	public Jogador getJogador1() {
		return jogador1;
	}
	public Jogador getJogador2() {
		return jogador2;
	}
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	public boolean isSopro() {
		return sopro;
	}
	public String getRegraMovimento() {
		return regraMovimento;
	}
	public ControleDeMovimento getControleDeMovimento() {
		return controleDeMovimento;
	}
	public void setIdPartida(String idPartida) {
		this.idPartida = idPartida;
	}
	public void setVez(int vez) {
			this.vez = vez;
	}
	public void setJogadorPedraBranca(int jogadorPedraBranca) {
		this.jogadorPedraBranca = jogadorPedraBranca;
	}
	public void setJogador1(Jogador jogador1) {
		this.jogador1 = jogador1;
	}
	public void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}
	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	public void setSopro(boolean sopro) {
		this.sopro = sopro;
	}
	public void setRegraMovimento(String regraMovimento) {
		this.regraMovimento = regraMovimento;
	}
	public void setControleDeMovimento(ControleDeMovimento controleDeMovimento) {
		this.controleDeMovimento = controleDeMovimento;
	}
	public String getVencedor() {
		return vencedor;
	}
	public void setVencedor(String vencedor) {
		this.vencedor = vencedor;
	}
	public boolean isAlguemVenceu() {
		return alguemVenceu;
	}
	public void setAlguemVenceu(boolean alguemVenceu) {
		this.alguemVenceu = alguemVenceu;
	}	
}
