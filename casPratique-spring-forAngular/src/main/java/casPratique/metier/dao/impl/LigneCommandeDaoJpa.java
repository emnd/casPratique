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
import casPratique.metier.model.User;
import casPratique.metier.dao.CommandeDao;
import casPratique.metier.dao.LigneCommandeDao;
import casPratique.metier.dao.ProduitDao;


@Transactional
@Repository
public class LigneCommandeDaoJpa implements LigneCommandeDao {

	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;
	
	@Autowired
	private CommandeDao commandeDao;
	
	@Autowired
	private ProduitDao produitDao;
	


	@Override
	@Transactional(readOnly=true)
	public LigneCommande find(Long id) {
		return em.find(LigneCommande.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<LigneCommande> findAll() {
		Query query = em.createQuery("from LigneCommande ligneCommande");		
		return query.getResultList();
	}

	@Override
	public void create(LigneCommande ligneCommande) {
		em.persist(ligneCommande);
	}

	//un objet récupéré de la base est déjà managé donc les modif se font automatiquement pas besoin d'update
	//on utilise update pour merger objet
	@Override
	public LigneCommande update(LigneCommande ligneCommande) {
		return em.merge(ligneCommande);
		
	}

	@Override
	public void delete(LigneCommande ligneCommande) {
		ligneCommande = em.merge(ligneCommande);
		
		em.remove(ligneCommande);
		
	}

	@Override
	public void delete(Long id) {
		LigneCommande ligneCommande = find(id);
		em.remove(ligneCommande);
	
	}

	@Override
	public LigneCommande find(Integer quantite) {
		// TODO Auto-generated method stub
		return null;
	}

}
