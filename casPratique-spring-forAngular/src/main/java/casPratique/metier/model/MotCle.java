package casPratique.metier.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="MotCle")
public class MotCle {
	
	private Long id;
	private Integer version;
	private String motCle;
	private List<ProduitMotCle> produitMotCles = new ArrayList<ProduitMotCle>();
	
	public MotCle() {}

	public MotCle(String motCle) {
		this.motCle = motCle;
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

	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

	@JsonIgnore
	@OneToMany(mappedBy="motCle")
	public List<ProduitMotCle> getProduitMotCles() {
		return produitMotCles;
	}

	public void setProduitMotCles(List<ProduitMotCle> produitMotCles) {
		this.produitMotCles = produitMotCles;
	}
	
	
	
	

}
