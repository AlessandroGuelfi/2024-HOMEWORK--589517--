package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando{
//	public ComandoVai(IO console) {
//		this.console = console;
//	}
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		
		if(this.getParametro() == null) {
			this.getConsole().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione\n");
			return;
		}
		try {
			prossimaStanza = stanzaCorrente.getStanzaAdiacente(Direzione.valueOf(this.getParametro().toUpperCase()));
		} catch(Exception e) {
			prossimaStanza = null;
		}
		if(prossimaStanza ==  null) {
			this.getConsole().mostraMessaggio("Direzione inesistente\n");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		this.getConsole().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}

	@Override
	public String getNome() {
		return "vai";
	}
}
