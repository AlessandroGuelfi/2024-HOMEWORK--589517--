package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtectedTest {
	private StanzaProtected stanzaMagica;
	private Attrezzo spada;
	private Attrezzo lama;
	private Attrezzo carta;
	
	
	@Before
	public void setUp() throws Exception {
		this.stanzaMagica = new StanzaMagicaProtected("Stanza Magica Protetta",2);
		this.spada = new Attrezzo("spada",20);
		this.lama = new Attrezzo("lama",15);
		this.carta = new Attrezzo("carta",2);
	}

	
	@Test
	public void testAddAttrezzoNormale() {
	this.stanzaMagica.addAttrezzo(this.spada);
	assertTrue(stanzaMagica.hasAttrezzo(spada.getNome()));
	}
	
	@Test
	public void testAddAttrezzoMagico() {
	this.stanzaMagica.addAttrezzo(this.spada);
	this.stanzaMagica.addAttrezzo(this.lama);
	this.stanzaMagica.addAttrezzo(this.carta);
	assertTrue(stanzaMagica.hasAttrezzo("atrac"));
	}
	
	@Test
	public void testAddSecondoAttrezzoMagico() {
	this.stanzaMagica.addAttrezzo(this.spada);
	this.stanzaMagica.addAttrezzo(this.lama);
	this.stanzaMagica.addAttrezzo(this.carta);
	Attrezzo palla = new Attrezzo("palla",5);
	this.stanzaMagica.addAttrezzo(palla);
	assertTrue(stanzaMagica.hasAttrezzo("allap"));
	}
	
	@Test
	public void testAddSecondoAttrezzoMagicoNonTrovaOriginale() {
	this.stanzaMagica.addAttrezzo(this.spada);
	this.stanzaMagica.addAttrezzo(this.lama);
	this.stanzaMagica.addAttrezzo(this.carta);
	Attrezzo palla = new Attrezzo("palla",5);
	this.stanzaMagica.addAttrezzo(palla);
	assertFalse(stanzaMagica.hasAttrezzo("palla"));
	}
}
