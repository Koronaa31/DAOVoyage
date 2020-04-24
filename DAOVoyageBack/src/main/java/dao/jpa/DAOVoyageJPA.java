package dao.jpa;

import java.util.List;

import dao.DAOVoyage;
import model.Voyage;

public class DAOVoyageJPA extends DAOJpa implements DAOVoyage {

	@Override
	public void insert(Voyage object) {
		try {
			this.em.getTransaction().begin();		//On pense à démarrer la transaction
			this.em.persist(object);
			this.em.getTransaction().commit();		//On pense à commit la transaction
		}
		catch (Exception e) {this.em.getTransaction().rollback();System.out.println("insert Voyage pas marcher");}
	}

	@Override
	public Voyage selectById(Integer id) {
		return this.em.find(Voyage.class, id);
	}

	@Override
	public List<Voyage> selectAll() {
		return this.em.createQuery("select v from Voyage v", Voyage.class).getResultList();
	}

	@Override
	public void update(Voyage object) {
		try {
			this.em.getTransaction().begin();
			this.em.merge(object);
			this.em.getTransaction().commit();
		}
		catch (Exception e) {this.em.getTransaction().rollback();System.out.println("update Voyage pas marcher");}
	}

	@Override
	public void delete(Integer id) {
		try {
			Voyage VoyageToRemove = new Voyage();
			VoyageToRemove.setId(id);

			this.em.getTransaction().begin();
			this.em.remove(this.em.merge(VoyageToRemove));		//On attache car le Voyage n'est pas attaché
			this.em.getTransaction().commit();
		}
		catch (Exception e) {this.em.getTransaction().rollback();System.out.println("delete Voyage pas marcher");}
	}
}