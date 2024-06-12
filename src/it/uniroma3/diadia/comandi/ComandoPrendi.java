package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPrendi extends AbstractComando {
	
//	public ComandoPrendi(IO console) {
//		this.console = console;
//	}

	/**
	 * Prende un attrezzo dalla stanza corrente, se non trova nessun attrezzo 
	 * invia un messaggio d'errore
	 * @param nomeAttrezzo nome dell'attrezzo che vuoi raccogliere
	 */
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Giocatore player = partita.getGiocatore();
		if(stanzaCorrente.hasAttrezzo(this.getParametro())) {
			Attrezzo cercato = stanzaCorrente.getAttrezzo(this.getParametro());
			if(player.getBorsa().addAttrezzo(cercato)) {
				stanzaCorrente.removeAttrezzo(cercato);
				this.getConsole().mostraMessaggio("Attrezzo preso con successo\n");
			}
			else
				this.getConsole().mostraMessaggio("La borsa e' piena, posa degli oggetti per poterne prendere altri\n");
		}
		else 
			this.getConsole().mostraMessaggio("Questo attrezzo non esiste\n");
	}
	
	
	@Override
	public String getNome() {
		return "prendi";
	}

}
