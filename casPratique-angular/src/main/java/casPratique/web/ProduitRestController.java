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

import casPratique.metier.dao.ProduitDao;

import casPratique.metier.model.Produit;


@RestController
public class ProduitRestController {

	@Autowired
	private ProduitDao produitDao;

	@RequestMapping(value = "/produit/", method = RequestMethod.GET)
	public ResponseEntity<List<Produit>> listAll() {
		List<Produit> produits = produitDao.findAll();
		if (produits == null) {
			return new ResponseEntity<List<Produit>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Produit>>(produits, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/produit/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produit> get(@PathVariable("id") Long id) {
		Produit produit = produitDao.find(id);
		if (produit == null) {
			return new ResponseEntity<Produit>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Produit>(produit, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/produit/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Produit produit, UriComponentsBuilder ucBuilder) {
		if (produit.getId() != null && produitDao.find(produit.getId()) != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			produitDao.create(produit);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/produit/{id}").buildAndExpand(produit.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/produit/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Produit> update(@PathVariable("id") Long id, @RequestBody Produit produit) {
		Produit currentProduit = produitDao.find(id);
		if (currentProduit == null) {
			return new ResponseEntity<Produit>(HttpStatus.NOT_FOUND);
		} else {
			currentProduit.setNom(produit.getNom());
			currentProduit.setDescription(produit.getDescription());
			currentProduit.setPrix(produit.getPrix());
			currentProduit.setStock(produit.getStock());
			currentProduit.setCategorie(produit.getCategorie());
			currentProduit= produitDao.update(currentProduit);
			currentProduit = produitDao.find(currentProduit.getId());
			return new ResponseEntity<Produit>(currentProduit, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/produit/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Produit> delete(@PathVariable("id") Long id) {
		Produit produit = produitDao.find(id);
		if (produit == null) {
			return new ResponseEntity<Produit>(HttpStatus.NOT_FOUND);
		}
		produitDao.delete(produit);
		return new ResponseEntity<Produit>(HttpStatus.NO_CONTENT);
	}


}
