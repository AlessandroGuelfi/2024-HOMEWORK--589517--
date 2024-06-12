package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public abstract class AbstractPersonaggio {
	private String nome;
	private String presentazione;
	private boolean haSalutato;
	private Attrezzo attrezzo;


	public AbstractPersonaggio(String nome, String presentazione, Attrezzo attrezzo) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
		this.attrezzo = attrezzo;
	}


	public String getNome() {
		return nome;
	}

	public String getPresentazione() {
		return presentazione;
	}

	public boolean haSalutato() {
		return this.haSalutato;
	}

	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao io sono ");
		risposta.append(this.getNome() + ".");
		if(!this.haSalutato())
			risposta.append(this.getPresentazione());
		else
			risposta.append("Ci siamo già presentati!");
		this.haSalutato = true;
		return risposta.toString();
	}


	abstract public String agisci(Partita partita);
	abstract public  String riceviRegalo(Attrezzo attrezzo, Partita partita);


	@Override
	public String toString() {
		return this.getNome();
	}


	public Attrezzo getAttrezzo() {
		return attrezzo;
	}
	
	public void setAttrezzo(Attrezzo attrezzo) {
		this.attrezzo = attrezzo;
	}
}
