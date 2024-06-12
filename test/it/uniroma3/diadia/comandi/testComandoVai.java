package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class testComandoVai {
	private Partita p;
	private Comando c;
	private IOConsole console;
	private LabirintoBuilder lab;
	private FabbricaDiComandi riflessiva;
	
	
	@Before
	public void setUp() throws Exception {
		this.console = new IOConsole(new Scanner(System.in));
		riflessiva = new FabbricaDiComandiRiflessiva(console);
		this.lab = Labirinto.newBuilder("labirinto.txt");
	}

	@Test
	public void testVaiNord() {
		Labirinto labirinto = this.lab
				.addStanzaIniziale("inizio")
				.addStanza("adiacente")
				.addAdiacenza("inizio", "adiacente", "nord")
				.getLabirinto();
		this.p = new Partita(labirinto);
		Stanza stanza = this.p.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("nord".toUpperCase()));
		c = riflessiva.costruisciComando("vai nord");
		c.esegui(this.p);
		assertEquals(stanza, this.p.getStanzaCorrente());
	}
	
	@Test
	public void testVaiStanzaVuota() {
		Labirinto labirinto = this.lab
				.addStanzaIniziale("inizio")
				.getLabirinto();
		this.p = new Partita(labirinto);
		Stanza stanza = this.p.getStanzaCorrente();
		c = riflessiva.costruisciComando("vai ahfhdkj");
		c.esegui(this.p);
		assertEquals(stanza, this.p.getStanzaCorrente());
	}
	
	@Test
	public void testVaiSud() {
		Labirinto labirinto = this.lab
				.addStanzaIniziale("inizio")
				.addStanza("adiacente")
				.addAdiacenza("inizio", "adiacente", "sud")
				.getLabirinto();
		this.p = new Partita(labirinto);
		Stanza stanza = this.p.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("sud".toUpperCase()));
		c = riflessiva.costruisciComando("vai sud");
		c.esegui(this.p);
		assertEquals(stanza, this.p.getStanzaCorrente());
	}
	
	
	@Test
	public void testVaiDueStanzaStessaDirezione() {
		Labirinto labirinto = this.lab
				.addStanzaIniziale("inizio")
				.addStanza("adiacente")
				.addAdiacenza("inizio", "adiacente", "sud")
				.addStanza("finale")
				.addAdiacenza("adiacente", "finale", "sud")
				.getLabirinto();
		this.p = new Partita(labirinto);
		Stanza stanza = this.p.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("sud".toUpperCase())).getStanzaAdiacente(Direzione.valueOf("sud".toUpperCase()));
		c = riflessiva.costruisciComando("vai sud");
		c.esegui(this.p);
		c.esegui(this.p);
		assertEquals(stanza, this.p.getStanzaCorrente());
	}
	
	@Test
	public void testVaiInStanzaBloccataSenzaOggetto() {
		Labirinto labirinto = this.lab
				.addStanzaIniziale("inizio")
				.addStanzaBloccata("bloccata", "nord", "chiave")
				.addAdiacenza("inizio", "bloccata", "sud")
				.getLabirinto();
		this.p = new Partita(labirinto);
		Stanza stanza = this.p.getStanzaCorrente();
		c = riflessiva.costruisciComando("vai sud");
		c.esegui(this.p);
		c = riflessiva.costruisciComando("vai nord");
		c.esegui(this.p);
		assertFalse(stanza.equals(this.p.getStanzaCorrente()));
	}
	
	@Test
	public void testVaiInStanzaBloccataConOggetto() {
		Labirinto labirinto = this.lab
				.addStanzaIniziale("inizio")
				.addStanzaBloccata("bloccata", "nord", "chiave")
				.addAdiacenza("inizio", "bloccata", "sud")
				.addAttrezzo("chiave", 5)
				.addAdiacenza("bloccata", "inizio", "nord")
				.getLabirinto();
		this.p = new Partita(labirinto);
		Stanza stanza = this.p.getStanzaCorrente();
		c = riflessiva.costruisciComando("vai sud");
		c.esegui(this.p);
		c = riflessiva.costruisciComando("vai nord");
		c.esegui(this.p);
		assertTrue(stanza.equals(this.p.getStanzaCorrente()));
	}
	
	@Test
	public void testVaiInStanzaVincente() {
		Labirinto labirinto = this.lab
				.addStanzaIniziale("inizio")
				.addStanzaVincente("fine")
				.addAdiacenza("inizio", "fine", "est")
				.getLabirinto();
		this.p = new Partita(labirinto);
		Stanza stanza = this.p.getLabirinto().getStanzaVincente();
		c = riflessiva.costruisciComando("vai est");
		c.esegui(this.p);
		assertEquals(stanza, this.p.getStanzaCorrente());
	}
	
	@Test
	public void testVaiInStanzaBuia() {
		Labirinto labirinto = this.lab
				.addStanzaIniziale("inizio")
				.addStanzaBuia("buia", "lanterna")
				.addAdiacenza("inizio", "buia", "nord")
				.getLabirinto();
		this.p = new Partita(labirinto);
		String messaggioModificatore = "qui c'è un buio pesto";
		c = riflessiva.costruisciComando("vai nord");
		c.esegui(this.p);
		assertEquals(messaggioModificatore, this.p.getStanzaCorrente().getDescrizione());
	}

}
