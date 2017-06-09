package casPratique.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import casPratique.metier.dao.UserDao;
import casPratique.metier.model.Commande;
import casPratique.metier.model.LigneCommande;
import casPratique.metier.model.Produit;
import casPratique.metier.model.ProduitMotCle;
import casPratique.metier.model.User;
import casPratique.metier.dao.CommandeDao;
import casPratique.metier.dao.LigneCommandeDao;
import casPratique.metier.dao.ProduitDao;
import casPratique.metier.dao.ProduitMotCleDao;


@Transactional
@Repository
public class ProduitDaoJpa implements ProduitDao {

	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;
	
	@Autowired
	private LigneCommandeDao ligneCommandeDao;
	@Autowired
	private ProduitMotCleDao produitMotCleDao;

	@Override
	@Transactional(readOnly=true)
	public Produit find(Long id) {
		return em.find(Produit.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Produit> findAll() {
		Query query = em.createQuery("from Produit p");		
		return query.getResultList();
	}

	@Override
	public void create(Produit produit) {
		//produit.setCategorie(em.merge(produit.getCategorie()));
		em.persist(produit);
	}

	//un objet récupéré de la base est déjà managé donc les modif se font automatiquement pas besoin d'update
	//on utilise update pour merger objet
	@Override
	public Produit update(Produit produit) {
		//produit.setCategorie(em.merge(produit.getCategorie()));
		return em.merge(produit);
		
	}

	@Override
	public void delete(Produit produit) {
		produit = em.merge(produit);
		for(LigneCommande ligneCommande : produit.getLigneCommande()){
			ligneCommandeDao.delete(ligneCommande);
		}
		for(ProduitMotCle produitMotCle : produit.getProduitMotCles()){
			produitMotCleDao.delete(produitMotCle);
		}
		em.remove(produit);
		
	}

	@Override
	public void delete(Long id) {
		Produit produit = find(id);
		for(LigneCommande ligneCommande : produit.getLigneCommande()){
			ligneCommandeDao.delete(ligneCommande);
		}
		for(ProduitMotCle produitMotCle : produit.getProduitMotCles()){
			produitMotCleDao.delete(produitMotCle);
		}
		em.remove(produit);
	
	}

}
