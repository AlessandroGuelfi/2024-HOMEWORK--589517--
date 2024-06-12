package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class PartitaTest {
	private Partita p;
	private LabirintoBuilder lab;

	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.lab =  Labirinto.newBuilder("labirinto.txt");
	}

	@Test
	public void testisFinitaTrueStanzaCorrUgualeStanzaVinc() throws FileNotFoundException, FormatoFileNonValidoException {
		Labirinto labirinto = lab
				.addStanzaIniziale("inizio")
				.addStanzaVincente("inizio")
				.getLabirinto();
		this.p = new Partita(labirinto);
		assertTrue(this.p.getStanzaCorrente().equals(this.p.getLabirinto().getStanzaVincente()));
	}

	@Test
	public void testisFinitaFalseStanzaCorrDiversaStanzaVinc() {
		Labirinto labirinto = lab
				.addStanzaIniziale("inizio")
				.addStanzaVincente("fine")
				.getLabirinto();
		this.p = new Partita(labirinto);
		assertFalse(this.p.getStanzaCorrente().equals(this.p.getLabirinto().getStanzaVincente()));
	}

	@Test
	public void testisFinitaFalse0Cfu() {
		this.p = new Partita();
		this.p.getGiocatore().setCfu(0);
		assertTrue(this.p.isFinita());
	}
}

