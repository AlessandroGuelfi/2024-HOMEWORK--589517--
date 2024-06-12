package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractComando implements Comando {
	private String parametro;
	private IO console;
	
	public abstract void esegui(Partita partita);
	public abstract String getNome();
	
	
	public String getParametro() {
		return this.parametro;
	}
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}



	public IO getConsole() {
		return console;
	}



	public void setConsole(IO console) {
		this.console = console;
	}
}
