package it.uniroma3.diadia.giocatore;

/**
 * La classe giocatore si occupa di gestire i cfu del giocatore, i cfu servono per muoversi
 * tra le stanze del labirinto, inoltre il giocatore ha una borsa dove può mettere i suoi oggetti
 * 
 * @see Borsa
 */

public class Giocatore {
	static final private int CFU_INIZIALI = 20;

	private int cfu;
	private Borsa borsa;

	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}

	public int getCfu() {
		return cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public Borsa getBorsa() {
		return borsa;
	}

	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}
}	
