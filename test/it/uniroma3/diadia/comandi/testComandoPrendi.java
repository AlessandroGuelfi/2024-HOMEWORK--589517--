package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class testComandoPrendi {
	private Partita p;
	private Comando c;
	private IOConsole console;
	private Labirinto lab;
	private FabbricaDiComandi riflessiva;
	@Before
	public void setUp() throws Exception {
		console = new IOConsole(new Scanner(System.in));
		lab = Labirinto.newBuilder("labirinto.txt")
				.addStanzaIniziale("inizio")
				.getLabirinto();
		p = new Partita(lab);
		riflessiva = new FabbricaDiComandiRiflessiva(console);
		p.getStanzaCorrente().addAttrezzo(new Attrezzo("spada",10));
	}

	@Test
	public void testAttrezzoNellaStanza() {
		c = riflessiva.costruisciComando("prendi spada");
		c.esegui(p);
		assertFalse(p.getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	@Test
	public void testAttrezzoNellaBorsa() {
		c = riflessiva.costruisciComando("prendi spada");
		c.esegui(p);
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
	
	@Test
	public void testAttrezzoNonNellaStanza() {
		c = riflessiva.costruisciComando("prendi lama");
		c.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("lama"));
	}

}
