package com.taxes.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Entreprise implements Serializable {
  @Id	
  @GeneratedValue
  private long id;
  public long getId() {
	return id;
}
public void setId(long code) {
	this.id = code;
}
	@NotNull
	private String nom;
	  private String email;
	  private String raisonSociale;
	  @OneToMany(mappedBy="entreprise",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	  private Collection<Taxe> taxes;

public Entreprise( String nom, String email, String raisonSociale) {
	super();
	//this.code = code;
	this.nom = nom;
	this.email = email;
	this.raisonSociale = raisonSociale;
	
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getRaisonSociale() {
	return raisonSociale;
}
public void setRaisonSociale(String raisonSociale) {
	this.raisonSociale = raisonSociale;
}
public Collection<Taxe> getTaxes() {
	return taxes;
}
@JsonIgnore
public void setTaxes(Collection<Taxe> taxes) {
	this.taxes = taxes;
}
public Entreprise() {
	super();
	// TODO Auto-generated constructor stub
}
  
  
}
