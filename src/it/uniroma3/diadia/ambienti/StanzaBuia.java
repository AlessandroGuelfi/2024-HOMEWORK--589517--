package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	private String nomeAttrezzoModificatore;


	public StanzaBuia (String nome, String nomeAttrezzoModificatore) {
		super(nome);
		this.nomeAttrezzoModificatore = nomeAttrezzoModificatore;	
	}
	
	
	@Override
	public String getDescrizione() {
		String messaggioModificatore = "qui c'è un buio pesto";
		if(this.hasAttrezzo(nomeAttrezzoModificatore))
			return super.getDescrizione();
		else
			return messaggioModificatore;
	}
	
	public String getNomeAttrezzoModificatore() {
		return nomeAttrezzoModificatore;
	}
	
}
