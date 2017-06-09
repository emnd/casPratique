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


import casPratique.metier.dao.CategorieDao;
import casPratique.metier.model.Categorie;


@RestController
public class CategorieRestController {

	@Autowired
	private CategorieDao categorieDao;

	@RequestMapping(value = "/categorie/", method = RequestMethod.GET)
	public ResponseEntity<List<Categorie>> listAll() {
		List<Categorie> categories = categorieDao.findAll();
		if (categories == null) {
			return new ResponseEntity<List<Categorie>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Categorie>>(categories, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/categorie/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categorie> get(@PathVariable("id") Long id) {
		Categorie categorie = categorieDao.find(id);
		if (categorie == null) {
			return new ResponseEntity<Categorie>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Categorie>(categorie, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/categorie/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Categorie categorie, UriComponentsBuilder ucBuilder) {
		if (categorie.getId() != null && categorieDao.find(categorie.getId()) != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			categorieDao.create(categorie);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/categorie/{id}").buildAndExpand(categorie.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/categorie/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Categorie> update(@PathVariable("id") Long id, @RequestBody Categorie categorie) {
		Categorie currentCategorie = categorieDao.find(id);
		if (currentCategorie == null) {
			return new ResponseEntity<Categorie>(HttpStatus.NOT_FOUND);
		} else {
			currentCategorie.setNom(categorie.getNom());
			currentCategorie.setDescription(categorie.getDescription());
			currentCategorie= categorieDao.update(currentCategorie);
			currentCategorie = categorieDao.find(currentCategorie.getId());
			return new ResponseEntity<Categorie>(currentCategorie, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/categorie/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Categorie> delete(@PathVariable("id") Long id) {
		Categorie categorie = categorieDao.find(id);
		if (categorie == null) {
			return new ResponseEntity<Categorie>(HttpStatus.NOT_FOUND);
		}
		categorieDao.delete(categorie);
		return new ResponseEntity<Categorie>(HttpStatus.NO_CONTENT);
	}


}
