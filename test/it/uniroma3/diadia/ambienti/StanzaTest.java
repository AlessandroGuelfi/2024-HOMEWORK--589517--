package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;


import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {


	@Test
	public void testgetStanzaAdiacenteSoloNord() {
		Stanza stanza = new Stanza("Stanza");
		Stanza stanzaAdiacente = new Stanza ("StanzaAdiacente");
		stanza.impostaStanzaAdiacente("nord",stanzaAdiacente);
		assertEquals("StanzaAdiacente",stanza.getStanzaAdiacente("nord").getNome());
	}

	@Test
	public void testgetAttrezzoTrue() {
		Stanza atrio = new Stanza("atrio");
		atrio.addAttrezzo(new Attrezzo("Piccone",1));
		assertNotNull(atrio.getAttrezzo("Piccone"));
	}
	@Test
	public void testgetAttrezzoFalse() {
		Stanza atrio = new Stanza("atrio");
		assertNull(atrio.getAttrezzo("spada"));
	}
	@Test
	public void testgetAttrezzoSuStanzaVuota() {
		Stanza stanzaVuota = new Stanza("stanzaVuota");
		assertNull(stanzaVuota.getAttrezzo("spada"));
	}

	@Test
	public void testAddAttrezzoFalseLimiteDiAttrezziContenuti() {
		Stanza atrio = new Stanza("atrio");
		atrio.setNumeroAttrezzi(10);
		assertFalse(atrio.addAttrezzo(new Attrezzo("spada",0)));
	}
	@Test
	public void testAddAttrezzoTrue() {
		Stanza atrio = new Stanza("atrio");
		assertTrue(atrio.addAttrezzo(new Attrezzo("lama",0)));
	}
	@Test
	public void testAddAttrezzoNonEsistente() {
		Stanza atrio = new Stanza("atrio");
		assertFalse(atrio.addAttrezzo(null));
	}

	@Test 
	public void testremoveAttrezzoTrue() {
		Stanza atrio = new Stanza("atrio");
		Attrezzo piccone = new Attrezzo("Piccone",1);
		atrio.addAttrezzo(piccone);
		assertTrue(atrio.removeAttrezzo(piccone));
	}

	@Test
	public void testremoveAttrezzoSuStanzaVuota() {
		Stanza atrio = new Stanza("atrio");
		Attrezzo piccone = new Attrezzo("Piccone",1);
		assertFalse(atrio.removeAttrezzo(piccone));
	}
	@Test
	public void testremoveAttrezzoInesistente() {
		Stanza atrio = new Stanza("atrio");
		assertFalse(atrio.removeAttrezzo(null));
	}





}

