package com.project.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Entreprise;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

	List<Entreprise> findEntrepriseByProfilesId(Long Id);
	
}
