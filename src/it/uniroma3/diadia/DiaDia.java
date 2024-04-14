package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.giocatore.*;
import java.util.Scanner;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "posa", "prendi"};

	private Partita partita;
	private IOConsole console;

	public DiaDia() {
		this.partita = new Partita();
		this.console = new IOConsole();
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {
			istruzione = this.console.leggiRiga();
		}
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		if(istruzione.isEmpty()) return false;
		Comando comandoDaEseguire = new Comando(istruzione);
		if (comandoDaEseguire.getNome().equals("fine") || this.partita.getGiocatore().getCfu() == 0) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			this.console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			this.console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false; 
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			this.console.mostraMessaggio(elencoComandi[i]+" ");
		this.console.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			this.console.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(--cfu);
			this.console.mostraMessaggio("cfu rimanenti:" + cfu);
		}
		this.console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		if(this.partita.getCfu() == 0)
			this.console.mostraMessaggio("Non hai piu' cfu, ricomincia!\n");
		this.console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}
	/**
	 * Prende un attrezzo dalla stanza corrente, se non trova nessun attrezzo 
	 * invia un messaggio d'errore
	 * @param nomeAttrezzo nome dell'attrezzo che vuoi raccogliere
	 */
	public void prendi(String nomeAttrezzo) {
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		Giocatore player = this.partita.getGiocatore();
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
	/**
	 * Posa un attrezzo contenuto nella borsa del giocatore, se l'attrezzo non esiste invia un 
	 * messaggio d'errore
	 * 
	 * @param nomeAttrezzo nome dell'attrezzo
	 */
	public void posa(String nomeAttrezzo) {
		Giocatore player = this.partita.getGiocatore();
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		if(player.getBorsa().hasAttrezzo(nomeAttrezzo)) {
			if(stanzaCorrente.addAttrezzo(player.getBorsa().getAttrezzo(nomeAttrezzo))) {
				this.console.mostraMessaggio("Hai posato l'attrezzo "+ player.getBorsa().removeAttrezzo(nomeAttrezzo) + " nella stanza " + stanzaCorrente.getNome() + "\n");
				this.console.mostraMessaggio("Borsa: " + player.getBorsa().toString() + "\n"); 
			}
			else
				this.console.mostraMessaggio("La stanza e' piena, non puoi posare attrezzi all'interno\n");
		}
		else
			this.console.mostraMessaggio("Questo attrezzo non esiste\n");
	}


	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}

