package fr.formation.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.model.Cagnotte;
import fr.formation.model.Client;
import fr.formation.model.Site;
import fr.formation.model.Transport;
import fr.formation.model.Ville;
import fr.formation.model.Voyage;

@Controller
public class CagnotteController {

	@Autowired
	private Site site;

	@GetMapping("/cagnotte")
	public String getCagnotte() {
		return "cagnotte";
	}


	@PostMapping("/cagnotte")
	public String postCagnotte(@RequestParam String action,
			@RequestParam (required = false) String action2,
			@RequestParam (required = false) String loginDestinataire,
			@RequestParam (required = false) Integer v1,
			@RequestParam (required = false) Integer v2,
			@RequestParam (required = false) Integer t,
			@RequestParam (required = false) Double sommePayee,
			@RequestParam (required = false) Integer cagnotteChoisie,
			Model model,
			HttpSession session) {

		////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////// Création ///////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		if (action.equals("creation")) {
			model.addAttribute("aAfficher", "creation");
			model.addAttribute("villes", site.getDaoVille().findAll());
			model.addAttribute("transports", site.getDaoTransport().findAll());
			try {
				if (action2.equals("creationCagnotte")) {
					Client initiateur = (Client) session.getAttribute("client");
					Client destinataire = (Client) site.getDaoUtilisateur().findByLogin(loginDestinataire);
					if (destinataire == null) {
						model.addAttribute("cagnotteSuccess", "N");
						return "/cagnotte";
					}
					Voyage v = new Voyage(site.getDaoVille().findById(v1).orElse(new Ville()),
							site.getDaoVille().findById(v2).orElse(new Ville()),
							site.getDaoTransport().findById(t).orElse(new Transport()),
							destinataire);
					v.setStatut("Cagnotte");
					site.creationCagnotte(v, initiateur);
					model.addAttribute("cagnotteSuccess", "Y");
				}
			} catch (NullPointerException e) {return "/cagnotte";}
		}

		////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////// Participation ////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////

		else if (action.equals("participation")) {
			model.addAttribute("aAfficher", "participation");
			try {
				if (action2.equals("participationCagnotte")) {
					Client destinataire = (Client) site.getDaoUtilisateur().findByLogin(loginDestinataire);
					if (destinataire == null) {
						model.addAttribute("checkLogin", "N");
					} else {
						destinataire.setCagnottesDestinataire(site.getDaoCagnotte().findByDestinataireAPayer(destinataire));
						if (destinataire.getCagnottesDestinataire().isEmpty()) {
							model.addAttribute("checkLogin", "N");
						} else {
							model.addAttribute("checkLogin", "Y");
							model.addAttribute("cagnottesDestinataire", destinataire.getCagnottesDestinataire());
						}
					}
					return "/cagnotte";
				}
			} catch (NullPointerException e){return "/cagnotte";}
		}
		
		else if (action.contentEquals("sommePayee")) {
			site.participer(sommePayee,
							site.getDaoCagnotte().findById(cagnotteChoisie).orElse(new Cagnotte()), 
							(Client) session.getAttribute("client"));
			return "redirect:/accueil";
		}

		////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////// Archives ///////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////

		else if (action.equals("archives")) {
			Client client = (Client) session.getAttribute("client");
			site.archives(client);
			session.setAttribute("client", client);
			model.addAttribute("aAfficher", "archives");
		}
		return "/cagnotte";	
	}
}
