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
import casPratique.metier.model.User;
import casPratique.metier.dao.CommandeDao;


@Transactional
@Repository
public class UserDaoJpa implements UserDao {

	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;
	
	@Autowired
	private CommandeDao commandeDao;


	@Override
	@Transactional(readOnly=true)
	public User find(Long id) {
		return em.find(User.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<User> findAll() {
		Query query = em.createQuery("select u from User u");		
		return query.getResultList();
	}

	@Override
	public void create(User user) {
		em.persist(user);
	}

	//un objet récupéré de la base est déjà managé donc les modif se font automatiquement pas besoin d'update
	//on utilise update pour merger objet
	@Override
	public User update(User user) {
		return em.merge(user);
		
	}

	@Override
	public void delete(User user) {
		user = em.merge(user);
		for(Commande cmd : user.getCommandes()){
			commandeDao.delete(cmd);
		}
		em.remove(user);
		
	}

	@Override
	public void delete(Long id) {
		User user = find(id);
		for(Commande cmd : user.getCommandes()){
			commandeDao.delete(cmd);
		}
		//em.remove(client.getLogin());
		em.remove(user);
	
	}

}
