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
import casPratique.metier.model.MotCle;
import casPratique.metier.model.ProduitMotCle;
import casPratique.metier.model.User;
import casPratique.metier.dao.CommandeDao;
import casPratique.metier.dao.MotCleDao;
import casPratique.metier.dao.ProduitMotCleDao;


@Transactional
@Repository
public class MotCleDaoJpa implements MotCleDao {

	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;
	
	@Autowired
	private ProduitMotCleDao produitMotCleDao;


	@Override
	@Transactional(readOnly=true)
	public MotCle find(Long id) {
		return em.find(MotCle.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<MotCle> findAll() {
		Query query = em.createQuery("from MotCle motCle");		
		return query.getResultList();
	}

	@Override
	public void create(MotCle motCle) {
		em.persist(motCle);
	}

	//un objet récupéré de la base est déjà managé donc les modif se font automatiquement pas besoin d'update
	//on utilise update pour merger objet
	@Override
	public MotCle update(MotCle motCle) {
		return em.merge(motCle);
		
	}

	@Override
	public void delete(MotCle motCle) {
		motCle = em.merge(motCle);
		for(ProduitMotCle produitMotCle : motCle.getProduitMotCles()){
			produitMotCleDao.delete(produitMotCle);
		}
		em.remove(motCle);
		
	}

	@Override
	public void delete(Long id) {
		MotCle motCle = find(id);
		for(ProduitMotCle produitMotCle : motCle.getProduitMotCles()){
			produitMotCleDao.delete(produitMotCle);
		}
		em.remove(motCle);
	
	}

}
