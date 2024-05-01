package it.uniroma3.diadia;


public class IOSimulator implements IO {
	
	private String[] messaggioDaLeggere;
	private String[] messaggioDaMostrare;
	private int indiceMessLegg;
	private int indiceMessMostra;
	
	
	public IOSimulator(String[] righeDaLeggere) {
		this.indiceMessLegg = 0;
		this.indiceMessMostra = 0;
		this.messaggioDaLeggere = righeDaLeggere;
		this.messaggioDaMostrare = new String[500];
	}
	
	
	

	@Override
	public String leggiRiga() {
		String riga = null;

		riga = this.messaggioDaLeggere[indiceMessLegg];
		this.indiceMessLegg++;
		return riga;
	}

	@Override
	public void mostraMessaggio(String msg) {
		this.messaggioDaMostrare[indiceMessMostra] = msg;
		this.indiceMessMostra++;
	}
	

}
