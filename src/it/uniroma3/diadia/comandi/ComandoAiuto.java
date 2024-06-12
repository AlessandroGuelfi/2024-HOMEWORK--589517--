package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
//	public ComandoAiuto(IO console) {
//		this.console = console;
//	}
	@Override
	public void esegui(Partita partita) {
		
			for(String elemento : Partita.getElencocomandi()) 
				this.getConsole().mostraMessaggio(elemento);
			this.getConsole().mostraMessaggio("");
	}

	@Override
	public String getNome() {
		return "aiuto";
	}
}
