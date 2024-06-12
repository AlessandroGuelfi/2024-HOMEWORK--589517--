package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;


public class testFabbricaDiComandiRiflessiva {
	private FabbricaDiComandi riflessiva;
	private IO console;
	private Comando cmd;
	
	@Before
	public void setUp() throws Exception {
		console = new IOConsole(new Scanner(System.in));
		riflessiva = new FabbricaDiComandiRiflessiva(console);
	}

	@Test
	public void testComandoNonValido() {
		cmd = riflessiva.costruisciComando("go est");
		assertEquals(ComandoNonValido.class, cmd.getClass());
	}
	
	@Test
	public void testComandoGuarda() {
		cmd = riflessiva.costruisciComando("guarda borsa");
		assertEquals("guarda", cmd.getNome());
		assertEquals("borsa", cmd.getParametro());
	}
	
	@Test
	public void testComandoAiuto() {
		cmd = riflessiva.costruisciComando("aiuto");
		assertEquals(ComandoAiuto.class, cmd.getClass());
	}


}
