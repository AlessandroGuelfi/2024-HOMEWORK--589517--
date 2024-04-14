package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddAttrezzoTrue() {
		Borsa borsa = new Borsa();
		assertTrue(borsa.addAttrezzo(new Attrezzo("Piccone",1)));
	}
	
	@Test
	public void testAddAttrezzoBorsaPiena() {
		Borsa borsa = new Borsa();
		borsa.addAttrezzo(new Attrezzo("Piccone",10));
		assertFalse(borsa.addAttrezzo(new Attrezzo("spada",2)));
	}
	
	@Test
	public void testgetAttrezzoNotNull() {
		Borsa borsa = new Borsa();
		borsa.addAttrezzo(new Attrezzo("lancia",2));
		assertNotNull(borsa.getAttrezzo("lancia"));
	}
	
	@Test
	public void testgetAttrezzoNullAttrezzoNonNellaBorsa() {
		Borsa borsa = new Borsa();
		borsa.addAttrezzo(new Attrezzo("fucile",2));
		assertNull(borsa.getAttrezzo("spada"));
	}
	
	@Test
	public void testgetAttrezzoBorsaVuota() {
		Borsa borsa = new Borsa();
		assertNull(borsa.getAttrezzo("spada"));
	}
	
	@Test
	public void testremoveAttrezzoAttrezzoEsistente() {
		Borsa borsa = new Borsa();
		borsa.addAttrezzo(new Attrezzo("esistente",2));
		assertNotNull(borsa.getAttrezzo("esistente"));
	}
	
	@Test
	public void testremoveAttrezzoAttrezzoNonEsistente() {
		Borsa borsa = new Borsa();
		borsa.addAttrezzo(new Attrezzo("esistente",2));
		assertNull(borsa.getAttrezzo("nonEsistente"));
	}
	
	@Test
	public void testremoveAttrezzoAttrezzoBorsaVuota() {
		Borsa borsa = new Borsa();
		assertNull(borsa.getAttrezzo("nonEsistente"));
	}
}
