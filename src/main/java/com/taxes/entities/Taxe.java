package com.taxes.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",
discriminatorType=DiscriminatorType.STRING,length=3)

public class Taxe implements Serializable{
	@Id	@GeneratedValue
	private long id;
	private String titre;
	private Date dateTaxe;
	private double montant;
	@ManyToOne
	@JoinColumn(name="CODE_ENT")
	private Entreprise entreprise;
	public Taxe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Taxe(String titre, Date dateTaxe, double montant, Entreprise entreprise) {
		super();
		this.titre = titre;
		this.dateTaxe = dateTaxe;
		this.montant = montant;
		this.entreprise = entreprise;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Date getDateTaxe() {
		return dateTaxe;
	}
	public void setDateTaxe(Date dateTaxe) {
		this.dateTaxe = dateTaxe;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	

}