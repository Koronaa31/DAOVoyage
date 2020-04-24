package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import dao.*;

@WebServlet("/recherche")
public class recherche extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//affiche la liste de voyages
		request.setAttribute("panier", Site.getInstance().getPanier().size());
		request.setAttribute("recherche", Site.getInstance().getVoyage());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//recupere les entrees du formulaire, reconstruit les villes, transport-> voyages, appelle les fonctions recherche, remplit la liste voyage, l'affiche (via doGet)
		
		Site.getInstance().getVoyage().clear();
		
		String villeDep = request.getParameter("v1");
		String villeArr = request.getParameter("v2");
		String transport = request.getParameter("t");
		Ville v1 = Site.getInstance().getDaoVille().selectByNom(villeDep);
		
		if (!villeArr.equals("N"))
		{
			Ville v2 = Site.getInstance().getDaoVille().selectByNom(villeArr);
			
			if (!transport.equals("N"))
			{
				Transport t = Site.getInstance().getDaoTransport().selectByNom(transport);
				Site.getInstance().research(v1,v2,t);
			}
			
			else
			{
				Site.getInstance().research(v1,v2);
			}
		}
		else
		{
			Site.getInstance().research(v1);
		}
		
		doGet(request,response);
	}

}
