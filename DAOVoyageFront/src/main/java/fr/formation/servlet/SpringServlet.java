package fr.formation.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.formation.dao.IDAOCagnotte;
import fr.formation.dao.IDAOTransport;
import fr.formation.dao.IDAOUtilisateur;
import fr.formation.dao.IDAOVille;
import fr.formation.dao.IDAOVoyage;

public abstract class SpringServlet extends HttpServlet {
	
	@Autowired
	protected IDAOCagnotte daoCagnotte;
	
	@Autowired
	protected IDAOVille daoVille;
	
	@Autowired
	protected IDAOVoyage daoVoyage;
	
	@Autowired
	protected IDAOUtilisateur daoUtilisateur;
	
	@Autowired
	protected IDAOTransport daoTransport;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport
			.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

}
