package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class testComandoPosa {
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
		p.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("spada",10));
		
	}

	@Test
	public void testComandoPosaCorrettamenteNellaStanza() {
		c = riflessiva.costruisciComando("posa spada");
		c.esegui(p);
		assertTrue(p.getStanzaCorrente().hasAttrezzo("spada"));
	}
	@Test
	public void testComandoPosaCorrettamenteNonNellaBorsa() {
		c = riflessiva.costruisciComando("posa spada");
		c.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
	@Test
	public void testComandoPosaAttrezzoNonNellaBorsa() {
		c = riflessiva.costruisciComando("posa lama");
		c.esegui(p);
		assertFalse(p.getStanzaCorrente().hasAttrezzo("lama"));
	}
	
	
}
