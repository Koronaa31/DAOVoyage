package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Client;
import model.Site;
import model.Ville;


@WebServlet("/accueil")
public class accueil extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Ville> villes = Site.getInstance().getDaoVille().selectAll();
		ArrayList nomsVilles = new ArrayList<>();
		for(Ville v : villes) {
			nomsVilles.add(v.getNom());
			}
		/*
		List<Transport> transports = Site.getInstance().getDaoTransport().selectAll();
		ArrayList nomsTransports = new ArrayList<>();
		for(Transport t : transports) {
			nomsTransports.add(t.getNom());												//En attente de la classe Transport
			}
		request.getSession().setAttribute("transports", nomsTransports);
		*/
		request.getSession().setAttribute("villes1", nomsVilles);
		request.getSession().setAttribute("villes2", nomsVilles);
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
		if (action.equals("seConnecter")) {
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			Client c = (Client) Site.getInstance().checkConnect(login, password);
			if (c != null) {
				request.getSession().setAttribute("login", c.getLogin());
				request.getSession().setAttribute("id", c.getId());
				request.getSession().setAttribute("isConnect", "Y");
			} else {request.getSession().setAttribute("error", "Y");}
		} else if (action.equals("seDeconnecter")) {
			Site.getInstance().getPanier().clear();
			request.getSession().invalidate();
		}
		doGet(request, response);
	}

}
