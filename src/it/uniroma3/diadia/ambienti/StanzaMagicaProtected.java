package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected { 
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreOggettiPosati;
	private int soglia;


	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.soglia = soglia;
	}


	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),
				pesoX2);
		return attrezzo;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreOggettiPosati++;
		if (this.contatoreOggettiPosati > this.soglia)
			attrezzo = this.modificaAttrezzo(attrezzo);
		return this.attrezzi.add(attrezzo);
	}
}

