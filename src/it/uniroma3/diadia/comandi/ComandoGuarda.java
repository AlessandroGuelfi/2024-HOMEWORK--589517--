package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoGuarda extends AbstractComando{


	//	public ComandoGuarda(IO console) {
	//		this.console = console;
	//	
	//	}
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if(this.getParametro() != null && this.getParametro().equals("borsa"))
			this.getConsole().mostraMessaggio("Inventario: " + partita.getGiocatore().getBorsa().toString());
		else {
			this.getConsole().mostraMessaggio("Posizione: \n" + stanzaCorrente.getDescrizione());
			this.getConsole().mostraMessaggio("Cfu rimanenti: " + partita.getCfu() + "\n");
		}
	}


	@Override
	public String getNome() {
		return "guarda";
	}

}
