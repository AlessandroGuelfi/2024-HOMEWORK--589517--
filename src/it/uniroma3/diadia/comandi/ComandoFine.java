package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
//	public ComandoFine(IO console) {
//		this.console = console;
//	}
	
	@Override
	public void esegui(Partita partita) {
		if(partita.getCfu() == 0)
			this.getConsole().mostraMessaggio("Hai esaurito i CFU...");
		this.getConsole().mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}

	@Override
	public String getNome() {
		return "fine";
	}

}
