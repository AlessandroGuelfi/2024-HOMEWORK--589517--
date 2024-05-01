package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPrendi implements Comando {
	private String nomeAttrezzo;
	private IO console;
	
	public ComandoPrendi(IO console) {
		this.console = console;
	}

	/**
	 * Prende un attrezzo dalla stanza corrente, se non trova nessun attrezzo 
	 * invia un messaggio d'errore
	 * @param nomeAttrezzo nome dell'attrezzo che vuoi raccogliere
	 */
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Giocatore player = partita.getGiocatore();
		if(stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
			Attrezzo cercato = stanzaCorrente.getAttrezzo(nomeAttrezzo);
			if(player.getBorsa().addAttrezzo(cercato)) {
				stanzaCorrente.removeAttrezzo(cercato);
				this.console.mostraMessaggio("Attrezzo preso con successo\n");
			}
			else
				this.console.mostraMessaggio("La borsa e' piena, posa degli oggetti per poterne prendere altri\n");
		}
		else 
			this.console.mostraMessaggio("Questo attrezzo non esiste\n");
	}
	
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;

	}


	@Override
	public String getNome() {
		return "prendi";
	}


	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
