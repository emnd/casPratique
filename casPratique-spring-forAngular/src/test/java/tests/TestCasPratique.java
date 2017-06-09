package tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import casPratique.metier.dao.CategorieDao;
import casPratique.metier.dao.CommandeDao;
import casPratique.metier.dao.LigneCommandeDao;
import casPratique.metier.dao.MotCleDao;
import casPratique.metier.dao.ProduitDao;
import casPratique.metier.dao.ProduitMotCleDao;
import casPratique.metier.dao.UserDao;
import casPratique.metier.model.Adresse;
import casPratique.metier.model.Categorie;
import casPratique.metier.model.Commande;
import casPratique.metier.model.Etat;
import casPratique.metier.model.LigneCommande;
import casPratique.metier.model.MotCle;
import casPratique.metier.model.Produit;
import casPratique.metier.model.ProduitMotCle;
import casPratique.metier.model.Type;
import casPratique.metier.model.User;


@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCasPratique {

	@Autowired
	private UserDao userDao;
	@Autowired
	private CommandeDao commandeDao;
	@Autowired
	private ProduitDao produitDao;
	@Autowired
	private CategorieDao categorieDao;
	@Autowired
	private MotCleDao motCleDao;
	@Autowired
	private LigneCommandeDao ligneCommandeDao;
	@Autowired
	private ProduitMotCleDao produitMotCleDao;
	
	@Autowired
	private ProduitMotCleDao produitMotCleIdDao;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void test1() throws ParseException {
		Adresse adrUser = new Adresse();
		adrUser.setRue("rue de la paix");
		adrUser.setCodePostal("75001");
		adrUser.setPays("France");
		adrUser.setVille("Paris");

		Adresse adrCommande = new Adresse();
		adrCommande.setRue("rue des lapins");
		adrCommande.setCodePostal("45000");
		adrCommande.setPays("Orleans");
		adrCommande.setVille("France");
		
		User user1 = new User("Cas","Pratique","caspratique","caspratique",Type.AdminCat);
		user1.setAdresse(adrUser);
		User user2 = new User("AJC","Open","caspratique","caspratique",Type.AdminProd);
		user2.setAdresse(adrCommande);
		User user3 = new User("Cas","Pratique","caspratique","caspratique",Type.Client);
		user3.setAdresse(adrUser);
		
		System.out.println("Mon user "+user1);
		
		userDao.create(user1);
		userDao.create(user2);
		userDao.create(user3);
		user3.setNom("TP CAS PRATIQUE");
		userDao.update(user3);
		userDao.delete(user2);
		
		
	}
	
	@Test
	public void test2() throws ParseException {
		
		Produit produit1 = new Produit("hp",50.02,50,"Bon produit 1");
		Produit produit2 = new Produit("samsung",50.02,50,"Bon produit 2");
		Produit produit3 = new Produit("toshiba",75.05,50,"Bon produit 3");
		Produit produit4 = new Produit("iphone",950.99,50,"Bon produit 4");
		produitDao.create(produit1);
		produitDao.create(produit2);
		produitDao.create(produit3);
		produitDao.create(produit4);
		produit1.setPrix(100.25);
		produitDao.update(produit1);
		produitDao.delete(produit3);
	}
	
	@Test
	public void test3() throws ParseException {
		
		Adresse adrUser = new Adresse();
		adrUser.setRue("rue de la paix");
		adrUser.setCodePostal("75001");
		adrUser.setPays("France");
		adrUser.setVille("Paris");

		Adresse adrCommande = new Adresse();
		adrCommande.setRue("rue des lapins");
		adrCommande.setCodePostal("45000");
		adrCommande.setPays("Orleans");
		adrCommande.setVille("France");
		
		User user1 = new User("userCommande","commandeUser","commandeClient","clientCommande",Type.Client);
		user1.setAdresse(adrUser);
		
		Commande cmd1 = new Commande(sdf.parse("04/10/2016"),"Tres belle commande",10.10);
		cmd1.setAdresse(adrCommande);
		cmd1.setEtat(Etat.Valide);
		Commande cmd2 = new Commande(sdf.parse("04/10/2015"),"belle commande",100.10);
		cmd2.setAdresse(adrUser);
		cmd2.setEtat(Etat.Paye);
		
		
		commandeDao.create(cmd1);
		commandeDao.create(cmd2);
		
		userDao.create(user1);
		cmd1.setUser(user1);
		commandeDao.update(cmd1);
		
	}
	
	@Test
	public void test4() throws ParseException {
		Categorie categorie1 = new Categorie("ordinateur","Des ordinateurs portable");
		Categorie categorie2 = new Categorie("usb","Des cles USB");
		
		Produit p= new Produit("iphone",950.99,50,"Bon produit 4");
		categorieDao.create(categorie1);
		categorieDao.create(categorie2);
		
		p.setCategorie(categorie1);
		p.setNom("HP portable");
		p.setPrix(900.49);
		produitDao.create(p);
		
	}
	
	@Test
	public void test5() throws ParseException {
		MotCle motCle1 = new MotCle("hp");
		MotCle motCle2 = new MotCle("samsung");
		
		motCleDao.create(motCle1);
		motCleDao.create(motCle2);
		motCle1.setMotCle("HP hp Portable PORTABLE ordinateur");
		
		motCleDao.update(motCle1);
	}

	@Test
	public void test6() throws ParseException {
		Categorie categorie2 = new Categorie("usb","Des cles USB de 32G");
		Produit p= new Produit("Produit Mot Cle",999.99,10,"Bon produit mot cle");
		categorieDao.create(categorie2);
		p.setCategorie(categorie2);
		produitDao.create(p);
		
		MotCle motCle2 = new MotCle("cle usb CLE USB");
		motCleDao.create(motCle2);
		
		ProduitMotCle produitMotCle = new ProduitMotCle(p,motCle2);
		produitMotCleDao.create(produitMotCle);
		
	}
	
	@Test
	public void test7() throws ParseException {
		Produit p= new Produit("Produit Mot Cle",999.99,10,"Bon produit mot cle");
		Categorie categorie2 = new Categorie("usb","Des cles USB de 32G");
		categorieDao.create(categorie2);
		p.setCategorie(categorie2);
		produitDao.create(p);
		Adresse adrCommande = new Adresse();
		adrCommande.setRue("rue de la paix");
		adrCommande.setCodePostal("75001");
		adrCommande.setPays("France");
		adrCommande.setVille("Paris");
		Commande cmd1 = new Commande(sdf.parse("04/10/2016"),"Tres belle commande",10.10);
		cmd1.setAdresse(adrCommande);
		User user1 = new User("userCommande","commandeUser","commandeClient","clientCommande",Type.Client);
		user1.setAdresse(adrCommande);
		cmd1.setUser(user1);
		userDao.create(user1);
		commandeDao.create(cmd1);
		
		LigneCommande ligneCmd = new LigneCommande(p,cmd1);
		ligneCommandeDao.create(ligneCmd);
		
	}
	
	
}
