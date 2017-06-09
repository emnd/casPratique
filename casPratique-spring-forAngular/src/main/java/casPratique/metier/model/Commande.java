package casPratique.metier.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="Commande")
public class Commande {
	
	private Long id;
	private Integer version;
	private Date dte;
	private String commentaire;
	private Double total;
	private List<LigneCommande> ligneCommande = new ArrayList<LigneCommande>();
	private User user;
	private Adresse adresse;
	private Etat etat;
	
	public Commande() {}

	public Commande(Date dte, String commentaire, Double total) {
		this.dte = dte;
		this.commentaire = commentaire;
		this.total = total;
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

	@Column(name="Date")
	//@NotEmpty(message="Date ne peut pas etre vide")
	public Date getDte() {
		return dte;
	}

	public void setDte(Date dte) {
		this.dte = dte;
	}

	@Column(name="Commentaire")
	//@NotEmpty(message="commentaire ne peut pas etre vide")
	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	@Column(name="Total")
	//@NotEmpty(message="total ne peut pas etre vide")
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@JsonIgnore
	@OneToMany(mappedBy="commande")
	public List<LigneCommande> getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(List<LigneCommande> ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	@ManyToOne
	@JoinColumn(name = "commande_user")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="rue",column=@Column(name="Cmd_RUE",length=50)),
		@AttributeOverride(name="codePostal",column=@Column(name="Cmd_CP",length=20)),
		@AttributeOverride(name="ville",column=@Column(name="Cmd_VILLE",length=50)),
		@AttributeOverride(name="pays",column=@Column(name="Cmd_PAYS",length=50))
		})
	@Valid
	public Adresse getAdresse() {
		return adresse;
	}



	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Column(name="Etat")
	@Enumerated(EnumType.STRING)
	//@NotEmpty(message="type ne peut pas etre vide")
	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}

}
