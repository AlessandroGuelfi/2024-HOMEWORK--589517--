package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirintoTest {
	private CaricatoreLabirinto cLab;
	
	private static final String monolocale = "Stanze:biblioteca\n"
			+ "Inizio:biblioteca\n"
			+ "Vincente:biblioteca\n"
			+ "Cani:\n"
			+ "Maghi:\n"
			+ "Streghe:\n"
			+ "StanzeBloccate:\n"
			+ "StanzeBuie:\n"
			+ "StanzeMagiche:\n"
			+ "Attrezzi:astuccio 5 biblioteca\n"
			+ "Uscite:";
	
	private static final String bilocale = "Stanze:biblioteca,N10\n"
			+ "Inizio:biblioteca\n"
			+"Vincente:N10\n"
			+ "Cani:\n"
			+ "Maghi:\n"
			+ "Streghe:\n"
			+ "StanzeBloccate:\n"
			+ "StanzeBuie:\n"
			+ "StanzeMagiche:\n"
			+ "Attrezzi:spada 4 biblioteca,lama 4 N10\n"
			+ "Uscite:biblioteca sud N10";
	
	private static final String trilocale = "Stanze:biblioteca,N10,N11\n"
			+ "Inizio:N10\n"
			+ "Vincente:N11\n"
			+ "Cani:\n"
			+ "Maghi:\n"
			+ "Streghe:\n"
			+ "StanzeBloccate:\n"
			+ "StanzeBuie:\n"
			+ "StanzeMagiche:\n"
			+ "Attrezzi:martello 10 biblioteca,pinza 2 N10\n"
			+ "Uscite:biblioteca nord N10,biblioteca sud N11";
			
	@Test
	public void testMonolocale() throws FileNotFoundException, FormatoFileNonValidoException {
		cLab = new CaricatoreLabirinto(new StringReader(monolocale));
		cLab.carica();
		Stanza iniziale = cLab.getStanzaIniziale();
		Stanza finale = cLab.getStanzaVincente();
		assertEquals(new Stanza("biblioteca"), iniziale);
		assertEquals(new Stanza("biblioteca"), finale);
		assertEquals(new Attrezzo("astuccio", 5), iniziale.getAttrezzo("astuccio"));
	} 
	
	@Test
	public void testBilocale() throws FileNotFoundException, FormatoFileNonValidoException {
		cLab = new CaricatoreLabirinto(new StringReader(bilocale));
		cLab.carica();
		Stanza iniziale = cLab.getStanzaIniziale();
		Stanza finale = cLab.getStanzaVincente();
		assertEquals(new Stanza("biblioteca"), iniziale);
		assertEquals(new Stanza("N10"), finale);
		assertEquals(new Attrezzo("spada",4), iniziale.getAttrezzo("spada"));
		assertEquals(new Attrezzo("lama",4), finale.getAttrezzo("lama"));
		assertEquals(iniziale.getMapStanzeAdiacenti().get(Direzione.SUD), finale);
	}
	
	@Test
	public void testTrilocale() throws FileNotFoundException, FormatoFileNonValidoException {
		cLab = new CaricatoreLabirinto(new StringReader(trilocale));
		cLab.carica();
		Stanza iniziale = cLab.getStanzaIniziale();
		Stanza finale = cLab.getStanzaVincente();
		Stanza biblioteca = cLab.getNome2stanza().get("biblioteca");
		assertEquals(new Stanza("N10"), iniziale);
		assertEquals(new Stanza("N11"), finale);
		assertEquals(new Attrezzo("martello", 10), biblioteca.getAttrezzo("martello"));
		assertEquals(new Attrezzo("pinza", 2), iniziale.getAttrezzo("pinza"));
		assertEquals(biblioteca.getMapStanzeAdiacenti().get(Direzione.NORD), iniziale);
		assertEquals(biblioteca.getMapStanzeAdiacenti().get(Direzione.SUD), finale);
		
	}
	

}
