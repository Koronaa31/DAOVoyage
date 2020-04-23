package dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOJpa {

	private static EntityManagerFactory emf = null;
	protected EntityManager em = null;
	
	public DAOJpa() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (emf == null) {
			emf = Persistence.createEntityManagerFactory("DAOVoyageUnit");}
			em = emf.createEntityManager();}
			catch (Exception e) {}
	}
	
	public static void close() {
		emf.close();
	}
}