package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi{
	private IO io;
	public FabbricaDiComandiRiflessiva(IO io) {
		this.io = io;
	}
	@SuppressWarnings("deprecation")
	@Override
	public Comando costruisciComando(String istruzione){
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		AbstractComando comando = null;
		
		if(scannerDiParole.hasNext()) 
			nomeComando = scannerDiParole.next();
		if(scannerDiParole.hasNext()) 
			parametro = scannerDiParole.next();
		try {
		StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
		nomeClasse.append(nomeComando.substring(1));
		comando = (AbstractComando)Class.forName(nomeClasse.toString()).newInstance();
		comando.setParametro(parametro);
		} catch(Exception e) {
			comando = new ComandoNonValido();
			this.io.mostraMessaggio("Comando inesistente");
		}
		comando.setConsole(io);
		return comando;
		
	}
}
