package it.uniroma3.diadia;

import java.util.Scanner;


/** 
 * Classe che si occupa di gestire tutte le stampe e gli input dell'utilizzatore
 */
public class IOConsole implements IO {
	private Scanner scanner;
	
	public IOConsole(Scanner scanner) {
		this.scanner = scanner;
	}
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	@Override
	public String leggiRiga() {
		String riga = scanner.nextLine();
		return riga;
	}
}