package dao.jpa;

import java.util.List;

import dao.DAOCommande;
import model.Voyage;

public class DAOCommandeJPA extends DAOJpa implements DAOCommande {
	
	@Override
	public void insert(Voyage v) {
		
		try {
			this.em.getTransaction().begin();
			this.em.persist(v);
			this.em.getTransaction().commit();
			System.out.println("Commande ajoutée à la base de données");
		}
		catch(Exception e) {this.em.getTransaction().rollback();System.out.println("Erreur -> Commande non ajoutée à la base de données");}		
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
	public void update(Voyage t) {
		//nope
	}

	@Override
	public void delete(Integer id) {
		//nope
	}
	
	
	public List<Voyage> selectAllByClient(Integer id) {
        return this.em.createQuery("select v from Voyage v left join v.client c where c.id=?1", Voyage.class).setParameter(1, id).getResultList();
    }
	
}
