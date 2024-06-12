package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfiguraStatoIniziale {
	public static Properties prop;
	
	
	public int getCfuIniziali() {
		if(prop == null)
			this.caricaFileSuProperties();
		return Integer.parseInt(prop.getProperty("cfu_iniziali"));
	}

	public int getPeso() {
		if(prop == null)
			this.caricaFileSuProperties();
		return Integer.parseInt(prop.getProperty("peso_max_borsa"));
	}
	

	public void caricaFileSuProperties() {
		prop = new Properties();
		try {
			Class<?> clazz = this.getClass();
			InputStream inputStream = clazz.getResourceAsStream("/diadia.properties");
			prop.load(inputStream);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}