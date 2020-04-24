package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Site;
import model.Transport;
import model.Ville;


@WebServlet("/admin")
public class admin extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action.equals("ajoutVille")) {
			String nomVille = request.getParameter("nom_ville");
			double latitude = Double.parseDouble(request.getParameter("latitude"));
			double longitude = Double.parseDouble(request.getParameter("longitude"));
			
			Ville ville = new Ville(nomVille,latitude,longitude);
			
			Site.getInstance().ajoutVille(ville);
		}
		else if(action.equals("ajoutTransport")) {
			String nomTransport = request.getParameter("nom_transport");
			double prixAuKm = Double.parseDouble(request.getParameter("prix"));
			double vitesse = Double.parseDouble(request.getParameter("vitesse"));
			
			Transport t = new Transport(nomTransport,prixAuKm,vitesse);
			
			Site.getInstance().ajoutTransport(t);
		}
		
		
		doGet(request, response);
	}

}
