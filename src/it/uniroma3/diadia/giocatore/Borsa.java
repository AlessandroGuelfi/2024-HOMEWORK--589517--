package it.uniroma3.diadia.giocatore;

import java.util.*;

import it.uniroma3.diadia.ConfiguraStatoIniziale;
import it.uniroma3.diadia.attrezzi.*;

/**
 * La classe borsa si occupa di gestire gli attrezzi del giocatore
 * gli attrezzi sono contenuti in un'array e la borsa ha un limite di peso
 * @see Attrezzo
 */

public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = new ConfiguraStatoIniziale().getPeso();
	private List<Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<>(); 
		this.numeroAttrezzi = 0;
	}
	
	/**
	 * Si occupa di aggiungere un attrezzo nella borsa
	 * @param attrezzo riferimento all'oggetto che si vuole aggiungere
	 * @return true se l'attrezzo è stato messo nella borsa, false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null) return false;
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) return false;
		this.numeroAttrezzi++;
		this.attrezzi.add(attrezzo);
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}
	
	/**
	 * Si occupa di prendere l'attrezzo desiderato dalla borsa
	 * @param nomeAttrezzo nome dell'attrezzo 
	 * @return una copia del riferimento all'oggetto cercato all'interno della borsa
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for(Attrezzo att : this.attrezzi) {
			if(att != null && att.getNome().equals(nomeAttrezzo))
				return att;
		}
		return null;
	}

	public int getPeso() {
		int peso = 0;
		//Set<String> nomeAttrezzi = this.attrezzi.keySet();
		for (Attrezzo attuale : this.attrezzi) {
			if(attuale != null)
				peso += attuale.getPeso();
		}
		return peso;
	}

	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/**
	 * Si occupa di rimuovere un attrezzo dalla borsa
	 * @param nomeAttrezzo nome dell'attrezzo da rimuovere
	 * @return un riferimento all'oggetto rimosso dalla borsa
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> i = this.attrezzi.iterator();
		while(i.hasNext()) {
			a = i.next();
			if(a.getNome().equals(nomeAttrezzo)) {
				i.remove();
				this.numeroAttrezzi--;
				break;
			}
		}
		return a;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo attuale : this.attrezzi) {
				if(attuale != null)
					s.append(attuale.toString()+" ");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> listaOrdinataPerPeso;
		listaOrdinataPerPeso = this.attrezzi;
		
		
		Collections.sort(listaOrdinataPerPeso);
		return listaOrdinataPerPeso;
	}
	
	public List<Attrezzo> getAttrezzi() {
		return attrezzi;
	}

	public void setAttrezzi(List<Attrezzo> attrezzi) {
		this.attrezzi = attrezzi;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		ComparatorePerNome cmp = new ComparatorePerNome();
		SortedSet<Attrezzo> ordinatoPerNome = new TreeSet<>(cmp);
		ordinatoPerNome.addAll(this.attrezzi);
		
		return ordinatoPerNome;
	}
	
	public Map<Integer,List<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer,List<Attrezzo>> mappa = new HashMap<>();
		
		for(Attrezzo a : this.attrezzi) {
			List<Attrezzo> tmp = mappa.get(a.getPeso());
			if(tmp == null) {
				tmp = new ArrayList<>();
				mappa.put(a.getPeso(), tmp);
			}
			tmp.add(a);
		}
		return mappa;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> ordinata = new TreeSet<>();
		ordinata.addAll(this.attrezzi);
		return ordinata;
	}
	
	
}