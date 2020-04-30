package fr.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Cagnotte;
import fr.formation.model.Client;

public interface IDAOCagnotte extends JpaRepository<Cagnotte, Integer> {

	@Query("select c from Cagnotte c where c.destinataire=?1 and c.sommeAPayer = 0")
	public List<Cagnotte> findByDestinataire(Client destinataire);
	
	@Query("select c from Cagnotte c where c.destinataire=?1 and c.sommeAPayer <> 0")
	public List<Cagnotte> findByDestinataireAPayer(Client destinataire);

	public List<Cagnotte> findByInitiateur(Client initiateur);

	@Query("select distinct c from Cagnotte c left join c.participants p where p=?1")
	public List<Cagnotte> findByParticipant(Client participant);
	

}
