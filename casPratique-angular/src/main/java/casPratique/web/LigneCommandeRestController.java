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

import casPratique.metier.dao.LigneCommandeDao;
import casPratique.metier.model.LigneCommande;

@RestController
public class LigneCommandeRestController {

	@Autowired
	private  LigneCommandeDao ligneCommandeDao;

	@RequestMapping(value = "/lignecommande/", method = RequestMethod.GET)
	public ResponseEntity<List<LigneCommande>> listAll() {
		List<LigneCommande> ligneCommandes = ligneCommandeDao.findAll();
		if (ligneCommandes == null) {
			return new ResponseEntity<List<LigneCommande>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<LigneCommande>>(ligneCommandes, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/lignecommande/{id}", method = RequestMethod.GET)
	public ResponseEntity<LigneCommande> get(@PathVariable("id") Long id) {
		LigneCommande ligneCommande = ligneCommandeDao.find(id);
		if (ligneCommande == null) {
			return new ResponseEntity<LigneCommande>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<LigneCommande>(ligneCommande, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/lignecommande/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody LigneCommande ligneCommande, UriComponentsBuilder ucBuilder) {
		if (ligneCommande.getId() != null && ligneCommandeDao.find(ligneCommande.getId()) != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			ligneCommandeDao.create(ligneCommande);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/lignecommande/{id}").buildAndExpand(ligneCommande.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/lignecommande/{id}", method = RequestMethod.PUT)
	public ResponseEntity<LigneCommande> update(@PathVariable("id") Long id, @RequestBody LigneCommande ligneCommande) {
		LigneCommande currentLigneCommande = ligneCommandeDao.find(id);
		if (currentLigneCommande == null) {
			return new ResponseEntity<LigneCommande>(HttpStatus.NOT_FOUND);
		} else {
			
			currentLigneCommande.setProduit(ligneCommande.getProduit());
			currentLigneCommande.setCommande(ligneCommande.getCommande());
			currentLigneCommande.setQuantite(ligneCommande.getQuantite());


			currentLigneCommande = ligneCommandeDao.update(currentLigneCommande);
			currentLigneCommande = ligneCommandeDao.find(currentLigneCommande.getId());
			return new ResponseEntity<LigneCommande>(currentLigneCommande, HttpStatus.OK);
			
			
		}
	}

	@RequestMapping(value = "/lignecommande/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<LigneCommande> delete(@PathVariable("id") Long id) {
		LigneCommande ligneCommande = ligneCommandeDao.find(id);
		if (ligneCommande == null) {
			return new ResponseEntity<LigneCommande>(HttpStatus.NOT_FOUND);
		}
		ligneCommandeDao.delete(ligneCommande);
		return new ResponseEntity<LigneCommande>(HttpStatus.NO_CONTENT);
	}


}
