package dao.jpa;

import java.util.List;

import dao.DAOUtilisateur;
import model.Client;
import model.Utilisateur;
import model.Ville;
import model.Client;

public class DAOUtilisateurJPA extends DAOJpa implements DAOUtilisateur {

	@Override
	public void insert(Utilisateur object) {
		try {
			this.em.getTransaction().begin();		//On pense à démarrer la transaction
			this.em.persist(object);
			this.em.getTransaction().commit();		//On pense à commit la transaction
		}
		catch (Exception e) {e.printStackTrace();this.em.getTransaction().rollback();System.out.println("insert Utilisateur pas marcher");}
	}

	@Override
	public Utilisateur selectById(Integer id) {
		return this.em.find(Client.class, id);
	}

	@Override
	public List<Utilisateur> selectAll() {
		return this.em.createQuery("select u from Utilisateur u", Utilisateur.class).getResultList();
	}

	@Override
	public void update(Utilisateur object) {
		try {
			this.em.getTransaction().begin();
			this.em.merge(object);
			this.em.getTransaction().commit();
		}
		catch (Exception e) {this.em.getTransaction().rollback();System.out.println("update Utilisateur pas marcher");}
	}

	@Override
	public void delete(Integer id) {
		try {
			Client UtilisateurToRemove = new Client();
			UtilisateurToRemove.setId(id);

			this.em.getTransaction().begin();
			this.em.remove(this.em.merge(UtilisateurToRemove));		//On attache car le Utilisateur n'est pas attaché
			this.em.getTransaction().commit();
		}
		catch (Exception e) {this.em.getTransaction().rollback();System.out.println("delete Utilisateur pas marcher");}
	}

	@Override
	public Utilisateur selectByAdresseMail(String adresseMail) {
		try {
			return this.em.createQuery("select c from Utilisateur c where c.adresseMail=?1", Client.class).setParameter(1, adresseMail).getSingleResult();
		} catch (Exception e) {return null;}
	}

	@Override
	public Utilisateur selectByLoginPassword(String login, String password) {
		try {
			return this.em.createQuery("select c from Utilisateur c where c.login=?1 and c.password=?2", Client.class).setParameter(1, login).setParameter(2, password).getSingleResult();
		} catch (Exception e) {return null;}
	}

}
