package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Site;
import model.Ville;


@WebServlet("/adminVille")
public class adminVille extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("villes", Site.getInstance().getDaoVille().selectAll());
		this.getServletContext().getRequestDispatcher("/WEB-INF/adminVille.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action.equals("ajouterVille")) {
			String nomVille = request.getParameter("nom_ville");
			double latitude = Double.parseDouble(request.getParameter("latitude"));
			double longitude = Double.parseDouble(request.getParameter("longitude"));
			
			Ville ville = new Ville(nomVille,longitude,latitude);
			
			Site.getInstance().ajoutVille(ville);
		}
		else if(action.equals("supprimerVille")) {
			int id = Integer.parseInt(request.getParameter("id"));			
			Site.getInstance().getDaoVille().delete(id);
		}
		
		
		doGet(request, response);
	}

}
