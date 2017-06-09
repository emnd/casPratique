package casPratique.metier.model;

public enum Etat {
	
	Panier("Panier"), Valide("Valide"), Paye("Paye"),Envoyer("Envoyer"),Livre("Livre");
	
	private final String label;

	private Etat(String label) {
		this.label = label;

	}
	public String getLabel() {
		return label;
	}

}
