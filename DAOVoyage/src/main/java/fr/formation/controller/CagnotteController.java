package fr.formation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.formation.model.Cagnotte;
import fr.formation.model.Client;
import fr.formation.model.Voyage;
import fr.formation.security.UserPrincipal;

@Controller
public class CagnotteController extends SiteController {

	@GetMapping("/cagnotte")
	public String getCagnotte() {
		return "cagnotte";
	}


	@PostMapping("/cagnotte")
	public String postCagnotte(@RequestParam String action,
			@RequestParam (required = false) String action2,
			@RequestParam (required = false) String loginDestinataire,
			@RequestParam (required = false) String v1,
			@RequestParam (required = false) String v2,
			@RequestParam (required = false) String t,
			@RequestParam (required = false) Double sommePayee,
			@RequestParam (required = false) Integer cagnotteChoisie,
			Model model,
			Authentication auth,
			HttpSession session) {

		UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
		
		////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////// Création ///////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		if (action.equals("creation")) {
			System.out.println("coucou");
			model.addAttribute("aAfficher", "creation");
			model.addAttribute("villes", site.getDaoVille().findAll());
			model.addAttribute("transports", site.getDaoTransport().findAll());

			try {
				if (action2.equals("creationCagnotte")) {
					
					
					Client initiateur = (Client) principal.getUtilisateur();
					Client destinataire = (Client) site.getDaoUtilisateur().findByLogin(loginDestinataire);
					if (destinataire == null) {
						model.addAttribute("cagnotteSuccess", "N");
						return "/cagnotte";
					}
					Voyage v = new Voyage(site.getDaoVille().findByNom(v1),
							site.getDaoVille().findByNom(v2),
							site.getDaoTransport().findByNom(t),
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
							(Client) principal.getUtilisateur());
			return "redirect:/accueil";
		}

		////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////// Archives ///////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////

		else if (action.equals("archives")) {
			Client client = (Client) principal.getUtilisateur();
			site.archives(client);
			session.setAttribute("client", (Client) principal.getUtilisateur());
			model.addAttribute("aAfficher", "archives");
		}
		return "/cagnotte";	
	}
	
	
	
	@PostMapping("/cagnotte/checkLoginDest")
	@ResponseBody
	public String checkLoginDest(@RequestParam String loginDest) {
		Client c = (Client) site.getDaoUtilisateur().findByLogin(loginDest);
		if (c == null) {return "Y";}
		else {return "N";}
	}
}
