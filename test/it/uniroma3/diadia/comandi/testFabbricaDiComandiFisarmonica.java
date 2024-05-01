package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;

public class testFabbricaDiComandiFisarmonica {
	private FabbricaDiComandi f;
	private Comando c;
	private IOConsole console;
	@Before
	public void setUp() throws Exception {
		f = new FabbricaDiComandiFisarmonica();
		console = new IOConsole();
	}

	@Test
	public void testComandoVaiCorretto() {
		c = f.costruisciComando("vai nord",console);
		assertEquals("vai nord", c.getNome() + " " + c.getParametro());
	}
	
	@Test
	public void testComandoVaiDirezioniDiverse() {
		c = f.costruisciComando("vai sud",console);
		assertNotEquals("vai nord", c.getNome() + " " + c.getParametro());
	}
	
	@Test
	public void testComandoPrendiCorretto() {
		c = f.costruisciComando("prendi spada",console);
		assertEquals("prendi spada", c.getNome() + " " + c.getParametro());
	}
	
	@Test
	public void testComandoPrendiAttrezzoDiverso() {
		c = f.costruisciComando("prendi spada",console);
		assertNotEquals("prendi lama", c.getNome() + " " + c.getParametro());
	}
	
	@Test
	public void testComandoPosaAttrezzoCorretto() {
		c = f.costruisciComando("posa spada",console);
		assertEquals("posa spada", c.getNome() + " " + c.getParametro());
	}

	
	@Test
	public void testComandoPosaAttrezzoDiverso() {
		c = f.costruisciComando("posa spada",console);
		assertNotEquals("posa lama", c.getNome() + " " + c.getParametro());
	}
	
	@Test
	public void testComandoNonValido() {
		c = f.costruisciComando("uccidi",console);
		assertNotEquals("non valido", c.getNome() + " " + c.getParametro());
	}
	
	@Test
	public void testComandoFine() {
		c = f.costruisciComando("fine",console);
		assertNotEquals("fine", c.getNome() + " " + c.getParametro());
	}
	
	@Test
	public void testComandoGuarda() {
		c = f.costruisciComando("guarda",console);
		assertNotEquals("guarda", c.getNome() + " " + c.getParametro());
	}
	@Test
	public void testComandoAiuto() {
		c = f.costruisciComando("aiuto",console);
		assertNotEquals("aiuto", c.getNome() + " " + c.getParametro());
	}



}
