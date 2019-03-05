package componentes;

import java.util.Scanner;

import Excecoes.DamasException;
import entradaESAida.Impressora;
import entradaESAida.Saida;

public class Main {

	public static void main(String[] args) throws DamasException {
		
		PersistenciaPartidas persistencia = PersistenciaPartidas.getInstance();
		Partida partida = null;
		Jogo jogo = persistencia.recuperarPartidas();
		Scanner ler = new Scanner(System.in);
		String resp;
		
		while(true){
			System.out.println("Voc� quer continuar uma partida?(s ou n)");
			resp = ler.nextLine();
			if(resp.equals("s") || resp.equals("n")){
				break;
			}
			else{
				System.out.println("Inv�lido!");
			}
		}	
		
		//recuperando partida
		boolean chave = true;
		boolean chave2 = true;
		if(resp.equals("s")){			
			
			
			while(chave){
				
				System.out.println("Digite o id da partida q quer recuperar");			
				String id = ler.nextLine();
				
				if(id == null || id.equals("")){
					System.out.println("id inv�lido");
					chave2 = false;
				}
				else{
					partida = jogo.buscarPartida(id);
					if(partida == null){
						System.out.println("Essa partida n�o existe");
						chave2 = false;
					}
					else{
						System.out.println("Carregando partida");
						chave = false;
					}
				}
				if(chave2 == false){
					System.out.println("Quer continuar a pesquisar ?(s ou n)");			
					id = ler.nextLine();
					if(id.equals("s")){
						chave2 = true;
					}
					else{
						chave = false;
					}
				}
			}
		}
		
		//criando nova partida
		
		if(partida == null){

			partida = new Partida();
			
			System.out.println("Criando nova partida");
			
			String idPartida;
			String jogador1;
			String jogador2;
			int jogadorPedraBranca = 0;
			String regraMovimento;
			String temp;
			String idTabuleiro;
			int tamanho = 0;
			
			while(true){
				System.out.println("Digite o id da partida");
				idPartida = ler.nextLine();
				if(idPartida == null || idPartida.equals("")){
					System.out.println("Inv�lido!");
				}
				else{
					break;
				}
			}			
			while(true){
				System.out.println("Digite o nome do jogador1");
				jogador1 = ler.nextLine();
				if(jogador1 == null || jogador1.equals("")){
					System.out.println("Inv�lido!");
				}
				else{
					break;
				}
			}			
			while(true){
				System.out.println("Digite o nome do jogador2");
				jogador2 = ler.nextLine();
				if(jogador2 == null || jogador2.equals("")){
					System.out.println("Inv�lido!");
				}
				else{
					break;
				}
			}
			while(true){
				try {
					System.out.println("Digite quem utilizar� as pecas brancas(1 ou 2)");
					jogadorPedraBranca = Integer.parseInt(ler.nextLine());
				} catch (Exception e) {}
				if(jogadorPedraBranca == 1 || jogadorPedraBranca == 2){
					break;
				}
				System.out.println("Inv�lido!");
			}			
			while(true){
				System.out.println("Qual regra de movimenta��o utilizar�?(regraMovimento1 ou regraMovimento2) regraMovimento1 - pe�as normais podem andar apenas pra frente, regraMovimento2 - pe�as normais podem andar para qualquer lado");
				regraMovimento = ler.nextLine();
				if(regraMovimento == null || regraMovimento.equals("")){
					System.out.println("Inv�lido!");
				}
				else if(regraMovimento.equals("regraMovimento1") || regraMovimento.equals("regraMovimento2")){
					break;
				}
				else{
					System.out.println("Inv�lido!");
				}
			}
			while(true){
				System.out.println("Com sopro ou sem?(n - sem sopro, s - com sopro)");
				temp = ler.nextLine();
				temp.toLowerCase();
				if(temp == null || temp.equals("")){
					System.out.println("Inv�lido!");
				}
				else if(temp.equals("s") || temp.equals("n")){
					break;
				}
				else{
					System.out.println("Inv�lido!");
				}
			}			
			boolean permiteSopro = false;
			if(temp.equals("s")){
				permiteSopro = true;
			}			
			while(true){
				System.out.println("Digite o id pro tabuleiro");
				idTabuleiro = ler.nextLine();
				if(idTabuleiro == null || idTabuleiro.equals("")){
					System.out.println("Inv�lido!");
				}
				else{
					break;
				}
			}
			while(true){
				try {
					System.out.println("Digite o tamanho do tabuleiro");				
					tamanho = Integer.parseInt(ler.nextLine());
				} catch (Exception e) {}
				if(tamanho >= 6 && tamanho <= 26){
					break;
				}
				System.out.println("Inv�lido!");
			}			
			jogo.criarTabuleiro(idTabuleiro, tamanho);			
			partida = jogo.iniciarPartida(idPartida, idTabuleiro, jogador1, jogador2, jogadorPedraBranca, regraMovimento, permiteSopro);		
		}	
		
		//iniciando jogo
		Saida saida = new Saida(partida.getTabuleiro());
		Impressora impressora = new Impressora();
		impressora.impress�o(saida);
		boolean chave3 = false;
		
		while(!partida.isAlguemVenceu()) {			
			
			int numeroJogador = 0;
			String coordenada;
			int linhaOrigem = 0;
			String colunaOrigem = null;
			int linhaDestino = 0;
			String colunaDestino = null;
			boolean sair = false;
			
			if(partida.getVez() == partida.getJogador1().getNumeroDoJogador()){
				System.out.println("Vez Do jogador: " + partida.getJogador1().getNome() + "\nCor da Peca: " + partida.getJogador1().getCorDaPeca());
				numeroJogador = partida.getJogador1().getNumeroDoJogador();
			}
			else{
				System.out.println("Vez Do jogador: " + partida.getJogador2().getNome() + "\nCor da Peca: " + partida.getJogador2().getCorDaPeca());
				numeroJogador = partida.getJogador2().getNumeroDoJogador();
			}
			
			while(true){
				System.out.println("Digite as coordenadas da pe�a que deseja mover - n�mero da linha e depois letra da coluna(ex: 1A)"
				+"\nou digite 'sair', para encerrar o jogo.");
				coordenada = ler.nextLine();
						
				if(coordenada.equals("sair")){
					chave3 = true;
					sair = true;
					break;
				}
				try {
					linhaOrigem = Integer.parseInt(""+coordenada.charAt(0));
					colunaOrigem = ""+coordenada.charAt(1);
					break;
				}
				catch (Exception e) {
					System.out.println("Inv�lido!");
				}
			}
			if(sair){
				break;
			}
			while(true){
				System.out.println("Digite as coordenadas do destino da pe�a - n�mero da linha e depois letra da coluna(ex: 1A)"
				+"\nou digite 'sair', para encerrar o jogo.");			
				coordenada = ler.nextLine();
						
				if(coordenada.equals("sair")){
					chave3 = true;
					sair = true;
					break;
				}
				try {
					linhaDestino = Integer.parseInt(""+coordenada.charAt(0));
					colunaDestino = ""+coordenada.charAt(1);
					break;
				} 
				catch (Exception e) {
					System.out.println("Inv�lido!");
				}						
			}
			if(sair){
				break;	
			}
			try {
				partida.movimentoJogador(numeroJogador, linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
			} catch (DamasException e) {
				System.out.println(e.getMessage());
			}
			
			impressora.impress�o(saida);
		}
		//Pergunta ao usu�rio se ele deseja salvar a partida
		if(chave3 == true){
			System.out.println("Voc� deseja salvar a partida?(s ou n)");
			resp = ler.nextLine();
			if(resp.equals("s")){
				jogo.adicionarPartida(partida);
				System.out.println("Partida salva!");
			}
			else{
				System.out.println("Partida n�o foi salva");
			}
			persistencia.salvarPartidas(jogo);			
			System.out.println("Saindo...");
		}
		//verifica se alguem venceu
		if(partida.isAlguemVenceu()){
			System.out.println(partida.getVencedor() + " Venceu!!!!!");
		}	
	}
}
