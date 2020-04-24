package dao.jpa;

import java.util.List;

import dao.DAOTransport;
import model.Transport;

public class DAOTransportJPA extends DAOJpa implements DAOTransport {

	@Override
	public void insert(Transport object) {
		try {
			this.em.getTransaction().begin();		//On pense � d�marrer la transaction
			this.em.persist(object);
			this.em.getTransaction().commit();		//On pense � commit la transaction
		}
		catch (Exception e) {this.em.getTransaction().rollback();System.out.println("insert Transport pas marcher");}
	}

	@Override
	public Transport selectById(Integer id) {
		return this.em.find(Transport.class, id);
	}

	@Override
	public List<Transport> selectAll() {
		return this.em.createQuery("select t from Transport t", Transport.class).getResultList();
	}

	@Override
	public void update(Transport object) {
		try {
			this.em.getTransaction().begin();
			this.em.merge(object);
			this.em.getTransaction().commit();
		}
		catch (Exception e) {this.em.getTransaction().rollback();System.out.println("update Transport pas marcher");}
	}

	@Override
	public void delete(Integer id) {
		try {
			Transport TransportToRemove = new Transport();
			TransportToRemove.setId(id);

			this.em.getTransaction().begin();
			this.em.remove(this.em.merge(TransportToRemove));		//On attache car le Transport n'est pas attach�
			this.em.getTransaction().commit();
		}
		catch (Exception e) {this.em.getTransaction().rollback();System.out.println("delete Transport pas marcher");}
	}

	@Override
	public Transport selectByNom(String nom) {
		try {
			return this.em.createQuery("select t from Transport t where v.nom=?1", Transport.class).setParameter(1, nom).getSingleResult();
		} catch (Exception e) {return null;}
	}

}
