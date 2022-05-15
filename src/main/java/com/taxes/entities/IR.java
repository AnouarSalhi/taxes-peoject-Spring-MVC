package com.taxes.entities;

import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("RI")
public class IR extends Taxe{

	public IR() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IR(String titre, Date dateTaxe, double montant, Entreprise entreprise) {
		super(titre, dateTaxe, montant, entreprise);
		// TODO Auto-generated constructor stub
	}

}
