package it.uniroma3.diadia.personaggi;

import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	public static final String MESSAGGIO_BUONO = "Goditi questo trucco di magia";
	public static final String MESSAGGIO_CATTIVO = "Neanche mi hai salutato";
	public static final String MESSAGGIO_REGALO = "Mai fidarsi di una strega, ahahha";

	public Strega(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione, attrezzo);
	}
	
	public Strega(String nome, String presentazione) {
		this(nome, presentazione, null);
	}
	@Override
	public String agisci(Partita partita) {
		Map<Direzione, Stanza> stanzeAdiacenti = partita.getStanzaCorrente().getMapStanzeAdiacenti();
		Set<Direzione> nomeStanze = stanzeAdiacenti.keySet();		
		int numeroAttrezzi = 0;
		Stanza stanzaDoveVerraiTrasportato = null;
		String msg = null;
		if(this.haSalutato()) {
			for(Direzione s : nomeStanze) {
				if(stanzeAdiacenti.get(s).getAttrezzi().size() >= numeroAttrezzi) {
					stanzaDoveVerraiTrasportato = stanzeAdiacenti.get(s);
					numeroAttrezzi = stanzeAdiacenti.get(s).getAttrezzi().size();
				}
				msg = MESSAGGIO_BUONO;
			}
		}
		else {
			for(Direzione s : nomeStanze) {
				if(stanzeAdiacenti.get(s).getAttrezzi().size() <= numeroAttrezzi) {
					stanzaDoveVerraiTrasportato = stanzeAdiacenti.get(s);
					numeroAttrezzi = stanzeAdiacenti.get(s).getAttrezzi().size();
				}

				msg = MESSAGGIO_CATTIVO;
			}
		}
		partita.setStanzaCorrente(stanzaDoveVerraiTrasportato);	
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo != null) {
			String msg = MESSAGGIO_REGALO;
			this.setAttrezzo(attrezzo);
			return msg;
		}
		return null;
	}

}
