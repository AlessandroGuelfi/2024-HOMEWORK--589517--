package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder  {
	
	private Stanza ultimaStanza;
	private Labirinto labirinto;
	private Map<String, Stanza> mappa;
	
	
	
	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.mappa = new HashMap<>();
	}
	
	public LabirintoBuilder addStanzaIniziale(String nome) {
		this.ultimaStanza = new Stanza(nome); 
		this.mappa.put(nome, this.ultimaStanza);
		this.labirinto.setStanzaIniziale(this.ultimaStanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nome) {
		if(this.mappa.containsKey(nome)) {
			this.labirinto.setStanzaVincente(this.mappa.get(nome));
		}
		else {
			this.mappa.put(nome, new Stanza(nome));
			this.labirinto.setStanzaVincente(this.mappa.get(nome));
			this.ultimaStanza = this.mappa.get(nome);
		}
		return this;
	}
	
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		this.ultimaStanza.addAttrezzo(new Attrezzo(nome,peso));
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String iniziale, String adiacente, String direzione) {
		if(this.mappa.get(iniziale).dammiDirezioni().size() == 4)
			return this;
		this.mappa.get(iniziale).impostaStanzaAdiacente(direzione, this.mappa.get(adiacente));
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
		this.ultimaStanza = new StanzaMagica(nome, soglia);
		this.mappa.put(nome, this.ultimaStanza);
		return this;
	}
	
	
	public LabirintoBuilder addStanzaBuia(String nome, String attrezzoVisib) {
		this.ultimaStanza = new StanzaBuia(nome, attrezzoVisib);
		this.mappa.put(nome, ultimaStanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nome, String direzione, String nomeChiave) {
		this.ultimaStanza = new StanzaBloccata(nome, direzione, nomeChiave);
		this.mappa.put(nome, ultimaStanza);
		return this;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public LabirintoBuilder addStanza(String nome) {
		this.ultimaStanza = new Stanza(nome);
		this.mappa.put(nome, ultimaStanza);
		return this;
	}

	public Map<String, Stanza> getListaStanze() {
		return this.mappa;
	}	
}