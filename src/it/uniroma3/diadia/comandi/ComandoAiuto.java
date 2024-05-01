package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	private IO console;
	public ComandoAiuto(IO console) {
		this.console = console;
	}
	@Override
	public void esegui(Partita partita) {
			for(String elemento : partita.getElencocomandi()) 
				this.console.mostraMessaggio(elemento);
			this.console.mostraMessaggio("");
	}

	@Override
	public void setParametro(String parametro) {
	}

	@Override
	public String getNome() {
		return "aiuto";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
