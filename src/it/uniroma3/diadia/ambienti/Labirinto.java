package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.FormatoFileNonValidoException;
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
	
	public Labirinto() {
		
	}

	private Labirinto(String filePath) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto cLab = new CaricatoreLabirinto(filePath);
		cLab.carica();
		this.stanzaIniziale = cLab.getStanzaIniziale();
		this.stanzaVincente = cLab.getStanzaVincente();
	}
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
	
	public static LabirintoBuilder newBuilder(String filePath) throws FileNotFoundException, FormatoFileNonValidoException {
		return new LabirintoBuilder(filePath);
	}
	
	public static class LabirintoBuilder  {
		
		private Stanza ultimaStanza;
		private Labirinto labirinto;
		private Map<String, Stanza> mappa;
		
		
		
		public LabirintoBuilder(String filePath) throws FileNotFoundException, FormatoFileNonValidoException {
			this.labirinto = new Labirinto(filePath);
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
			this.ultimaStanza = new StanzaBloccata(nome, Direzione.valueOf(direzione.toUpperCase()), nomeChiave);
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
}