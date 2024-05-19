package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.*;

/**
 * Classe che si occupa di gestire la creazione del labirinto e di memorizzare la stanza iniziale
 * e quella vincente
 * @see Stanza
 * @see Attrezzo
 */


public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	public void setStanzaIniziale(Stanza stanzaCorrente) {
		this.stanzaIniziale = stanzaCorrente;
	}
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

}