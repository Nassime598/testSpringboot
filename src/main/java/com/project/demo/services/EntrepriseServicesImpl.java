package com.project.demo.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.project.demo.models.Entreprise;
import com.project.demo.repository.EntrepriseRepository;

@Service
public class EntrepriseServicesImpl implements EntrepriseServices{

    @Autowired
    private EntrepriseRepository entrepriseRepository;
	
    @Override
	public Entreprise SaveEntreprise(Entreprise entreprise) {
		return entrepriseRepository.save(entreprise);
	}

	@Override
	public List<Entreprise> fetchEntrepriseList() {
		return (List<Entreprise>)entrepriseRepository.findAll();
	}

	@Override
	public Entreprise updateEntreprise(Entreprise entreprise, Long TvaNumber) {
		
		Entreprise EntrepriseDB
         = entrepriseRepository.findById(TvaNumber)
               .get();

        if (Objects.nonNull(entreprise.getAddress())
            && !"".equalsIgnoreCase(
            		entreprise.getAddress())) {
        	EntrepriseDB.setAddress(
        			entreprise.getAddress());
        }
 
        return entrepriseRepository.save(EntrepriseDB);
		
		
	}

	@Override
	public void DeleteEntrepriseById(Long TvaNumber) {
		entrepriseRepository.deleteById(TvaNumber);
		
	}

	@Override
	public Optional<Entreprise> findById(Long Tvanumber) {
		
		return entrepriseRepository.findById(Tvanumber);
		
		        
	}

	@Override
	public List<Entreprise> findEntrepriseByProfileId(Long Tvanumber) {
		
		return entrepriseRepository.findEntrepriseByProfilesId(Tvanumber);
	}

}
