package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class testComandoPosa {
	private Partita p;
	private Comando c;
	private IOConsole console;
	@Before
	public void setUp() throws Exception {
		console = new IOConsole();
		p = new Partita();
		c = new ComandoPosa(console);
		p.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("spada",10));
		
	}

	@Test
	public void testComandoPosaCorrettamenteNellaStanza() {
		c.setParametro("spada");
		c.esegui(p);
		assertTrue(p.getStanzaCorrente().hasAttrezzo("spada"));
	}
	@Test
	public void testComandoPosaCorrettamenteNonNellaBorsa() {
		c.setParametro("spada");
		c.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
	@Test
	public void testComandoPosaAttrezzoNonNellaBorsa() {
		c.setParametro("lama");
		c.esegui(p);
		assertFalse(p.getStanzaCorrente().hasAttrezzo("lama"));
	}
	
	
}
