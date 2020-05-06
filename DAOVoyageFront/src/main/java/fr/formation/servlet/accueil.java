package fr.formation.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.model.Client;
import fr.formation.model.Site;
import fr.formation.model.Transport;
import fr.formation.model.Utilisateur;
import fr.formation.model.Ville;

//@WebServlet("/accueil")
public class accueil extends SpringServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Ville> Villes = daoVille.findAll();
		List<String> villes = new ArrayList<>();
		for (Ville v : Villes) {
			villes.add(v.getNom());}
		request.setAttribute("villes", villes);
		
		List<Transport> Transports = daoTransport.findAll();
		List<String> transports = new ArrayList<>();
		for (Transport t : Transports) {
			transports.add(t.getNom());}
		request.setAttribute("transports", transports);
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
		if (action.equals("seConnecter")) {
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			Utilisateur u = Site.getInstance().checkConnect(login, password);
			if (u != null) {
				request.getSession().setAttribute("login", u.getLogin());
				request.getSession().setAttribute("id", u.getId());
				request.getSession().setAttribute("isConnect", "Y");
				if (u.getTypeCompte().equals("Admin")) {
					this.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
				}
			else if (u.getTypeCompte().equals("Client")) {
				Client client = (Client) u;
				request.getSession().setAttribute("client", client);
				doGet(request, response);
			}
			} else {request.getSession().setAttribute("error", "Y"); doGet(request, response);}
		}
		else if (action.equals("seDeconnecter")) {
			Site.getInstance().getPanier().clear();
			request.getSession().invalidate();
			
			doGet(request, response);
		}
	}
}