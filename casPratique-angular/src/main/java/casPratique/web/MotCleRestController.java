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

import casPratique.metier.dao.MotCleDao;
import casPratique.metier.model.MotCle;

@Controller
public class MotCleRestController {
	
	@Autowired
	private MotCleDao motcleDao;

	@RequestMapping(value = "/motcle/", method = RequestMethod.GET)
	public ResponseEntity<List<MotCle>> listAll() {
		List<MotCle> motcles = motcleDao.findAll();
		if (motcles == null) {
			return new ResponseEntity<List<MotCle>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<MotCle>>(motcles, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/motcle/{id}", method = RequestMethod.GET)
	public ResponseEntity<MotCle> get(@PathVariable("id") Long id) {
		MotCle motcle = motcleDao.find(id);
		if (motcle == null) {
			return new ResponseEntity<MotCle>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<MotCle>(motcle, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/motcle/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody MotCle motcle, UriComponentsBuilder ucBuilder) {
		if (motcle.getId() != null && motcleDao.find(motcle.getId()) != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			motcleDao.create(motcle);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/motcle/{id}").buildAndExpand(motcle.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/motcle/{id}", method = RequestMethod.PUT)
	public ResponseEntity<MotCle> update(@PathVariable("id") Long id, @RequestBody MotCle motCle) {
		MotCle currentMotCle = motcleDao.find(id);
		if (currentMotCle == null) {
			return new ResponseEntity<MotCle>(HttpStatus.NOT_FOUND);
		} else {
			
			currentMotCle.setMotCle(motCle.getMotCle());


			currentMotCle = motcleDao.update(currentMotCle);
			currentMotCle = motcleDao.find(currentMotCle.getId());
			return new ResponseEntity<MotCle>(currentMotCle, HttpStatus.OK);
			
			
		}
	}

	@RequestMapping(value = "/motcle/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<MotCle> delete(@PathVariable("id") Long id) {
		MotCle motcle = motcleDao.find(id);
		if (motcle == null) {
			return new ResponseEntity<MotCle>(HttpStatus.NOT_FOUND);
		}
		motcleDao.delete(motcle);
		return new ResponseEntity<MotCle>(HttpStatus.NO_CONTENT);
	}



}
