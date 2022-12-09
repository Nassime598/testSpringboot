package com.project.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.exception.ResourceNotFoundException;
import com.project.demo.models.Entreprise;
import com.project.demo.models.Profile;
import com.project.demo.services.EntrepriseServices;

@RestController
@RequestMapping("/api")
public class EntrepriseController {
	
	@Autowired
	private EntrepriseServices entrepriseService;
	
    // Save operation
    @PostMapping("/entreprises")
    public Entreprise saveEntreprise(@Validated @RequestBody Entreprise entreprise)
    {
        return entrepriseService.SaveEntreprise(entreprise);
    }
 
    @GetMapping("/entreprises/{tvanumero}")
    public ResponseEntity<Entreprise>  fetchEntrepriseById(@PathVariable("tvanumero") long tvanumero) {
        Entreprise E = entrepriseService.findById(tvanumero)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Profile with id = " + tvanumero));

            return new ResponseEntity<>(E, HttpStatus.OK);
      }
    
    // Read operation
    @GetMapping("/entreprises")
    public ResponseEntity<List<Entreprise>> fetchEntrepriseList()
    {
    	 
    	    List<Entreprise> entreprise = new ArrayList<Entreprise>();

    	    entrepriseService.fetchEntrepriseList().forEach(e -> entreprise.add(e));

    	    if (entreprise.isEmpty()) {
    	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	    }

    	    return new ResponseEntity<>(entreprise, HttpStatus.OK);
    }
    
 
    // Update operation
    @PutMapping("/entreprises/{id}")
    public ResponseEntity<Entreprise> updateEntreprise(@PathVariable("TVAnumero") long id, @RequestBody Entreprise entrepriseRequest) {
      Entreprise e = entrepriseService.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("TagId " + id + "not found"));

      e.setAddress(entrepriseRequest.getAddress());

      return new ResponseEntity<>(entrepriseService.SaveEntreprise(e), HttpStatus.OK);
    }
    
    @DeleteMapping("/entreprises/{id}")
    public String deleteEntrepriseById(@PathVariable("id")
                                       Long TvaNumber)
    {
    	entrepriseService.DeleteEntrepriseById(
    			TvaNumber);
        return "Profile Deleted Successfully";
    }
   
    
    @GetMapping("/profiles/{ProfileId}/Entreprises")
    public ResponseEntity<List<Entreprise>> getAllEntreprisesByProfileId(@PathVariable(value = "ProfileId") Long profileId) {
      if (entrepriseService.findById(profileId) == null) {
        throw new ResourceNotFoundException("Not found Profile with id = " + profileId);
      }
      List<Entreprise> entreprise = entrepriseService.findEntrepriseByProfileId(profileId);
      return new ResponseEntity<>(entreprise, HttpStatus.OK);
    }

}
