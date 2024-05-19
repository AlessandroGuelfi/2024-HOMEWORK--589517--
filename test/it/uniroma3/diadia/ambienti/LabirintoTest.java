package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabirintoTest {

	@Test
	public void testgetStanzaVincente() {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaVincente("fine")
				.getLabirinto();
		assertEquals(new Stanza("fine"),lab.getStanzaVincente());
	}
	
	@Test
	public void testgetStanzaVincenteNon() {
		Labirinto lab = new LabirintoBuilder()
				.addStanzaVincente("fine")
				.getLabirinto();
		Stanza nonVincente = new Stanza("StanzaNonVincente");
		assertNotEquals(nonVincente,lab.getStanzaVincente());
	}

}
