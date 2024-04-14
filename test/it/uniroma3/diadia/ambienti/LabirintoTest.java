package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabirintoTest {

	@Test
	public void testgetStanzaVincente() {
		Labirinto lab = new Labirinto();
		Stanza vincente = new Stanza("StanzaVincente");
		lab.setStanzaVincente(vincente);
		assertEquals(vincente,lab.getStanzaVincente());
	}
	
	@Test
	public void testgetStanzaVincenteNon() {
		Labirinto lab = new Labirinto();
		Stanza nonVincente = new Stanza("StanzaNonVincente");
		assertNotEquals(nonVincente,lab.getStanzaVincente());
	}

}
