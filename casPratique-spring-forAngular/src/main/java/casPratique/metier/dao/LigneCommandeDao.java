package casPratique.metier.dao;

import casPratique.metier.model.LigneCommande;


public interface LigneCommandeDao extends Dao<LigneCommande, Long> {

	LigneCommande find(Integer quantite);
}
