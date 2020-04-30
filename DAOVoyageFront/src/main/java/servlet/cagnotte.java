package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.model.Cagnotte;
import fr.formation.model.Client;
import fr.formation.model.Site;
import fr.formation.model.Transport;
import fr.formation.model.Ville;
import fr.formation.model.Voyage;


@WebServlet("/cagnotte")
public class cagnotte extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/cagnotte.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equals("accueilCreation")) {
			request.getSession().setAttribute("errorDes", "N");
			request.getSession().setAttribute("aAfficher", "creation");
			request.getSession().setAttribute("checkLogin", "wrong");
		} else if(action.equals("checkLogin")) {
			String login = request.getParameter("loginDestinataire");
			Client c = (Client) Site.getInstance().getDaoUtilisateur().findByLogin(login);
			if (c != null){
				request.getSession().setAttribute("errorDes", "N");
				request.getSession().setAttribute("destinataire", c);
				request.getSession().setAttribute("checkLogin", "right");
				List<Ville> villes = Site.getInstance().getDaoVille().findAll();
				List<Transport> transports = Site.getInstance().getDaoTransport().findAll();
				request.getSession().setAttribute("villes1", villes);
				request.getSession().setAttribute("villes2", villes);
				request.getSession().setAttribute("transports", transports);
			} else {
				request.getSession().setAttribute("errorDes", "Y");
			}
		} else if(action.equals("creationCagnotte")) {
			String villeDep = request.getParameter("v1");
			String villeArr = request.getParameter("v2");
			String transport = request.getParameter("t");
			Client initiateur = (Client) request.getSession().getAttribute("client");
			Client destinataire = (Client) request.getSession().getAttribute("destinataire");
			Ville v1 = Site.getInstance().getDaoVille().findByNom(villeDep);
			Ville v2 = Site.getInstance().getDaoVille().findByNom(villeArr);
			Transport t = Site.getInstance().getDaoTransport().findByNom(transport);
			Voyage v = new Voyage(v1, v2, t, destinataire);
			v.setStatut("Cagnotte");
			initiateur.creationCagnotte(v);
			
			
		} else if(action.equals("archives")) {
			request.getSession().setAttribute("aAfficher", "archives");
			Client client = (Client) request.getSession().getAttribute("client");
			client.archives();
			request.getSession().setAttribute("cagnottesDestinataire", client.getCagnottesDestinataire());
			request.getSession().setAttribute("cagnottesInitiateur", client.getCagnottesInitiateur());
			request.getSession().setAttribute("cagnottesParticipant", client.getCagnottesParticipant());
		
		
		} else if(action.equals("accueilParticipation")) {
			request.getSession().setAttribute("aAfficher", "participation");
			request.getSession().setAttribute("checkIdDestinataire", "wrong");
		} else if(action.equals("checkIdDestinataire")) {
			String login = request.getParameter("loginDestinataire");
			List<Cagnotte> listeCagnotteDestinataire = Site.getInstance().getDaoCagnotte().findByDestinataireLogin(login);
			if(!listeCagnotteDestinataire.isEmpty()) {
				request.getSession().setAttribute("errorDes", "N");
				request.getSession().setAttribute("checkIdDestinataire", "right");
				request.getSession().setAttribute("listeCagnotteDestinataire", listeCagnotteDestinataire);
			} else {request.getSession().setAttribute("errorDes", "Y");}
		} else if(action.equals("sommePayee")) {
			double sommePayee = Double.parseDouble(request.getParameter("sommePayee"));
			Client participant = (Client) request.getSession().getAttribute("client");
			int id = Integer.parseInt(request.getParameter("cagnotteChoisie"));
			Cagnotte c = Site.getInstance().getDaoCagnotte().findById(id);
			participant.participer(sommePayee, c);
			List<Cagnotte> listeCagnotteDestinataire = (List<Cagnotte>) request.getSession().getAttribute("listeCagnotteDestinataire");
			Client destinataire = listeCagnotteDestinataire.get(0).getDestinataire();
			listeCagnotteDestinataire = Site.getInstance().getDaoCagnotte().findByDestinataireLogin(destinataire.getLogin());
			if(!listeCagnotteDestinataire.isEmpty()) {
				request.getSession().setAttribute("listeCagnotteDestinataire", listeCagnotteDestinataire);
			} else {request.getSession().setAttribute("cagnotteIsEmpty", "Y");}
		}
		
		
		doGet(request, response);
	}

}
