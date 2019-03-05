package componentes;

import java.util.ArrayList;
import java.util.List;
 
import easyaccept.EasyAcceptFacade;

public class MainTest {
	public static void main(String[] args){		
		
		List<String> files = new ArrayList<String>();
		
		 files.add("CE01.txt");
		 files.add("CE02.txt");
		 files.add("CE03.txt");
		 files.add("CE04.txt");
		 files.add("CE05.txt");
		 files.add("US01.txt");
		 files.add("US02.txt");
		 files.add("US03.txt");
		 files.add("US04.txt");
		 files.add("US05.txt");
		 files.add("US06.txt");
		 files.add("US07.txt");
		 
		 Adapter adapter = new Adapter();
		 
		 EasyAcceptFacade eaFacade = new EasyAcceptFacade(adapter, files);
		
         eaFacade.executeTests();
         System.out.println(eaFacade.getCompleteResults());
			
	}
}
