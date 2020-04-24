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


@WebServlet("/adminTransport")
public class adminTransport extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("transports", Site.getInstance().getDaoTransport().selectAll());
		this.getServletContext().getRequestDispatcher("/WEB-INF/adminTransport.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action.equals("ajouterTransport")) {
			String nomTransport = request.getParameter("nom_transport");
			double prix = Double.parseDouble(request.getParameter("prix"));
			double vitesse = Double.parseDouble(request.getParameter("vitesse"));
			
			Transport t = new Transport(nomTransport,prix,vitesse);
			
			Site.getInstance().ajoutTransport(t);
		}
		else if(action.equals("supprimerTransport")) {
			int id = Integer.parseInt(request.getParameter("id"));			
			Site.getInstance().getDaoTransport().delete(id);
		}
		
		doGet(request, response);
	}

}
