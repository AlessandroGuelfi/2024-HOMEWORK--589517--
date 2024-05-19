package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.*;

public class PartitaTest {
	private Partita p;
	private Labirinto lab;
	


	@Test
	public void testisFinitaTrueStanzaCorrUgualeStanzaVinc() {
		this.lab = new LabirintoBuilder()
				.addStanzaIniziale("inizio")
				.addStanzaVincente("inizio")
				.getLabirinto();
		this.p = new Partita(lab);
		assertTrue(this.p.getStanzaCorrente().equals(this.p.getLabirinto().getStanzaVincente()));
	}

	@Test
	public void testisFinitaFalseStanzaCorrDiversaStanzaVinc() {
		this.lab = new LabirintoBuilder()
				.addStanzaIniziale("inizio")
				.addStanzaVincente("fine")
				.getLabirinto();
		this.p = new Partita(lab);
		assertFalse(this.p.getStanzaCorrente().equals(this.p.getLabirinto().getStanzaVincente()));
	}

	@Test
	public void testisFinitaFalse0Cfu() {
		this.p = new Partita();
		this.p.getGiocatore().setCfu(0);
		assertTrue(this.p.isFinita());
	}
}

