package casPratique.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import casPratique.metier.dao.MotCleDao;
import casPratique.metier.dao.ProduitDao;
import casPratique.metier.dao.ProduitMotCleDao;
import casPratique.metier.model.ProduitMotCle;


@Transactional
@Repository
public class ProduitMotCleDaoJpa implements ProduitMotCleDao {

	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;
	
	@Autowired
	private MotCleDao motCleDao;
	
	@Autowired
	private ProduitDao produitDao;
	


	@Override
	@Transactional(readOnly=true)
	public ProduitMotCle find(Long id) {
		return em.find(ProduitMotCle.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ProduitMotCle> findAll() {
		Query query = em.createQuery("from ProduitMotCle produitMotCle");		
		return query.getResultList();
	}

	@Override
	public void create(ProduitMotCle produitMotCle) {
		em.persist(produitMotCle);
	}

	//un objet récupéré de la base est déjà managé donc les modif se font automatiquement pas besoin d'update
	//on utilise update pour merger objet
	@Override
	public ProduitMotCle update(ProduitMotCle produitMotCle) {
		return em.merge(produitMotCle);
		
	}

	@Override
	public void delete(ProduitMotCle produitMotCle) {
		produitMotCle = em.merge(produitMotCle);
		
		em.remove(produitMotCle);
		
	}

	@Override
	public void delete(Long id) {
		ProduitMotCle produitMotCle = find(id);
		em.remove(produitMotCle);
	
	}


	

}
