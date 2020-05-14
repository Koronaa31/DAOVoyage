package fr.formation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.formation.model.Client;
import fr.formation.model.Transport;
import fr.formation.model.Ville;
import fr.formation.model.Voyage;

@Controller
public class PanierController extends SiteController {
	
	@GetMapping("/panier")
	public String getPanier(Model model) {
		model.addAttribute("panier",  site.getPanier());
		double total=0;
		for(Voyage voy : site.getPanier())
		{
			total+=voy.getPrix();
		}
		model.addAttribute("total", Math.floor(total*100)/100);
		
		for (Voyage v : site.getPanier())
		System.out.println(v);
		
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

//	@PostMapping("/panier/remove")
//	public String removeById(@RequestParam(required = false) int nb) {
//		System.out.println(nb);
//		site.getPanier().remove(nb);
//		return "redirect:/panier";
//	}
	
	@PostMapping("/panier/remove")
	@ResponseBody
	public String supprimerDuPanier(@RequestParam int i) {
		site.getPanier().remove(i-1);
		
		double total=0;
		for(Voyage voy : site.getPanier())
		{
			total+=voy.getPrix();
		}
		
		return String.valueOf(Math.floor(total*100)/100);
	}
	
	@GetMapping("/commandes")
	public String getCommandes(Model model, HttpSession session) {
		String statut = "Commande";
		Client client = (Client) session.getAttribute("client");
		List<Voyage> loadPanier = site.getDaoVoyage().findByClientAndStatut(client, statut);
		model.addAttribute("commandes", loadPanier);
		return "commandes";
	}
}