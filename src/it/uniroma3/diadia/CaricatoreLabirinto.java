package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	private static final String MAGHI_MARKER = "Maghi:";

	private static final String STREGHE_MARKER = "Streghe:";

	private static final String CANI_MARKER = "Cani:";
	
	private static final String STANZE_BUIE_MARKER = "StanzeBuie:";

	private static final String STANZE_BLOCCATE_MARKER = "StanzeBloccate:";

	private static final String STANZE_MAGICHE_MARKER = "StanzeMagiche:";
	/*
	 *  Esempio di un possibile file di specifica di un labirinto

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Mago: Nome NomeAttrezzo Peso Stanza Presentazione
		Streghe: Nome Stanza Presentazione
		Cani: Nome NomeAttrezzo Peso Stanza Presentazione
		StanzeBuie: Nome NomeAttrezzo 
		StanzeBloccate: Nome Direzione NomeAttrezzo 
		StanzeMagiche: Nome Soglia
		Attrezzi: martello 10 biblioteca, pinza 2 N10\n
		Uscite: biblioteca nord N10, biblioteca sud N11\n

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(StringReader stream) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(stream);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiEImpostaCani();
			this.leggiEImpostaMaghi();
			this.leggiEImpostaStreghe();
			this.leggiEImpostaStanzeBloccate();
			this.leggiEImpostaStanzeBuie();
			this.leggiEImpostaStanzeMagiche();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext())
				result.add(scannerDiParole.next());
		}
		return result;
	}

	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);
		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();

			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specificaUscite : separaStringheAlleVirgole(specificheUscite)) {

			try (Scanner scannerDiLinea = new Scanner(specificaUscite)) {			
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				String dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				String stanzaDestinazione = scannerDiLinea.next();

				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}

		}
	}

	private void leggiEImpostaMaghi() throws FormatoFileNonValidoException {
		String specificheMaghi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);
		for(String specificaMago : separaStringheAlleVirgole(specificheMaghi)) {
			try(Scanner scannerDiLinea = new Scanner(specificaMago)) {
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome di un mago" + specificaMago));
				String nomeMago = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome di un attrezzo di un mago"));
				String nomeAttrezzo = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il peso di un attrezzo di un mago"));
				String pesoAttrezzo = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome della stanza dove deve essere collocato un mago"));
				String nomeStanza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("presentazione del mago"));
				String presentazione = scannerDiLinea.next();

				check(isStanzaValida(nomeStanza),"Stanza dove vuoi collocare un mago, sconosciuta");
				int peso = Integer.parseInt(pesoAttrezzo);
				AbstractPersonaggio mago = new Mago(nomeMago, presentazione, new Attrezzo(nomeAttrezzo,peso));
				this.nome2stanza.get(nomeStanza).setPersonaggio(mago);
			}
		}

	}


	private void leggiEImpostaStreghe() throws FormatoFileNonValidoException {
		String specificheStreghe = this.leggiRigaCheCominciaPer(STREGHE_MARKER);
		for(String specificaStrega : separaStringheAlleVirgole(specificheStreghe)) {
			try(Scanner scannerDiLinea = new Scanner(specificaStrega)) {
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome di una strega" + specificaStrega));
				String nomeStrega = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome della stanza dove deve essere collocata una strega"));
				String nomeStanza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("presentazione della strega"));
				String presentazione = scannerDiLinea.next();

				check(isStanzaValida(nomeStanza),"Stanza dove vuoi collocare una strega, sconosciuta");
				AbstractPersonaggio strega = new Strega(nomeStrega, presentazione);
				this.nome2stanza.get(nomeStanza).setPersonaggio(strega);
			}
		}

	}

	private void leggiEImpostaCani() throws FormatoFileNonValidoException {
		String specificheCani = this.leggiRigaCheCominciaPer(CANI_MARKER);
		for(String specificaCane : separaStringheAlleVirgole(specificheCani)) {
			try(Scanner scannerDiLinea = new Scanner(specificaCane)) {
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome di un cane" + specificaCane));
				String nomeCane = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome di un attrezzo di un cane"));
				String nomeAttrezzo = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il peso di un attrezzo di un cane"));
				String pesoAttrezzo = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome della stanza dove deve essere collocato un cane"));
				String nomeStanza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("presentazione del cane"));
				String presentazione = scannerDiLinea.next();

				check(isStanzaValida(nomeStanza),"Stanza dove vuoi collocare un cane, sconosciuta");
				int peso = Integer.parseInt(pesoAttrezzo);
				AbstractPersonaggio cane = new Cane(nomeCane, presentazione, new Attrezzo(nomeAttrezzo,peso));
				this.nome2stanza.get(nomeStanza).setPersonaggio(cane);
			}
		}

	}

	private void leggiEImpostaStanzeBuie() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String specificaStanzaBuia : separaStringheAlleVirgole(specificheStanze)) {
			try(Scanner scannerDiLinea = new Scanner(specificaStanzaBuia)) {
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome di una stanza buia" + specificaStanzaBuia));
				String nomeStanza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome del attrezzo per vedere nella stanza buia"));
				String nomeAttrezzo = scannerDiLinea.next();
				Stanza stanzaBuia = new StanzaBuia(nomeStanza, nomeAttrezzo);
				this.nome2stanza.put(nomeStanza, stanzaBuia);
			}
		}
	}

	private void leggiEImpostaStanzeBloccate() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String specificaStanzaBloccata : separaStringheAlleVirgole(specificheStanze)) {
			try(Scanner scannerDiLinea = new Scanner(specificaStanzaBloccata)) {
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome di una stanza bloccata" + specificaStanzaBloccata ));
				String nomeStanza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la direzione bloccata di una stanza"));
				String direzione = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome del attrezzo per vedere nella stanza bloccata"));
				String nomeAttrezzo = scannerDiLinea.next();
				Stanza stanzaBloccata = new StanzaBloccata(nomeStanza, Direzione.valueOf(direzione.toUpperCase()), nomeAttrezzo);
				this.nome2stanza.put(nomeStanza, stanzaBloccata);
			}
		}
	}

	private void leggiEImpostaStanzeMagiche() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String specificaStanzaMagica : separaStringheAlleVirgole(specificheStanze)) {
			try(Scanner scannerDiLinea = new Scanner(specificaStanzaMagica)) {
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome di una stanza magica" + specificaStanzaMagica ));
				String nomeStanza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la soglia di una stanza magica"));
				String sogliaMagica = scannerDiLinea.next();
				int soglia = Integer.parseInt(sogliaMagica);
				Stanza stanzaMagica = new StanzaMagica(nomeStanza, soglia);
				this.nome2stanza.put(nomeStanza, stanzaMagica);
			}
		}
	}


	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public Map<String, Stanza> getNome2stanza() {
		return nome2stanza;
	}
}