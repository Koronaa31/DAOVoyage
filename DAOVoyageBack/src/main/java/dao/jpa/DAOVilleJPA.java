package dao.jpa;

import java.util.List;

import dao.DAOVille;
import model.Ville;
import model.Ville;

public class DAOVilleJPA extends DAOJpa implements DAOVille {

	@Override
	public void insert(Ville object) {
		try {
			this.em.getTransaction().begin();		//On pense à démarrer la transaction
			this.em.persist(object);
			this.em.getTransaction().commit();		//On pense à commit la transaction
		}
		catch (Exception e) {this.em.getTransaction().rollback();System.out.println("insert Ville pas marcher");}
	}

	@Override
	public Ville selectById(Integer id) {
		return this.em.find(Ville.class, id);
	}

	@Override
	public List<Ville> selectAll() {
		return this.em.createQuery("select v from Ville v", Ville.class).getResultList();
	}

	@Override
	public void update(Ville object) {
		try {
			this.em.getTransaction().begin();
			this.em.merge(object);
			this.em.getTransaction().commit();
		}
		catch (Exception e) {this.em.getTransaction().rollback();System.out.println("update Ville pas marcher");}
	}

	@Override
	public void delete(Integer id) {
		try {
			Ville VilleToRemove = new Ville();
			VilleToRemove.setId(id);

			this.em.getTransaction().begin();
			this.em.remove(this.em.merge(VilleToRemove));		//On attache car le Ville n'est pas attaché
			this.em.getTransaction().commit();
		}
		catch (Exception e) {this.em.getTransaction().rollback();System.out.println("delete Ville pas marcher");}
	}

	@Override
	public Ville selectByNom(String nom) {
		try {
			return this.em.createQuery("select v from Ville v where v.nom=?1", Ville.class).setParameter(1, nom).getSingleResult();
		} catch (Exception e) {return null;}
	}

}
