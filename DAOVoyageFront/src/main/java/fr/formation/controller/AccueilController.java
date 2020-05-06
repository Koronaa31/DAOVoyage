package fr.formation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.model.Admin;
import fr.formation.model.Site;
import fr.formation.model.Transport;
import fr.formation.model.Utilisateur;
import fr.formation.model.Ville;

@Controller
public class AccueilController {
	
	@Autowired
	private Site site;
	
	@GetMapping("/accueil")
	public String getAccueil(Model model) {
		return "accueil";
	}
	
	@PostMapping("/accueil/connect")
	public String checkConnect(@RequestParam String login,
							   @RequestParam String password,
								Model model, HttpSession session) {
		Utilisateur u = site.checkConnect(login, password);
		if (u != null) {
			session.setAttribute("client", u);
			session.setAttribute("isConnect", "Y");
			if (u instanceof Admin) {
				//return AdminController.getAdmin(model);
			}
		} else {session.setAttribute("isConnect", "N");}
		return this.getAccueil(model);
	}
	
	@PostMapping("/accueil/disconnect")
	public String invalidate(Model model, HttpSession session) {
		site.getPanier().clear();
		session.invalidate();
		return this.getAccueil(model);
	}
	
	
	@ModelAttribute("villes")
	public List<Ville> villes(){
		return site.getDaoVille().findAll();
	}
	
	@ModelAttribute("transports")
	public List<Transport> transports(){
		return site.getDaoTransport().findAll();
	}
	
	
}