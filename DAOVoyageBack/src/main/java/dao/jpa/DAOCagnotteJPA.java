package dao.jpa;

import java.util.List;

import dao.DAOCagnotte;
import model.Cagnotte;
import model.Client;

public class DAOCagnotteJPA extends DAOJpa implements DAOCagnotte {

	@Override
	public void insert(Cagnotte object) {
		try {
			this.em.getTransaction().begin();		
			this.em.persist(object);
			this.em.getTransaction().commit();	
		}
		catch (Exception e) {
			this.em.getTransaction().rollback();
			System.out.println("insert Cagnotte pas marcher");
			e.printStackTrace();
			}
	}

	@Override
	public Cagnotte selectById(Integer id) {
		return this.em.find(Cagnotte.class, id);
	}

	@Override
	public List<Cagnotte> selectAll() {
		return this.em.createQuery("select t from Cagnotte t", Cagnotte.class).getResultList();
	}

	@Override
	public void update(Cagnotte object) {
		try {
			this.em.getTransaction().begin();
			this.em.merge(object);
			this.em.getTransaction().commit();
		}
		catch (Exception e) {this.em.getTransaction().rollback();System.out.println("update Cagnotte pas marcher");}
	}

	@Override
	public void delete(Integer id) {
		try {
			Cagnotte CagnotteToRemove = new Cagnotte();
			CagnotteToRemove.setId(id);

			this.em.getTransaction().begin();
			this.em.remove(this.em.merge(CagnotteToRemove));		//On attache car le Cagnotte n'est pas attach�
			this.em.getTransaction().commit();
		}
		catch (Exception e) {this.em.getTransaction().rollback();System.out.println("delete Cagnotte pas marcher");}
	}

	@Override
	public List<Cagnotte> selectByDestinataire(Client destinataire) {
		try {
			return this.em.createQuery("select c from Cagnotte c where c.destinataire=?1 and c.sommeAPayer = 0", Cagnotte.class).setParameter(1, destinataire).getResultList();
		} catch (Exception e) {return null;}
	}

	@Override
	public List<Cagnotte> selectByInitiateur(Client initiateur) {
		try {
			return this.em.createQuery("select c from Cagnotte c where c.initiateur=?1", Cagnotte.class).setParameter(1, initiateur).getResultList();
		} catch (Exception e) {return null;}
	}

	@Override
	public List<Cagnotte> selectByParticipant(Client participant) {
		try {
			return this.em.createQuery("select distinct c from Cagnotte c left join c.participants p where p=?1", Cagnotte.class).setParameter(1, participant).getResultList();
		} catch (Exception e) {return null;}
	}

	@Override
	public List<Cagnotte> selectByDestinataireLogin(String login) {
		try {
			return this.em.createQuery("select c from Cagnotte c left join c.destinataire d where d.login=?1 and c.sommeAPayer <> 0", Cagnotte.class).setParameter(1, login).getResultList();
		} catch (Exception e) {return null;}
	}

}
