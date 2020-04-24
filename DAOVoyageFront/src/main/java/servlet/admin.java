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
		if(action.equals("modifVille")) {
			this.getServletContext().getRequestDispatcher("/adminVille").forward(request, response);
		}
		else if(action.equals("modifTransport")) {
			this.getServletContext().getRequestDispatcher("/adminTransport").forward(request, response);
		}
	}

}
