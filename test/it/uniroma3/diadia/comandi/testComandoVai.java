package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class testComandoVai {
	private Partita p;
	private Comando c;
	private IOConsole console;
	
	
	@Before
	public void setUp() throws Exception {
		this.p = new Partita();
		this.console = new IOConsole();
		c = new ComandoVai(this.console);
	}

	@Test
	public void testVaiNord() {
		Stanza stanza = this.p.getStanzaCorrente().getStanzaAdiacente("nord");
		c.setParametro("nord");
		c.esegui(this.p);
		assertEquals(stanza, this.p.getStanzaCorrente());
	}
	
	@Test
	public void testVaiStanzaVuota() {
		Stanza stanza = this.p.getStanzaCorrente();
		c.setParametro("fjghf");
		c.esegui(this.p);
		assertEquals(stanza, this.p.getStanzaCorrente());
	}
	
	@Test
	public void testVaiSud() {
		Stanza stanza = this.p.getStanzaCorrente().getStanzaAdiacente("sud");
		c.setParametro("sud");
		c.esegui(this.p);
		assertEquals(stanza, this.p.getStanzaCorrente());
	}

}
