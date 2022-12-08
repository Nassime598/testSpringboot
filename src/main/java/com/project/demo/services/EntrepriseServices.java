package com.project.demo.services;

import java.util.List;
import java.util.Optional;

import com.project.demo.models.Entreprise;


public interface EntrepriseServices {
	
	Entreprise SaveEntreprise(Entreprise entreprise);
	
	List<Entreprise> fetchEntrepriseList();
	
	Entreprise updateEntreprise(Entreprise entreprise , Long Tvanumber);
	
	void DeleteEntrepriseById(Long Tvanumber);
	
	Optional<Entreprise> findById(Long Tvanumber);
	
	List<Entreprise> findEntrepriseByProfileId(Long Tvanumber);


}
