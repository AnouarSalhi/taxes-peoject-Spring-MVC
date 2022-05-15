package com.taxes.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.taxes.entities.Entreprise;

public interface EntrepriseRepository extends JpaRepository <Entreprise,Long>{
  public Page<Entreprise> findByNomContains(String mc,Pageable pageable);
  //public Page<Entreprise> deleteById(long code);
  
}
