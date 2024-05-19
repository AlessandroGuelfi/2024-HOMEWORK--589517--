package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	private Borsa borsa;
	private Borsa borsa2;
	private Attrezzo piuma;
	private Attrezzo ps;
	private Attrezzo libro;
	private Attrezzo piombo;

	@Before
	public void setUp() throws Exception {
		this.borsa2 = new Borsa();
		
		this.borsa = new Borsa(100);
		this.piuma = new Attrezzo("piuma",1);
		this.ps = new Attrezzo("ps",5);
		this.libro = new Attrezzo("libro",5);
		this.piombo = new Attrezzo("piombo",10);
		
	}

	@Test
	public void testAddAttrezzoTrue() {
		assertTrue(borsa2.addAttrezzo(new Attrezzo("Piccone",1)));
	}
	
	@Test
	public void testAddAttrezzoBorsaPiena() {
		borsa2.addAttrezzo(new Attrezzo("Piccone",10));
		assertFalse(borsa2.addAttrezzo(new Attrezzo("spada",2)));
	}
	
	@Test
	public void testgetAttrezzoNotNull() {
		borsa2.addAttrezzo(new Attrezzo("lancia",2));
		assertNotNull(borsa2.getAttrezzo("lancia"));
	}
	
	@Test
	public void testgetAttrezzoNullAttrezzoNonNellaBorsa() {
		borsa2.addAttrezzo(new Attrezzo("fucile",2));
		assertNull(borsa2.getAttrezzo("spada"));
	}
	
	@Test
	public void testgetAttrezzoBorsaVuota() {
		assertNull(borsa2.getAttrezzo("spada"));
	}
	
	@Test
	public void testremoveAttrezzoAttrezzoEsistente() {
		borsa2.addAttrezzo(new Attrezzo("esistente",2));
		assertNotNull(borsa2.getAttrezzo("esistente"));
	}
	
	@Test
	public void testremoveAttrezzoAttrezzoNonEsistente() {
		borsa2.addAttrezzo(new Attrezzo("esistente",2));
		assertNull(borsa2.getAttrezzo("nonEsistente"));
	}
	
	@Test
	public void testremoveAttrezzoAttrezzoBorsaVuota() {
		assertNull(borsa2.getAttrezzo("nonEsistente"));
	}
	
	//-------------------------------------------------------------Test su Liste------------------------------------------------
	@Test
	public void testListaOrdinataPerPeso() {
		this.borsa.addAttrezzo(libro);
		this.borsa.addAttrezzo(piombo);
		this.borsa.addAttrezzo(ps);
		this.borsa.addAttrezzo(piuma);
		List<Attrezzo> ordinata = this.borsa.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> i = ordinata.iterator();
		assertTrue(i.hasNext());
		assertEquals(piuma,i.next());
		assertTrue(i.hasNext());
		assertEquals(libro,i.next());
		assertTrue(i.hasNext());
		assertEquals(ps,i.next());
		assertTrue(i.hasNext());
		assertEquals(piombo,i.next());
		assertFalse(i.hasNext());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testListaOrdinataPerPesoVuota() {
		List<Attrezzo> ordinata = this.borsa.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> i = ordinata.iterator();
		i.next();
	}
	
	@Test
	public void testListaOrdinataPerPesoUnElemento() {
		this.borsa.addAttrezzo(libro);
		List<Attrezzo> ordinata = this.borsa.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> i = ordinata.iterator();
		assertTrue(i.hasNext());
		assertEquals(libro, i.next());
		assertFalse(i.hasNext());
	}
	@Test
	public void testListaOrdinataPerPesoDueElementiStessoPeso() {
		this.borsa.addAttrezzo(ps);
		this.borsa.addAttrezzo(libro);
		List<Attrezzo> ordinata = this.borsa.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> i = ordinata.iterator();
		assertTrue(i.hasNext());
		assertEquals(libro, i.next());
		assertTrue(i.hasNext());
		assertEquals(ps, i.next());
		assertFalse(i.hasNext());
	}
	//------------------------------------------------Test sui Set-------------------------------------------------------
	@Test
	public void testSetOrdinatoPerNome() {
		this.borsa.addAttrezzo(libro);
		this.borsa.addAttrezzo(piombo);
		this.borsa.addAttrezzo(ps);
		this.borsa.addAttrezzo(piuma);
		Set<Attrezzo> ordinata = this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> i = ordinata.iterator();
		assertTrue(i.hasNext());
		assertEquals(libro,i.next());
		assertTrue(i.hasNext());
		assertEquals(piombo,i.next());
		assertTrue(i.hasNext());
		assertEquals(piuma,i.next());
		assertTrue(i.hasNext());
		assertEquals(ps,i.next());
		assertFalse(i.hasNext());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testSetOrdinatoPerNomeVuoto() {
		Set<Attrezzo> ordinata = this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> i = ordinata.iterator();
		i.next(); 
	}
	
	@Test
	public void testSetOrdinataPerNomeDueElementiStessoNome() {
		this.borsa.addAttrezzo(libro);
		this.borsa.addAttrezzo(new Attrezzo("libro",2));
		Set<Attrezzo> ordinata = this.borsa.getContenutoOrdinatoPerNome();
		assertTrue(ordinata.size() == 1);
	}
	
	//--------------------------------------------Test su Mappe--------------------------------------------------
	@Test
	public void testMapRaggruppataPerPesoVuota() {
		Map<Integer,List<Attrezzo>> mappa = this.borsa.getContenutoRaggruppatoPerPeso();
		assertTrue(mappa.size() == 0);
	}
	
	@Test
	public void testMapRaggruppataPerPesoDueElementiStessoPeso() {
		this.borsa.addAttrezzo(libro);
		this.borsa.addAttrezzo(ps);
		Map<Integer,List<Attrezzo>> mappa = this.borsa.getContenutoRaggruppatoPerPeso();
		assertTrue(mappa.get(5).size() == 2);
	}
	
	
	@Test
	public void testMapRaggruppataPerPeso() {
		this.borsa.addAttrezzo(libro);
		this.borsa.addAttrezzo(piombo);
		this.borsa.addAttrezzo(ps);
		this.borsa.addAttrezzo(piuma);
		Map<Integer, List<Attrezzo>> mappa = this.borsa.getContenutoRaggruppatoPerPeso();
		assertTrue(mappa.get(1).contains(piuma));
		assertTrue(mappa.get(5).contains(libro));
		assertTrue(mappa.get(5).contains(ps));
		assertTrue(mappa.get(10).contains(piombo));
		
	}
	
	//-------------------------------------------------------Test Sorted Set----------------------------------------------------------
	
	@Test
	public void testSortedSetOrdinatoPerPesoVuoto() {
		SortedSet<Attrezzo> ordinata = this.borsa.getSortedSetOrdinatoPerPeso();
		assertTrue(ordinata.size() == 0);
	}
	
	@Test
	public void testSortedSetOrdinatoPerPesoDueElementiStessoPeso() {
		this.borsa.addAttrezzo(libro);
		this.borsa.addAttrezzo(ps);
		SortedSet<Attrezzo> ordinata = this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> i = ordinata.iterator();
		assertTrue(i.hasNext());
		assertEquals(libro, i.next());
		assertTrue(i.hasNext());
		assertEquals(ps, i.next());
		assertFalse(i.hasNext());
	}
	
	
	@Test
	public void testSortedSetOrdinatoPerPeso() {
		this.borsa.addAttrezzo(libro);
		this.borsa.addAttrezzo(piuma);
		this.borsa.addAttrezzo(piombo);
		this.borsa.addAttrezzo(ps);
		SortedSet<Attrezzo> ordinata = this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> i = ordinata.iterator();
		assertTrue(i.hasNext());
		assertEquals(piuma,i.next());
		assertTrue(i.hasNext());
		assertEquals(libro,i.next());
		assertTrue(i.hasNext());
		assertEquals(ps,i.next());
		assertTrue(i.hasNext());
		assertEquals(piombo,i.next());
		assertFalse(i.hasNext());
	}
}
