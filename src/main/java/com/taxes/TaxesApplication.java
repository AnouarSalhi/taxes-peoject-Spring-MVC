package com.taxes;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.taxes.dao.EntrepriseRepository;
import com.taxes.dao.TaxeRepository;
import com.taxes.entities.Entreprise;
import com.taxes.entities.TVA;

@SpringBootApplication
public class TaxesApplication implements CommandLineRunner {
	@Autowired
    EntrepriseRepository entrepriseRepository;
	@Autowired
    TaxeRepository taxeRepository;
	public static void main(String[] args) {
		SpringApplication.run(TaxesApplication.class, args);
	}
                                        
	@Override
	public void run(String... args) throws Exception {
		Entreprise e1=entrepriseRepository.save( new Entreprise("R1","r1@email.com","SARL"));
		Entreprise e2=entrepriseRepository.save( new Entreprise("R2","r2@email.com","SAS"));
		Entreprise e3=entrepriseRepository.save( new Entreprise("R4","r1@email.com","SARL"));
		Entreprise e4=entrepriseRepository.save( new Entreprise("R5","r2@email.com","SAS"));
		
		taxeRepository.save( new TVA("TVA  abilation1",new Date(),345.87,e1));
		taxeRepository.save( new TVA("TVA  abilation2",new Date(),345.87,e1));
		taxeRepository.save( new TVA("TVA  abilation3",new Date(),345.87,e1));
		taxeRepository.save( new TVA("TVA  op",new Date(),345.87,e2));
		
	}

}
