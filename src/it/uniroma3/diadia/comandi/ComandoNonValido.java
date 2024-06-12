package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	
//	public ComandoNonValido(IO console) {
//		this.console = console;
//	}
//	
	@Override
	public void esegui(Partita partita) {
		this.getConsole().mostraMessaggio("Comando non valido, digita aiuto per vedere i comandi\n");

	}
	@Override
	public String getNome() {
		return "non valido";
	}
}
