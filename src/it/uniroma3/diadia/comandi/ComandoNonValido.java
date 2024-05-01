package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	private IO console;
	
	public ComandoNonValido(IO console) {
		this.console = console;
	}
	
	@Override
	public void esegui(Partita partita) {
		this.console.mostraMessaggio("Comando non valido, digita aiuto per vedere i comandi\n");

	}
	
	public void setParametro(String parametro) {
	}

	@Override
	public String getNome() {
		return "non valido";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
