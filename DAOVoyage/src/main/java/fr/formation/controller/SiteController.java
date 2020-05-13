package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.model.Site;

public abstract class SiteController {
	
	@Autowired
	protected Site site;
	
}
