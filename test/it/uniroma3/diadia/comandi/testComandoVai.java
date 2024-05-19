package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.ambienti.Stanza;

public class testComandoVai {
	private Partita p;
	private Comando c;
	private IOConsole console;
	private Labirinto lab;
	
	
	@Before
	public void setUp() throws Exception {
		this.console = new IOConsole();
		c = new ComandoVai(this.console);
	}

	@Test
	public void testVaiNord() {
		lab = new LabirintoBuilder()
				.addStanzaIniziale("inizio")
				.addStanza("adiacente")
				.addAdiacenza("inizio", "adiacente", "nord")
				.getLabirinto();
		this.p = new Partita(lab);
		Stanza stanza = this.p.getStanzaCorrente().getStanzaAdiacente("nord");
		c.setParametro("nord");
		c.esegui(this.p);
		assertEquals(stanza, this.p.getStanzaCorrente());
	}
	
	@Test
	public void testVaiStanzaVuota() {
		lab = new LabirintoBuilder()
				.addStanzaIniziale("inizio")
				.getLabirinto();
		this.p = new Partita(lab);
		Stanza stanza = this.p.getStanzaCorrente();
		c.setParametro("fjghf");
		c.esegui(this.p);
		assertEquals(stanza, this.p.getStanzaCorrente());
	}
	
	@Test
	public void testVaiSud() {
		lab = new LabirintoBuilder()
				.addStanzaIniziale("inizio")
				.addStanza("adiacente")
				.addAdiacenza("inizio", "adiacente", "sud")
				.getLabirinto();
		this.p = new Partita(lab);
		Stanza stanza = this.p.getStanzaCorrente().getStanzaAdiacente("sud");
		c.setParametro("sud");
		c.esegui(this.p);
		assertEquals(stanza, this.p.getStanzaCorrente());
	}
	
	
	@Test
	public void testVaiDueStanzaStessaDirezione() {
		lab = new LabirintoBuilder()
				.addStanzaIniziale("inizio")
				.addStanza("adiacente")
				.addAdiacenza("inizio", "adiacente", "sud")
				.addStanza("finale")
				.addAdiacenza("adiacente", "finale", "sud")
				.getLabirinto();
		this.p = new Partita(lab);
		Stanza stanza = this.p.getStanzaCorrente().getStanzaAdiacente("sud").getStanzaAdiacente("sud");
		c.setParametro("sud");
		c.esegui(this.p);
		c.esegui(this.p);
		assertEquals(stanza, this.p.getStanzaCorrente());
	}
	
	@Test
	public void testVaiInStanzaBloccataSenzaOggetto() {
		lab = new LabirintoBuilder()
				.addStanzaIniziale("inizio")
				.addStanzaBloccata("bloccata", "nord", "chiave")
				.addAdiacenza("inizio", "bloccata", "sud")
				.getLabirinto();
		this.p = new Partita(lab);
		Stanza stanza = this.p.getStanzaCorrente();
		c.setParametro("sud");
		c.esegui(this.p);
		c.setParametro("nord");
		c.esegui(this.p);
		assertFalse(stanza.equals(this.p.getStanzaCorrente()));
	}
	
	@Test
	public void testVaiInStanzaBloccataConOggetto() {
		lab = new LabirintoBuilder()
				.addStanzaIniziale("inizio")
				.addStanzaBloccata("bloccata", "nord", "chiave")
				.addAdiacenza("inizio", "bloccata", "sud")
				.addAttrezzo("chiave", 5)
				.addAdiacenza("bloccata", "inizio", "nord")
				.getLabirinto();
		this.p = new Partita(lab);
		Stanza stanza = this.p.getStanzaCorrente();
		c.setParametro("sud");
		c.esegui(this.p);
		c.setParametro("nord");
		c.esegui(this.p);
		assertTrue(stanza.equals(this.p.getStanzaCorrente()));
	}
	
	@Test
	public void testVaiInStanzaVincente() {
		lab = new LabirintoBuilder()
				.addStanzaIniziale("inizio")
				.addStanzaVincente("fine")
				.addAdiacenza("inizio", "fine", "est")
				.getLabirinto();
		this.p = new Partita(lab);
		Stanza stanza = this.p.getLabirinto().getStanzaVincente();
		c.setParametro("est");
		c.esegui(this.p);
		assertEquals(stanza, this.p.getStanzaCorrente());
	}
	
	@Test
	public void testVaiInStanzaBuia() {
		lab = new LabirintoBuilder()
				.addStanzaIniziale("inizio")
				.addStanzaBuia("buia", "lanterna")
				.addAdiacenza("inizio", "buia", "nord")
				.getLabirinto();
		this.p = new Partita(lab);
		String messaggioModificatore = "qui c'è un buio pesto";
		c.setParametro("nord");
		c.esegui(this.p);
		assertEquals(messaggioModificatore, this.p.getStanzaCorrente().getDescrizione());
	}

}
