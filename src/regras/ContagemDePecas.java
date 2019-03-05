package regras;

import componentes.Peca;
import componentes.Tabuleiro;

public class ContagemDePecas extends Regra {
	
	public ContagemDePecas(Tabuleiro tabuleiro){
		this.setTabuleiro(tabuleiro);
	}
	public Integer[] contar(){
		
		Integer[] somatorioPecas = {0,0,0,0};
		
		for(int i = 0; i < getTabuleiro().getTamanho(); i++){
			for( int j = 0; j < getTabuleiro().getTamanho(); j++){
				Peca peca = getTabuleiro().getTabuleiro().get(i)[j];				
				if(peca != null){
					//branca
					if(peca.getCor().equals("branca")){
						//dama branca
						if(peca.isDama()){
							somatorioPecas[0] = somatorioPecas[0]+1;
						} 
						//peça comum branca
						else{
							somatorioPecas[1] = somatorioPecas[1]+1;
						}
					}
					//escura
					else{
						//dama escura
						if(peca.isDama()){
							somatorioPecas[2] = somatorioPecas[2]+1;
						}
						//peca comum escura
						else{
							somatorioPecas[3] = somatorioPecas[3]+1;
						}						
					}					
				}
			}
		}
		return somatorioPecas;	
	}
}
