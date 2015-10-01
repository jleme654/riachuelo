package br.com.julio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Produtor extends Executa implements Job {
	
	private static Logger logger;
	private static Properties prop;
	private String minStr;
	private String maxStr;
	private Arquivo a1;
	private Arquivo a2;
	private Arquivo a3;
	private List<Arquivo> lista;
	
	public Produtor() throws IOException {
		logger = Logger.getLogger(Produtor.class.getName());
		
		prop = new Properties();
		String path = new File("config.properties").getAbsolutePath();
		FileInputStream in = new FileInputStream(path);  
        prop.load(in);  
        in.close(); 
		
        minStr = prop.getProperty("MIN_PAGES_PER_DOCUMENT");
		maxStr = prop.getProperty("MAX_PAGES_PER_DOCUMENT");
		
		a1 = new Arquivo("documento","doc", getRandomNumeroPaginas(minStr, maxStr));
		a2 = new Arquivo("planilha","xls", getRandomNumeroPaginas(minStr, maxStr));
		a3 = new Arquivo("foto","jpg", getRandomNumeroPaginas(minStr, maxStr));
		
		lista = new ArrayList<Arquivo>();
		lista.add(a1);
		lista.add(a2);
		lista.add(a3);
		
		//randomiza a lista com seus elementos
		Collections.shuffle(lista);
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("##### INICIO PRODUCAO IMPRESSORA-01 #####");
		
		for (Arquivo arquivo : lista) {
			System.out.println(arquivo);
			for (int i = 1; i <= arquivo.getPaginas(); i++) {
				System.out.println("....... Imprimindo pagina "+i);
			}
		}
		logger.info("##### FIM PRODUCAO IMPRESSORA-01 #####");
	}
	
	private int getRandomNumeroPaginas(String minStr, String maxStr){
		Random randomGenerator = new Random();
		int randomInt =0 ;
		System.out.println(minStr);
		int min = Integer.parseInt(minStr);
		int max = Integer.parseInt(maxStr);
	    for (int idx = min; idx <= max; idx++){
	      randomInt = randomGenerator.nextInt(max);
	    }
	    return randomInt;
	}
	
}
