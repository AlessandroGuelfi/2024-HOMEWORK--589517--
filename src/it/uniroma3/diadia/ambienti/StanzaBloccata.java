package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Direzione;

public class StanzaBloccata extends Stanza {
	private Direzione direzioneBloccata;
	private String nomeAttrezzoChiave;
	
	
	public StanzaBloccata(String nome, Direzione direzione, String nomeAttrezzo) {
		super(nome);
		this.direzioneBloccata = direzione;
		this.nomeAttrezzoChiave = nomeAttrezzo;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione dir) {
		if(dir.equals(this.direzioneBloccata) && (!this.hasAttrezzo(this.nomeAttrezzoChiave)))
			return this;
		else
			return super.getStanzaAdiacente(dir);
	}
	
}
