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
import fr.formation.model.Voyage;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/voyage")
public class VoyageApiController extends SiteController {
	
	@GetMapping
	public List<Voyage> findAll() {
		return this.site.getDaoVoyage().findAll();
	}
	
	@GetMapping("/{id}")
	public Voyage findById(@PathVariable int id) {
		return this.site.getDaoVoyage().findById(id).orElse(null);
	}

	@PostMapping
	public Voyage add(@Valid @RequestBody Voyage voyage, BindingResult result) {
		this.site.getDaoVoyage().save(voyage);
		return voyage;
	}
	
	@PutMapping("/{id}")
	public Voyage update(@PathVariable int id, @RequestBody Voyage voyage) {
		voyage.setId(id);
		this.site.getDaoVoyage().save(voyage);
		return voyage;
	}
	
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable int id) {
		try {
			this.site.getDaoVoyage().deleteById(id);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
}