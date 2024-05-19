package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoGuarda implements Comando {
	private IO console;
	private String oggetto;
	
	public ComandoGuarda(IO console) {
		this.console = console;
	
	}
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if(this.oggetto != null && this.oggetto.equals("borsa"))
			this.console.mostraMessaggio("Inventario: " + partita.getGiocatore().getBorsa().toString());
		else {
			this.console.mostraMessaggio("Posizione: \n" + stanzaCorrente.getDescrizione());
			this.console.mostraMessaggio("Cfu rimanenti: " + partita.getCfu() + "\n");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.oggetto = parametro;
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
