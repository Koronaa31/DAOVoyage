package dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOJpa {

	protected static EntityManagerFactory emf = null;
	protected EntityManager em = null;

	public DAOJpa() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (emf == null) {
				emf = Persistence.createEntityManagerFactory("DAOVoyageUnit");
			}
			em = emf.createEntityManager();}
		catch (Exception e) {e.printStackTrace();}
	}

	public static void close() {
		emf.close();
	}

	public static EntityManagerFactory getEmf() {
		return emf;
	}

	public static void setEmf(EntityManagerFactory emf) {
		DAOJpa.emf = emf;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}


}