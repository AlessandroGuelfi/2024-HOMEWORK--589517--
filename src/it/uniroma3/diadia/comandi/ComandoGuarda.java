package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoGuarda implements Comando {
	private IO console;
	
	public ComandoGuarda(IO console) {
		this.console = console;
	}
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		this.console.mostraMessaggio("Posizione: \n" + stanzaCorrente.getDescrizione());
		this.console.mostraMessaggio("Cfu rimanenti: " + partita.getCfu() + "\n");
	}

	@Override
	public void setParametro(String parametro) {
	}

	@Override
	public String getNome() {
		return "guarda";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
