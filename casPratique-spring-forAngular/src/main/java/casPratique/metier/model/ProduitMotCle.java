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
@Table(name="ProduitMotCle")
public class ProduitMotCle {

	private Long id;
	private Produit produit;
	private MotCle motCle;
	private Integer version;
	
	public ProduitMotCle() {}
	public ProduitMotCle(Produit produit,MotCle motCle)
	{
		this.produit = produit;
		this.motCle = motCle;
	}

	@Id @GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	//@Id 
	@ManyToOne
	@JoinColumn(name="Produit_ProduitMotCle")
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	//@Id 
	@ManyToOne
	@JoinColumn(name="MotCle_ProduitMotCle")
	public MotCle getMotCle() {
		return motCle;
	}

	public void setMotCle(MotCle motCle) {
		this.motCle = motCle;
	}
	
	@Version
	@Column(name="Version")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
}
