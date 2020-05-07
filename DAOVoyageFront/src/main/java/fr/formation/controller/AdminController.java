package fr.formation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.formation.model.Site;
import fr.formation.model.Transport;
import fr.formation.model.Ville;

@Controller
public class AdminController extends SiteController {
	
	@GetMapping("/admin")
	public String getAdmin(Model model, HttpSession session) {
		return "admin";
	}
	
	@PostMapping("/admin/ville")
	public String adminVille(HttpSession session) {
		session.setAttribute("aGerer", "gererVilles");
		return "redirect:/admin";
	}
	
	@PostMapping("/admin/transport")
	public String adminTransport(HttpSession session) {
		session.setAttribute("aGerer", "gererTransports");
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/ville/{id}/supprimer")
	public String supprimerVille(@PathVariable int id) {
		try {
			site.getDaoVille().deleteById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin";
	}
	
	@PostMapping("admin/ville/ajout")
	public String ajoutVille(Ville v) {
		site.getDaoVille().save(v);
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/transport/{id}/supprimer")
	public String supprimerTransport(@PathVariable int id) {
		try {
			site.getDaoTransport().deleteById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin";
	}
	
	@PostMapping("admin/transport/ajout")
	public String ajoutTransport(Transport t) {
		site.getDaoTransport().save(t);
		return "redirect:/admin";
	}
	
	
	
	
	
	@ModelAttribute("villes")
	public List<Ville> villes() {
		return site.getDaoVille().findAll();
	}
	
	@ModelAttribute("transports")
	public List<Transport> transport() {
		return site.getDaoTransport().findAll();
	}
}
