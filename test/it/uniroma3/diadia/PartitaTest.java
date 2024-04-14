package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Test;

public class PartitaTest {
	private Partita p;


	@Test
	public void testisFinitaTrueStanzaCorrUgualeStanzaVinc() {
		this.p = new Partita();
		this.p.getLabirinto().setStanzaVincente(this.p.getStanzaCorrente());;
		assertTrue(this.p.isFinita());
	}

	@Test
	public void testisFinitaFalseStanzaCorrDiversaStanzaVinc() {
		this.p = new Partita();
		assertFalse(this.p.isFinita());
	}

	@Test
	public void testisFinitaFalse0Cfu() {
		this.p = new Partita();
		this.p.getGiocatore().setCfu(0);
		assertTrue(this.p.isFinita());
	}
}

