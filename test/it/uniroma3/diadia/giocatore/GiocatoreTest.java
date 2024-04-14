package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Test;

public class GiocatoreTest {

	@Test
	public void testgetCfuTrue() {
		Giocatore giocatore = new Giocatore();
		giocatore.setCfu(10);
		assertEquals(10, giocatore.getCfu());
	}
	@Test
	public void testgetCfuFalse() {
		Giocatore giocatore = new Giocatore();
		giocatore.setCfu(10);
		assertNotEquals(0, giocatore.getCfu());
	}

}
