package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Client;
import model.Site;
import model.Voyage;

@WebServlet("/commandes")
public class commandes extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ajouter la liste de voyages du client (statut = commande) à la liste panier // Voyages.selectAllByClient
		String statut = "Commande";
		Client client = (Client) request.getSession().getAttribute("client");
		List<Voyage> loadPanier = Site.getInstance().getDaoVoyage().selectByClient(client, statut);
		System.out.println(loadPanier);
		request.getSession().setAttribute("commandes", loadPanier);
		this.getServletContext().getRequestDispatcher("/WEB-INF/commandes.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}