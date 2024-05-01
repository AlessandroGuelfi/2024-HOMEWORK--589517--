package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	private IO console;
	public ComandoFine(IO console) {
		this.console = console;
	}
	
	@Override
	public void esegui(Partita partita) {
		if(partita.getCfu() == 0)
			this.console.mostraMessaggio("Hai esaurito i CFU...");
		this.console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}

	@Override
	public void setParametro(String parametro) {
	}

	@Override
	public String getNome() {
		return "fine";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
