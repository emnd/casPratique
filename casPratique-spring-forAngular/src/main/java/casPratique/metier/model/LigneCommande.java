package casPratique.metier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="LigneCommande")
//@IdClass(LigneCommandeId.class)
public class LigneCommande {
	
	private Long id;
	private Integer version;
	private Integer quantite;
	private Commande commande;
	private Produit produit;
	
	public LigneCommande() {}

	public LigneCommande(Integer quantite) {
		this.quantite = quantite;
	}
	public LigneCommande(Produit produit,Commande commande)
	{
		this.produit = produit;
		this.commande = commande;
	}

	@Id @GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Version
	@Column(name="Version")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	//@Id 
	@ManyToOne
	@JoinColumn(name="Commande_LigneCommande")
	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	//@Id 
	@ManyToOne
	@JoinColumn(name="Produit_LigneCommande")
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	
	
	

}
