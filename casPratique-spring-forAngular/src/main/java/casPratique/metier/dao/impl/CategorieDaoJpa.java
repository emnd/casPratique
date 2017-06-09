package casPratique.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import casPratique.metier.dao.UserDao;
import casPratique.metier.model.Categorie;
import casPratique.metier.model.Commande;
import casPratique.metier.model.Produit;
import casPratique.metier.model.User;
import casPratique.metier.dao.CategorieDao;
import casPratique.metier.dao.CommandeDao;
import casPratique.metier.dao.ProduitDao;


@Transactional
@Repository
public class CategorieDaoJpa implements CategorieDao {

	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;
	
	@Autowired
	private ProduitDao produitDao;


	@Override
	@Transactional(readOnly=true)
	public Categorie find(Long id) {
		return em.find(Categorie.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Categorie> findAll() {
		Query query = em.createQuery("from Categorie c");		
		return query.getResultList();
	}

	@Override
	public void create(Categorie categorie) {
		em.persist(categorie);
	}

	//un objet récupéré de la base est déjà managé donc les modif se font automatiquement pas besoin d'update
	//on utilise update pour merger objet
	@Override
	public Categorie update(Categorie categorie) {
		return em.merge(categorie);
		
	}

	@Override
	public void delete(Categorie categorie) {
		categorie = em.merge(categorie);
		for(Produit produit : categorie.getProduits()){
			produitDao.delete(produit);
		}
		em.remove(categorie);
		
	}

	@Override
	public void delete(Long id) {
		Categorie categorie = find(id);
		for(Produit produit : categorie.getProduits()){
			produitDao.delete(produit);
		}
		em.remove(categorie);
	
	}

}
