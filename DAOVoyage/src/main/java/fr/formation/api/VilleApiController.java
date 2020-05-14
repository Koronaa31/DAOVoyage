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
import fr.formation.model.Ville;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/ville")
public class VilleApiController extends SiteController {
	
	@GetMapping
	public List<Ville> findAll() {
		return this.site.getDaoVille().findAll();
	}
	
	@GetMapping("/{id}")
	public Ville findById(@PathVariable int id) {
		return this.site.getDaoVille().findById(id).orElse(null);
	}

	@PostMapping
	public Ville add(@Valid @RequestBody Ville ville, BindingResult result) {
		this.site.getDaoVille().save(ville);
		return ville;
	}
	
	@PutMapping("/{id}")
	public Ville update(@PathVariable int id, @RequestBody Ville ville) {
		ville.setId(id);
		this.site.getDaoVille().save(ville);
		return ville;
	}
	
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable int id) {
		try {
			this.site.getDaoVille().deleteById(id);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
}