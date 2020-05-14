package fr.formation.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.controller.SiteController;
import fr.formation.model.Utilisateur;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/utilisateur")
public class UtilisateurApiController extends SiteController {
	
	@GetMapping
	public List<Utilisateur> findAll() {
		return this.site.getDaoUtilisateur().findAll();
	}
	
	@GetMapping("/{id}")
	public Utilisateur findById(@PathVariable int id) {
		return this.site.getDaoUtilisateur().findById(id).orElse(null);
	}

	@PostMapping
	public Utilisateur add(@Valid @RequestBody Utilisateur utilisateur, BindingResult result) {
		this.site.getDaoUtilisateur().save(utilisateur);
		return utilisateur;
	}
	
	@PutMapping("/{id}")
	public Utilisateur update(@PathVariable int id, @RequestBody Utilisateur utilisateur) {
		utilisateur.setId(id);
		this.site.getDaoUtilisateur().save(utilisateur);
		return utilisateur;
	}
	
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable int id) {
		try {
			this.site.getDaoUtilisateur().deleteById(id);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
}