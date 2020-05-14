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
import fr.formation.model.Cagnotte;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cagnotte")
public class CagnotteApiController extends SiteController {
	
	@GetMapping
	public List<Cagnotte> findAll() {
		return this.site.getDaoCagnotte().findAll();
	}
	
	@GetMapping("/{id}")
	public Cagnotte findById(@PathVariable int id) {
		return this.site.getDaoCagnotte().findById(id).orElse(null);
	}

	@PostMapping
	public Cagnotte add(@Valid @RequestBody Cagnotte cagnotte, BindingResult result) {
		this.site.getDaoCagnotte().save(cagnotte);
		return cagnotte;
	}
	
	@PutMapping("/{id}")
	public Cagnotte update(@PathVariable int id, @RequestBody Cagnotte cagnotte) {
		cagnotte.setId(id);
		this.site.getDaoCagnotte().save(cagnotte);
		return cagnotte;
	}
	
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable int id) {
		try {
			this.site.getDaoCagnotte().deleteById(id);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
}