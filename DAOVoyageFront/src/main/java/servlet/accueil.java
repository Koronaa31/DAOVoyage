package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Client;
import model.Site;
import model.Utilisateur;


@WebServlet("/accueil")
public class accueil extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					doGet(request, response);
				}
				
			} else {request.getSession().setAttribute("error", "Y"); doGet(request, response);}
						
		} else if (action.equals("seDeconnecter")) {
			Site.getInstance().getPanier().clear();
			request.getSession().invalidate();
			
			doGet(request, response);
		}
		
	}

}
