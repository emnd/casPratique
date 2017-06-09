package casPratique.metier.model;

import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.Valid;


import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Utilisateur")
public class User {

	private Long id;
	private Integer version;
	private String nom;
	private String prenom;
	private String login;
	private String motDePasse;
	private Type type;
	private List<Commande> commandes =  new ArrayList<Commande>();
	private Adresse adresse;
	
	
	public User() {	}
	
	
	
	public User(String nom, String prenom, String login, String motDePasse, Type type) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.motDePasse = motDePasse;
		this.type = type;
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
	
	@Column(name="Prenom",length=50)
	//@NotEmpty(message="prenom ne peut pas etre vide")
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	@Column(name="Login",length=50)
	//@NotEmpty(message="login ne peut pas etre vide")
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Column(name="MotDePasse",length=50)
	//@NotEmpty(message="mot de passe ne peut pas etre vide")
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	@Column(name="Type")
	@Enumerated(EnumType.STRING)
	//@NotEmpty(message="type ne peut pas etre vide")
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}


	@JsonIgnore
	@OneToMany(mappedBy = "user")
	public List<Commande> getCommandes() {
		return commandes;
	}



	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}


	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="rue",column=@Column(name="U_RUE",length=50)),
		@AttributeOverride(name="codePostal",column=@Column(name="U_CP",length=20)),
		@AttributeOverride(name="ville",column=@Column(name="U_VILLE",length=50)),
		@AttributeOverride(name="pays",column=@Column(name="U_PAYS",length=50))
		})
	@Valid
	public Adresse getAdresse() {
		return adresse;
	}



	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", version=" + version + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login
				+ ", motDePasse=" + motDePasse + ", type=" + type + ", commandes=" + commandes + ", adresse=" + adresse
				+ "]";
	}

	
	
}
