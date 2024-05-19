package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.giocatore.*;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "posa", "prendi", "guarda", "guarda borsa"};
	
	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	private Giocatore giocatore;
	private boolean finita;

	
	public Partita(){
		this(new Labirinto());
	}
	
	
	
	public Partita(Labirinto lab){
		this.labirinto = lab;
		this.finita = false;
		this.giocatore = new Giocatore();
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
	}

	public void setLabirinto(Labirinto lab) {
		this.labirinto = lab;
	}
	

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.stanzaCorrente == this.labirinto.getStanzaVincente();
	}

	public Labirinto getLabirinto() {
		return labirinto;
	}

	public Stanza getStanzaCorrente() {
		return stanzaCorrente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public int getCfu() {
		return this.giocatore.getCfu();
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	public static String[] getElencocomandi() {
		return elencoComandi;
	}	
	
}

