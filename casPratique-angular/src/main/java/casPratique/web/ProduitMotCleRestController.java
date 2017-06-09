package casPratique.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.util.UriComponentsBuilder;

import casPratique.metier.dao.ProduitMotCleDao;
import casPratique.metier.model.ProduitMotCle;

@Controller
public class ProduitMotCleRestController {
	
	@Autowired
	private  ProduitMotCleDao produitMotCleDao;

	@RequestMapping(value = "/produitmotcle/", method = RequestMethod.GET)
	public ResponseEntity<List<ProduitMotCle>> listAll() {
		List<ProduitMotCle> produitMotCles = produitMotCleDao.findAll();
		if (produitMotCles == null) {
			return new ResponseEntity<List<ProduitMotCle>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<ProduitMotCle>>(produitMotCles, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/produitmotcle/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProduitMotCle> get(@PathVariable("id") Long id) {
		ProduitMotCle produitMotCle = produitMotCleDao.find(id);
		if (produitMotCle == null) {
			return new ResponseEntity<ProduitMotCle>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ProduitMotCle>(produitMotCle, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/produitmotcle/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody ProduitMotCle produitMotCle, UriComponentsBuilder ucBuilder) {
		if (produitMotCle.getId() != null && produitMotCleDao.find(produitMotCle.getId()) != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			produitMotCleDao.create(produitMotCle);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/produitMotCle/{id}").buildAndExpand(produitMotCle.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/produitmotcle/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ProduitMotCle> update(@PathVariable("id") Long id, @RequestBody ProduitMotCle produitMotCle) {
		ProduitMotCle currentProduitMotCle = produitMotCleDao.find(id);
		if (currentProduitMotCle == null) {
			return new ResponseEntity<ProduitMotCle>(HttpStatus.NOT_FOUND);
		} else {
			
			currentProduitMotCle.setProduit(produitMotCle.getProduit());
			currentProduitMotCle.setMotCle(produitMotCle.getMotCle());


			currentProduitMotCle = produitMotCleDao.update(currentProduitMotCle);
			currentProduitMotCle = produitMotCleDao.find(currentProduitMotCle.getId());
			return new ResponseEntity<ProduitMotCle>(currentProduitMotCle, HttpStatus.OK);
			
			
		}
	}

	@RequestMapping(value = "/produitmotcle/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ProduitMotCle> delete(@PathVariable("id") Long id) {
		ProduitMotCle produitMotCle = produitMotCleDao.find(id);
		if (produitMotCle == null) {
			return new ResponseEntity<ProduitMotCle>(HttpStatus.NOT_FOUND);
		}
		produitMotCleDao.delete(produitMotCle);
		return new ResponseEntity<ProduitMotCle>(HttpStatus.NO_CONTENT);
	}


}
