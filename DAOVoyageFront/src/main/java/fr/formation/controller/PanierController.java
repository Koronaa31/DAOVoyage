package fr.formation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.model.Client;
import fr.formation.model.Site;
import fr.formation.model.Transport;
import fr.formation.model.Ville;
import fr.formation.model.Voyage;

@Controller
public class PanierController {
	
	@Autowired
	Site site;
	
	@GetMapping("/panier")
	public String getPanier(HttpSession session) {
		session.setAttribute("panier",  site.getPanier());
		double total=0;
		for(Voyage voy : site.getPanier())
		{
			total+=voy.getPrix();
		}
		session.setAttribute("total", Math.floor(total*100)/100);
		return "panier";
	}
	
	@PostMapping("/panier/paiement")
	public String postPanier(Model model, HttpSession session) {
		
		List<Voyage> commande = site.getPanier();
		for(Voyage v : commande)
		{
		Ville v1 = v.getV1();
		Ville v2 = v.getV2();
		Transport t = v.getT();
		
		Voyage voyage = new Voyage(v1,v2,t);
		voyage.setStatut("Commande");
		
		//int id = (int) request.getSession().getAttribute("id");
		//Client client = (Client) Site.getInstance().getDaoUtilisateur().selectById(id);
		Client client = (Client) session.getAttribute("client");
        voyage.setClient(client);
        
		site.getDaoVoyage().save(voyage);
		}
		
		site.getPanier().clear();
		return "redirect:/panier";
	}

	@PostMapping("/panier/remove")
	public String removeById(@RequestParam int nb) {
		site.getPanier().remove(nb);
		return "done";
	}
}