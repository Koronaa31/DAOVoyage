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
import model.Transport;
import model.Ville;


@WebServlet("/cagnotte")
public class cagnotte extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/cagnotte.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("accueilCreation")) {
			request.getSession().setAttribute("aAfficher", "creation");
			request.getSession().setAttribute("checkLogin", "wrong");
		} else if(action.equals("archives")) {
			request.getSession().setAttribute("aAfficher", "archives");
		} else if(action.equals("accueilParticipation")) {
			request.getSession().setAttribute("aAfficher", "participation");
		} else if(action.equals("checkLogin")) {
			String login = request.getParameter("loginDestinataire");
			Client c = (Client) Site.getInstance().getDaoUtilisateur().selectByLogin(login);
			if (c != null){
				request.getSession().setAttribute("destinataire", c);
				request.getSession().setAttribute("checkLogin", "right");
				List<Ville> villes = Site.getInstance().getDaoVille().selectAll();
				List<Transport> transports = Site.getInstance().getDaoTransport().selectAll();
				request.getSession().setAttribute("villes1", villes);
				request.getSession().setAttribute("villes2", villes);
				request.getSession().setAttribute("transports", transports);
			}
		}
		doGet(request, response);
	}

}
