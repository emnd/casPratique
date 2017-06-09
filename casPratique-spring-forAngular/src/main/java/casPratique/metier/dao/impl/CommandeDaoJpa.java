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


@Transactional
@Repository
public class CommandeDaoJpa implements CommandeDao {

	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;
	
	@Autowired
	private LigneCommandeDao ligneCommandeDao;
	
	@Autowired
	private UserDao userDao;


	@Override
	@Transactional(readOnly=true)
	public Commande find(Long id) {
		return em.find(Commande.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Commande> findAll() {
		Query query = em.createQuery("from Commande cmd");		
		return query.getResultList();
	}

	@Override
	public void create(Commande cmd) {
		//cmd.setUser(em.merge(cmd.getUser()));
		em.persist(cmd);
	}

	//un objet récupéré de la base est déjà managé donc les modif se font automatiquement pas besoin d'update
	//on utilise update pour merger objet
	@Override
	public Commande update(Commande cmd) {
		//cmd.setUser(em.merge(cmd.getUser()));
		return em.merge(cmd);
		
	}

	@Override
	public void delete(Commande cmd) {
		cmd = em.merge(cmd);
		for(LigneCommande ligneCommande : cmd.getLigneCommande()){
			ligneCommandeDao.delete(ligneCommande);
		}
		em.remove(cmd);
		
	}

	@Override
	public void delete(Long id) {
		Commande cmd = find(id);
		for(LigneCommande ligneCommande : cmd.getLigneCommande()){
			ligneCommandeDao.delete(ligneCommande);
		}
		em.remove(cmd);
	
	}

}
