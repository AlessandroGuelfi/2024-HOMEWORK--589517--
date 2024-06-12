package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	private static final String MESSAGGIO_MORDE = "Il cane credendoti un giocattolo, ti ha morso, facendoti perdere 1 CFU";
	private static final String MESSAGGIO_REGALO = "Il cane accetta il regalo e lascia l'attrezzo che teneva in bocca";
	
	public Cane(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione, attrezzo);
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getCfu()-1);
		return MESSAGGIO_MORDE;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo != null) {
			String msg = MESSAGGIO_REGALO;
			partita.getStanzaCorrente().addAttrezzo(this.getAttrezzo());
			this.setAttrezzo(attrezzo);
			return msg;
		}
		return null;
	}

}
