package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO {
	
	private List<String> messaggioDaLeggere;
	private List<String> messaggioDaMostrare;
	private int indiceMessLegg;
	private int indiceMessMostra;
	private int indiceMessLetti;
	
	
	public IOSimulator(List<String> righeDaLeggere) {
		this.indiceMessLegg = 0;
		this.indiceMessMostra = 0;
		this.indiceMessLetti = 0;
		this.messaggioDaLeggere = righeDaLeggere;
		this.messaggioDaMostrare = new ArrayList<>();
	}
	
	
	

	@Override
	public String leggiRiga() {
		String riga = null;

		riga = this.messaggioDaLeggere.get(this.indiceMessLegg);
		this.indiceMessLegg++;
		return riga;
	}

	@Override
	public void mostraMessaggio(String msg) {
		this.messaggioDaMostrare.add(this.indiceMessMostra, msg);
		this.indiceMessMostra++;
	}
	
	public String nextMessaggio() {
		String next = this.messaggioDaLeggere.get(indiceMessLetti);
		this.indiceMessLetti++;
		return next;
	}
	
	public boolean hasNextMessaggio() {
		return this.indiceMessLetti < this.indiceMessMostra;
	}

}
