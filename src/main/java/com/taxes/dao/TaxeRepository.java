package com.taxes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taxes.entities.Entreprise;
import com.taxes.entities.Taxe;

public interface TaxeRepository extends JpaRepository<Taxe,Long>{
  public List<Taxe> findByEntreprise(Entreprise e);
}
