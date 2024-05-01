package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private String direzioneBloccata;
	private String nomeAttrezzoChiave;
	
	
	public StanzaBloccata(String nome, String direzione, String nomeAttrezzo) {
		super(nome);
		this.direzioneBloccata = direzione;
		this.nomeAttrezzoChiave = nomeAttrezzo;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if(dir.equals(this.direzioneBloccata) && (!this.hasAttrezzo(this.nomeAttrezzoChiave)))
			return this;
		else
			return super.getStanzaAdiacente(dir);
	}
	
}
