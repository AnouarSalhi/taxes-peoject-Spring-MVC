package com.taxes.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taxes.dao.EntrepriseRepository;
import com.taxes.dao.TaxeRepository;
import com.taxes.entities.Entreprise;

@RestController
public class TaxeRestController {

	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@RequestMapping("/rest")
	public List<Entreprise> list() {
		
		return entrepriseRepository.findAll();
	}
}
