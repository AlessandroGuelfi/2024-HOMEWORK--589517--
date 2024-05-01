package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPosa implements Comando {
	private String nomeAttrezzo;
	private IO console;
	
	public ComandoPosa(IO console) {
		this.console = console;
	}
	
	
	/**
	 * Posa un attrezzo contenuto nella borsa del giocatore, se l'attrezzo non esiste invia un 
	 * messaggio d'errore
	 * 
	 * @param nomeAttrezzo nome dell'attrezzo
	 */
	@Override
	public void esegui(Partita partita) {
		Giocatore player = partita.getGiocatore();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if(player.getBorsa().hasAttrezzo(nomeAttrezzo)) {
			if(stanzaCorrente.addAttrezzo(player.getBorsa().getAttrezzo(nomeAttrezzo))) {
				this.console.mostraMessaggio("Hai posato l'attrezzo "+ player.getBorsa().removeAttrezzo(nomeAttrezzo) + " nella stanza " 
																									+ stanzaCorrente.getNome() + "\n");
				this.console.mostraMessaggio("Borsa: " + player.getBorsa().toString() + "\n"); 
			}
			else
				this.console.mostraMessaggio("La stanza e' piena, non puoi posare attrezzi all'interno\n");
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
		return "posa";
	}


	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
