package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.formation.model.Site;
import fr.formation.model.Transport;
import fr.formation.model.Ville;
import fr.formation.model.Voyage;

@Controller
public class RechercheController {

	@Autowired
	private Site site;
	
	
	@GetMapping("/recherche")
	public String getRecherche(Model model) {
		model.addAttribute("nbPanier", site.getPanier().size());
		model.addAttribute("recherche", site.getVoyage());
		return "recherche";
	}
	
	@PostMapping("/recherche")
	public String nouvelleRecherche(@RequestParam String v1,
			   						@RequestParam String v2,
			   						@RequestParam String t) {
		site.getVoyage().clear();
		
		Ville ville1 = site.getDaoVille().findByNom(v1);
		
		if (!v1.equals("N")) {
			Ville ville2 = site.getDaoVille().findByNom(v2);
			
			if (!t.equals("N")) {
				Transport transport = site.getDaoTransport().findByNom(t);
				site.research(ville1, ville2 , transport);
			}
			else { site.research(ville1,ville2); }
		}
		else { site.research(ville1); }
		
		return "redirect:/recherche";
	}
	
	@PostMapping("/ajoutPanier")
	@ResponseBody
	public int ajoutPanier(@RequestParam String nomV1 ,@RequestParam String nomV2, @RequestParam String nomT) {
		
		System.out.println(nomV1);
		
		Ville v1 = site.getDaoVille().findByNom(nomV1);
		Ville v2 = site.getDaoVille().findByNom(nomV2);
		Transport t = site.getDaoTransport().findByNom(nomT);
		
		Voyage voy = new Voyage(v1,v2,t);
		System.out.println(voy);
		site.choix(voy);
		
		return site.getPanier().size();
	}
	
}
