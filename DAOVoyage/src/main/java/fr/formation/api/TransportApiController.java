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
import fr.formation.model.Transport;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/transport")
public class TransportApiController extends SiteController {
	
	@GetMapping
	public List<Transport> findAll() {
		return this.site.getDaoTransport().findAll();
	}
	
	@GetMapping("/{id}")
	public Transport findById(@PathVariable int id) {
		return this.site.getDaoTransport().findById(id).orElse(null);
	}

	@PostMapping
	public Transport add(@Valid @RequestBody Transport transport, BindingResult result) {
		this.site.getDaoTransport().save(transport);
		return transport;
	}
	
	@PutMapping("/{id}")
	public Transport update(@PathVariable int id, @RequestBody Transport transport) {
		transport.setId(id);
		this.site.getDaoTransport().save(transport);
		return transport;
	}
	
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable int id) {
		try {
			this.site.getDaoTransport().deleteById(id);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
}