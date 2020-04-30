package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOVille;
import dao.jdbc.DAOVilleJDBC;
import fr.formation.model.*;

@WebServlet("/panier")
public class panier extends HttpServlet {
	       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//affiche la liste panier
		
		request.getSession().setAttribute("panier", Site.getInstance().getPanier());
		double total=0;
		for(Voyage voy : Site.getInstance().getPanier())
		{
			total+=voy.getPrix();
		}
		request.setAttribute("total", Math.floor(total*100)/100);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		if(action.equals("deletePanier"))
		{
			int nb = Integer.parseInt(request.getParameter("nb"));
			
			Site.getInstance().getPanier().remove(nb);
		}
		else if(action.equals("addPanier"))
		{
			String villeDep = request.getParameter("v1");
			String villeArr = request.getParameter("v2");
			String transport = request.getParameter("t");	
			Ville v1 = Site.getInstance().getDaoVille().findByNom(villeDep);
			Ville v2 = Site.getInstance().getDaoVille().findByNom(villeArr);
			Transport t = Site.getInstance().getDaoTransport().findByNom(transport);
			
			Voyage voy = new Voyage(v1,v2,t);
			
			Site.getInstance().choix(voy);
        }
		else if(action.equals("paiement"))
		{	
			List<Voyage> commande = Site.getInstance().getPanier();
			for(Voyage v : commande)
			{
			Ville v1 = v.getV1();
			Ville v2 = v.getV2();
			Transport t = v.getT();
			
			Voyage voyage = new Voyage(v1,v2,t);
			voyage.setStatut("Commande");
			System.out.println("Voyage 1 : \n"+voyage);
			
			//int id = (int) request.getSession().getAttribute("id");
			//Client client = (Client) Site.getInstance().getDaoUtilisateur().selectById(id);
			Client client = (Client) request.getSession().getAttribute("client");
	        voyage.setClient(client);
	        System.out.println(client);
			System.out.println("Voyage 2 : \n"+voyage);
	        
			Site.getInstance().getDaoVoyage().save(voyage);
			}
			
			Site.getInstance().getPanier().clear();
		}
		
		doGet(request,response);
	}

}
