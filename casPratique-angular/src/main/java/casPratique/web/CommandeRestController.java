package casPratique.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import casPratique.metier.dao.CommandeDao;
import casPratique.metier.model.Commande;
import casPratique.metier.model.Etat;


@RestController
public class CommandeRestController {

	@Autowired
	private CommandeDao commandeDao;

	@RequestMapping(value = "/commande/", method = RequestMethod.GET)
	public ResponseEntity<List<Commande>> listAll() {
		List<Commande> users = commandeDao.findAll();
		if (users == null) {
			return new ResponseEntity<List<Commande>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Commande>>(users, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/commande/{id}", method = RequestMethod.GET)
	public ResponseEntity<Commande> get(@PathVariable("id") Long id) {
		Commande commande = commandeDao.find(id);
		if (commande == null) {
			return new ResponseEntity<Commande>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Commande>(commande, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/commande/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Commande commande, UriComponentsBuilder ucBuilder) {
		if (commande.getId() != null && commandeDao.find(commande.getId()) != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			commandeDao.create(commande);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/commande/{id}").buildAndExpand(commande.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/commande/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Commande> update(@PathVariable("id") Long id, @RequestBody Commande commande) {
		Commande currentCommande = commandeDao.find(id);
		if (currentCommande == null) {
			return new ResponseEntity<Commande>(HttpStatus.NOT_FOUND);
		} else {
			currentCommande.setUser(commande.getUser());
			currentCommande.setEtat(commande.getEtat());
			currentCommande.setCommentaire(commande.getCommentaire());
			currentCommande.setTotal(commande.getTotal());
			currentCommande.setDte(commande.getDte());
			currentCommande.setAdresse(currentCommande.getAdresse());
			currentCommande = commandeDao.update(currentCommande);
			currentCommande = commandeDao.find(currentCommande.getId());
			return new ResponseEntity<Commande>(currentCommande, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/commande/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Commande> delete(@PathVariable("id") Long id) {
		Commande commande = commandeDao.find(id);
		if (commande == null) {
			return new ResponseEntity<Commande>(HttpStatus.NOT_FOUND);
		}
		commandeDao.delete(commande);
		return new ResponseEntity<Commande>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/etats/", method = RequestMethod.GET)
	public ResponseEntity<Etat[]> listEtats() {

		return new ResponseEntity<Etat[]>(Etat.values(), HttpStatus.OK);

	}

}
