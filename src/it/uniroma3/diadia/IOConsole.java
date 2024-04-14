package it.uniroma3.diadia;

import java.util.Scanner;


/** 
 * Classe che si occupa di gestire tutte le stampe e gli input dell'utilizzatore
 */
public class IOConsole {
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}