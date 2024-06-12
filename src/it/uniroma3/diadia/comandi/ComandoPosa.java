package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPosa extends AbstractComando{
//	public ComandoPosa(IO console) {
//		this.console = console;
//	}
	
	/**
	 * Posa un attrezzo contenuto nella borsa del giocatore, se l'attrezzo non esiste invia un 
	 * messaggio d'errore
	 * 
	 * @param nomeAttrezzo nome dell'attrezzo
	 */
	@Override
	public void esegui(Partita partita) {
		Giocatore player = partita.getGiocatore();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if(player.getBorsa().hasAttrezzo(this.getParametro())) {
			if(stanzaCorrente.addAttrezzo(player.getBorsa().getAttrezzo(this.getParametro()))) {
				this.getConsole().mostraMessaggio("Hai posato l'attrezzo "+ player.getBorsa().removeAttrezzo(this.getParametro()) + " nella stanza " 
																									+ stanzaCorrente.getNome() + "\n");
				this.getConsole().mostraMessaggio("Borsa: " + player.getBorsa().toString() + "\n"); 
			}
			else
				this.getConsole().mostraMessaggio("La stanza e' piena, non puoi posare attrezzi all'interno\n");
		}
		else
			this.getConsole().mostraMessaggio("Questo attrezzo non esiste\n");
	}

	@Override
	public String getNome() {
		return "posa";
	}

}
