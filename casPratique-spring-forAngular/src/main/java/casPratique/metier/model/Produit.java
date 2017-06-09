package casPratique.metier.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Produit")
public class Produit {
	
	private Long id;
	private Integer version;
	private String nom;
	private Double prix;
	private Integer stock;
	private String description;
	private List<LigneCommande> ligneCommande = new ArrayList<LigneCommande>();
	private List<ProduitMotCle> produitMotCles = new ArrayList<ProduitMotCle>();
	private Categorie categorie;
	
	public Produit() {}

	public Produit(String nom, Double prix, Integer stock, String description) {
		this.nom = nom;
		this.prix = prix;
		this.stock = stock;
		this.description = description;
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

	@Column(name="Nom",length=50)
	//@NotEmpty(message="nom ne peut pas etre vide")
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name="Prix",length=50)
	//@NotEmpty(message="prix ne peut pas etre vide")
	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	@Column(name="Stock",length=50)
	//@NotEmpty(message="stock ne peut pas etre vide")
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Column(name="Description",length=50)
	//@NotEmpty(message="description ne peut pas etre vide")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@JsonIgnore
	@OneToMany(mappedBy="produit")
	public List<LigneCommande> getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(List<LigneCommande> ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	@JsonIgnore
	@OneToMany(mappedBy="produit")
	public List<ProduitMotCle> getProduitMotCles() {
		return produitMotCles;
	}

	public void setProduitMotCles(List<ProduitMotCle> produitMotCles) {
		this.produitMotCles = produitMotCles;
	}
	
	@ManyToOne
	@JoinColumn(name = "Produit_Categorie")
	public Categorie getCategorie()
	{
		return categorie;
	}
	public void setCategorie(Categorie categorie)
	{
		this.categorie=categorie;
	}

}
