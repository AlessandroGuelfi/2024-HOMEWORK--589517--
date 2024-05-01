package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class testComandoPrendi {
	private Partita p;
	private Comando c;
	private IOConsole console;
	@Before
	public void setUp() throws Exception {
		console = new IOConsole();
		p = new Partita();
		c = new ComandoPrendi(console);
		p.getStanzaCorrente().addAttrezzo(new Attrezzo("spada",10));
	}

	@Test
	public void testAttrezzoNellaStanza() {
		c.setParametro("spada");
		c.esegui(p);
		assertFalse(p.getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	@Test
	public void testAttrezzoNellaBorsa() {
		c.setParametro("spada");
		c.esegui(p);
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
	
	@Test
	public void testAttrezzoNonNellaStanza() {
		c.setParametro("lama");
		c.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("lama"));
	}

}
