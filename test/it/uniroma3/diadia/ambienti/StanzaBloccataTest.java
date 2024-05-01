package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	private Stanza stanzaBloccata;
	private Stanza stanza;
	private Attrezzo chiave;
	@Before
	public void setUp() throws Exception {
		this.stanza = new Stanza("Stanza Normale");
		this.stanzaBloccata = new StanzaBloccata("Stanza bloccata", "nord", "chiave");
		this.chiave = new Attrezzo("chiave", 4);
		
	}

	@Test
	public void testStanzaBloccataAttrezzoPresente() {
		this.stanzaBloccata.addAttrezzo(chiave);
		Stanza stanzaCorrente;
		this.stanzaBloccata.impostaStanzaAdiacente("nord", stanza);
		stanzaCorrente = this.stanzaBloccata.getStanzaAdiacente("nord");
		assertEquals(stanza, stanzaCorrente);
	}
	
	@Test
	public void testStanzaBloccataAttrezzoNonPresente() {
		Stanza stanzaCorrente;
		this.stanzaBloccata.impostaStanzaAdiacente("nord", stanza);
		stanzaCorrente = this.stanzaBloccata.getStanzaAdiacente("nord");
		assertEquals(stanzaBloccata, stanzaCorrente);
	}
	
	@Test
	public void testStanzaBloccataStanzaAdiacenteNonBloccata() {
		Stanza stanzaCorrente;
		this.stanzaBloccata.impostaStanzaAdiacente("sud", stanza);
		stanzaCorrente = this.stanzaBloccata.getStanzaAdiacente("sud");
		assertEquals(stanza, stanzaCorrente);
	}
	
	

}
