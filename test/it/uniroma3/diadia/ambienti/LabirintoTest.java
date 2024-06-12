package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;

public class LabirintoTest {

	@Test
	public void testgetStanzaVincente() throws FileNotFoundException, FormatoFileNonValidoException {
		Labirinto lab = Labirinto.newBuilder("labirinto.txt")
				.addStanzaVincente("fine")
				.getLabirinto();
		assertEquals(new Stanza("fine"),lab.getStanzaVincente());
	}
	
	@Test
	public void testgetStanzaVincenteNon() throws FileNotFoundException, FormatoFileNonValidoException {
		Labirinto lab = Labirinto.newBuilder("labirinto.txt")
				.addStanzaVincente("fine")
				.getLabirinto();
		Stanza nonVincente = new Stanza("StanzaNonVincente");
		assertNotEquals(nonVincente,lab.getStanzaVincente());
	}

}
