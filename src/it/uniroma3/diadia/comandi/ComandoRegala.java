package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {
	private static final String MESSAGGIO_REGALO = "Che cosa vuoi regalare?";
	private static final String MESSAGGIO_ATTREZZO_NON_NELLA_BORSA = "L'attrezzo che cerchi non e' nella borsa";
	private static final String MESSAGGIO_NESSUN_PERSONAGGIO = "Non c'e' nessun personaggio nella stanza";
	@Override
	public void esegui(Partita partita) {
		if(this.getParametro() == null) 
			this.getConsole().mostraMessaggio(MESSAGGIO_REGALO);
		else {
			if(!partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro())) {
				this.getConsole().mostraMessaggio(MESSAGGIO_ATTREZZO_NON_NELLA_BORSA);
			}
			else {
				AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
				if(personaggio == null) {
					this.getConsole().mostraMessaggio(MESSAGGIO_NESSUN_PERSONAGGIO);
				}
				else {
					this.getConsole().mostraMessaggio(personaggio.riceviRegalo(partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro()), partita));
				}
			}
		}
	}

	@Override
	public String getNome() {
		return "regala";
	}

}
