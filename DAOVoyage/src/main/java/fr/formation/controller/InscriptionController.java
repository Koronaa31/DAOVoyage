package fr.formation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.model.Client;
import fr.formation.model.Site;
import fr.formation.model.Utilisateur;

@Controller
public class InscriptionController extends SiteController {

	@GetMapping("/inscription")
	public String getInscription() {
		return "inscription";
	}

	@PostMapping("/inscription")
	public String postInscription(
			@RequestParam String adresseMail,
			@RequestParam String login,
			@RequestParam String password, Model model, HttpSession session) {

		Utilisateur u = site.checkMail(adresseMail);
		if (u == null) {
			u = new Client(login, password, adresseMail);
			site.inscription(u);
			session.setAttribute("isConnect", null);
			return "accueil";	
		} else {
			session.setAttribute("isConnect", "N");
			return getInscription();
		}
	}

	@PostMapping("/inscription/checkMail")
	public String checkMail(@RequestParam(required=false) String adresseMail, HttpSession session) {
		
			Utilisateur u = site.checkMail(adresseMail);
			if (u == null) {
				session.setAttribute("mailCorrect", "Y");
				return "accueil";	
			} else {
				session.setAttribute("mailCorrect", "N");
				return getInscription();
			}
	}


}