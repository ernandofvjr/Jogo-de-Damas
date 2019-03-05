package entradaESAida;

import java.util.ArrayList;
import componentes.Peca;

public class Impressora {
	
	private String[] listaAlfa = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	private String linhaAlfa(int tamanho){
		String linha = "";		
		linha += "     ";
		for(int i = 0; i < tamanho; i++){
			linha += listaAlfa[i]+"  ";
		}
		linha += "\n\n";		
		return linha;
	}
	

	/**
	 * imprime o tabuleiro de acordo com o array de peças
	 * @param saida
	 */
	public void impressão(Saida saida){
		
		ArrayList<Peca[]> tabuleiro = saida.getEstadoAtual();
		boolean cor = true;
		int var = tabuleiro.size()+1;	
		
		for(Peca[] linha : tabuleiro){
			
			if(var == linha.length+1){
				System.out.print(linhaAlfa(linha.length));
			}
			
			var --;
			if(cor == false){
				cor = true;
			}
			else{
				cor = false;
			}
			
			System.out.print(String.format("%02d", var) + "  ");
			
			for(Peca p : linha){
				
				if(p != null){
					if(p.getCor().equals("branca")){
						if(p.isDama()){
							System.out.print(" B ");
						}
						else{
							System.out.print(" b ");
						}
						
					}
					else{
						if(p.isDama()){
							System.out.print(" P ");
						}
						else{
							System.out.print(" p ");
						}						
					}
				}
				else{
					if(cor == false){
						System.out.print(" - ");
					}
					else{
						System.out.print(" * ");						
					}
				}
				if(cor == false){
					cor = true;
				}
				else{
					cor = false;
				}
			}
			
			System.out.print("  "+String.format("%02d", var));
			System.out.print("\n");
			
			if(var == 1){				
				System.out.print("\n"+linhaAlfa(linha.length));
			}
		}
	}
}
