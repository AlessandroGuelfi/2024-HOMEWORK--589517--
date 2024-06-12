package it.uniroma3.diadia;

import java.util.List;

import it.uniroma3.diadia.ambienti.Labirinto;
public class Simulazioni {
	public static IOSimulator creaLabirintoBilocale(List<String> comandiDaLeggere) throws Exception {
		IOSimulator sim = new IOSimulator(comandiDaLeggere);
		Labirinto lab = Labirinto.newBuilder("labirinto.txt")
				.addStanzaIniziale("inizio")
				.addStanzaVincente("fine")
				.addAdiacenza("iniziale", "fine", "nord")
				.getLabirinto();
		DiaDia gioco = new DiaDia(lab, sim);
		gioco.gioca();
		return sim;
				
	}

}
