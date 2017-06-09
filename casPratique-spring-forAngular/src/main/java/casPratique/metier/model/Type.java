package casPratique.metier.model;

public enum Type {
	Client("Client"), AdminCat("AdminCat"), AdminProd("AdminProd");

	private final String label;

	private Type(String label) {
		this.label = label;

	}
	public String getLabel() {
		return label;
	}
	
	

}
