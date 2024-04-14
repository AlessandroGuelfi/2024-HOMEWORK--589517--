package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.*;

/**
 * La classe borsa si occupa di gestire gli attrezzi del giocatore
 * gli attrezzi sono contenuti in un'array e la borsa ha un limite di peso
 * @see Attrezzo
 */

public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; 
		this.numeroAttrezzi = 0;
	}
	
	/**
	 * Si occupa di cercare un attrezzo nella borsa
	 * @param attrezzo riferimento all'oggetto attrezzo cercato
	 * @return true se l'attrezzo cercato è nella borsa, false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		for(int i = 0; i<this.attrezzi.length; i++) 
			if(attrezzi[i] == null) {
				this.attrezzi[i] = attrezzo;
				this.numeroAttrezzi++;
				return true;
			}
		return false;
	}

	public int getPesoMax() {
		return pesoMax;
	}
	
	/**
	 * Si occupa di prendere l'attrezzo desiderato dalla borsa
	 * @param nomeAttrezzo nome dell'attrezzo 
	 * @return una copia del riferimento all'oggetto cercato all'interno della borsa
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.attrezzi.length; i++) {
			if(this.attrezzi[i] != null) 
				if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
					a = attrezzi[i];
		}
		return a;
	}

	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.attrezzi.length; i++)  {
			if(attrezzi[i] != null)
				peso += this.attrezzi[i].getPeso();
		}

		return peso;
	}

	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/**
	 * Si occupa di rimuovere un attrezzo dalla borsa
	 * @param nomeAttrezzo nome dell'attrezzo da rimuovere
	 * @return un riferimento all'oggetto rimosso dalla borsa
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for(int i = 0; i<this.attrezzi.length; i++) {
			if(this.attrezzi[i] != null) {
				if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {	
					a = attrezzi[i];
					attrezzi[i] = null;
					this.numeroAttrezzi--;
					break;
				}
			}
		}
		return a;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.attrezzi.length; i++) {
				if(attrezzi[i] != null)
					s.append(attrezzi[i].toString()+" ");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}