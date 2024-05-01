package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{
	@Override
	public Comando costruisciComando(String istruzione, IO console) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
		if (nomeComando == null) 
			comando = new ComandoNonValido(console);
		else if (nomeComando.equals("vai"))
			comando = new ComandoVai(console);
		else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi(console);
		else if (nomeComando.equals("posa"))
			comando = new ComandoPosa(console);
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto(console);
		else if (nomeComando.equals("fine"))
			comando = new ComandoFine(console);
		else if(nomeComando.equals("guarda"))
			comando = new ComandoGuarda(console);
		else comando = new ComandoNonValido(console);
		comando.setParametro(parametro);
		return comando;
	} 
}