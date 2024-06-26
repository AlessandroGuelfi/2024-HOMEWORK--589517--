package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.*;


import java.util.*;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IO console;

	public DiaDia(IO io) {
		this.partita = new Partita();
		this.console = io;
	}

	public DiaDia(Labirinto lab, IO io) {
		this.console = io;
		this.partita = new Partita(lab);
	}

	public void gioca() throws Exception {
		String istruzione; 

		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {
			istruzione = this.console.leggiRiga();
		}
		while (!processaIstruzione(istruzione));
	}   

	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva(this.getConsole());
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita); 
		if (this.partita.vinta())
			this.console.mostraMessaggio("Hai vinto!");
		return this.partita.isFinita();
	}
	
	public IO getConsole() {
		return console;
	}

	public static void main(String[] argc) throws Exception {
		/* N.B. unica istanza di IOConsole
		di cui sia ammessa la creazione */ 
		Scanner scannerDiLinee = new Scanner(System.in);
		IO io = new IOConsole(scannerDiLinee);
		Labirinto lab = Labirinto.newBuilder("labirinto.txt").getLabirinto();
//		Labirinto labirinto = new LabirintoBuilder()
//				.addStanzaIniziale("LabCampusOne")
//				.addStanza("aulaN11")
//				.addAttrezzo("quaderno", 5)
//				.addAdiacenza("LabCampusOne", "aulaN11", "sud")
//				.addAdiacenza("aulaN11", "LabCampusOne", "nord")
//				.addStanzaVincente("Biblioteca")
//				.addAdiacenza("LabCampusOne","Biblioteca","ovest")
//				.getLabirinto();
//		mappaLabirinti.put(0, labirinto);
//		Labirinto monolocale = new LabirintoBuilder()
//				.addStanzaIniziale("salotto") 
//				.addStanzaVincente("salotto") 
//				.getLabirinto(); 
//		mappaLabirinti.put(1, monolocale);
//		Labirinto bilocale = new LabirintoBuilder()
//				.addStanzaIniziale("salotto")
//				.addStanzaVincente("camera")
//				.addAttrezzo("letto",10)
//				.addAdiacenza("salotto", "camera", "nord") 
//				.getLabirinto(); 
//		mappaLabirinti.put(2, bilocale);
//		Labirinto trilocale = new LabirintoBuilder()
//				.addStanzaIniziale("salotto")
//				.addStanza("cucina")
//				.addAttrezzo("pentola",1) 
//				.addStanzaVincente("camera")
//				.addAdiacenza("salotto", "cucina", "nord")
//				.addAdiacenza("cucina", "camera", "est")
//				.getLabirinto();
		DiaDia gioco = new DiaDia(lab, io);
		gioco.gioca();
		scannerDiLinee.close();
	}	
}

