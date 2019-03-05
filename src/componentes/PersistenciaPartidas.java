package componentes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PersistenciaPartidas {
	private XStream xstream = new XStream(new DomDriver("UTF-8"));
	private File arquivo = new File("partidas.xml");
	/**
	 * salva o objeto de aprtidas salvas em xml
	 * @param partidas
	 */
	
	private static PersistenciaPartidas uniqueInstance;

	private PersistenciaPartidas() {
	}

	public static synchronized PersistenciaPartidas getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new PersistenciaPartidas();

		return uniqueInstance;
	}
	
	public void salvarPartidas(Jogo jogo){
		String xml = xstream.toXML(jogo);
		try {
			arquivo.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivo, "UTF-8");
			gravar.print(xml);
			gravar.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * recupera um objeto de partidas salvas do xml
	 * @return partidasSalvas
	 */
	public Jogo recuperarPartidas(){
		
		try {
			if(arquivo.exists()){
				FileInputStream fis = new FileInputStream(arquivo);					
				return (Jogo) xstream.fromXML(fis);			
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new Jogo();
	}
}
