package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utilisateur;
import model.Utilisateur;
import model.Client;
import model.Site;


@WebServlet("/inscription")
public class inscription extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adresseMail=request.getParameter("mail");
		Utilisateur u = Site.getInstance().checkMail(adresseMail);
		if (u == null) {
			String login=request.getParameter("login");
			String password=request.getParameter("password");
			u = new Client(login, password, adresseMail);
			Site.getInstance().inscription(u);
			request.getSession().setAttribute("isConnect", null);
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);	
		} else {
			request.getSession().setAttribute("isConnect", "N");
			doGet(request, response);
		}
	}
}