package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	private Stanza stanzaBuia;
	private Attrezzo scimmia;
	
	@Before
	public void setUp() throws Exception {
		stanzaBuia = new StanzaBuia("Stanza buia", "Scimmia");
		scimmia = new Attrezzo("Scimmia",20);	
	}
	
	@Test
	public void testAttrezzoNonNellaStanza() {
		assertEquals("qui c'è un buio pesto", this.stanzaBuia.getDescrizione());
	}
	
	@Test
	public void testAttrezzoNellaStanza() {
		this.stanzaBuia.addAttrezzo(scimmia);
		assertEquals(this.stanzaBuia.toString(), this.stanzaBuia.getDescrizione());
	}
	
}