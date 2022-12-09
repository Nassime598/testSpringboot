package com.project.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.project.demo.services.ProfileServices;

@RestController 
@RequestMapping("/api")
public class ProfileController {
	
	@Autowired
	private ProfileServices profileservice ;
	@Autowired
	private EntrepriseServices entrepriseservice ;
	
    // Save operation
    @PostMapping("/profiles")
    public Profile saveProfile(@Validated @RequestBody Profile profile)
    {
        return profileservice.SaveProfile(profile);
    }
 
    // Read operation
    @GetMapping("/profiles")
    public List<Profile> fetchProfileList()
    {
        return profileservice.fetchProfilesList();
    }
    @GetMapping("/profiles/{id}")
    public ResponseEntity<Profile>  fetchProfileById(@PathVariable("id") long id) {
        Profile P = profileservice.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Profile with id = " + id));

            return new ResponseEntity<>(P, HttpStatus.OK);
      }
 
    // Update operation
    /*@PutMapping("/profiles/{id}")
    public Profile
    updateProfile(@RequestBody Profile profile,
                     @PathVariable("id") Long ProfileId)
    {
        return profileservice.updateProfile(
            profile, ProfileId);
    }*/
    @PutMapping("/profiles/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable("id") long id, @RequestBody Profile profile) {
    	Profile _P = profileservice.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Not found Profile with id = " + id));

      _P.setNom(profile.getNom());
      _P.setPrenom(profile.getPrenom());
      _P.setAddress(profile.getAddress());
      _P.setFreelance(profile.getFreelance());
      return new ResponseEntity<>(profileservice.SaveProfile(_P), HttpStatus.OK);
    }
 
    // Delete operation
    
    @DeleteMapping("/profiles")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
    	profileservice.deleteAll();
      
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping("/profiles/{id}")
    public String deleteProfileById(@PathVariable("id")
                                       Long ProfileId)
    {
    	profileservice.DeleteProfileById(
    			ProfileId);
        return "Profile Deleted Successfully";
    }
    
    @DeleteMapping("/profiles/{profileId}/entreprises/{tvanumero}")
    public ResponseEntity<HttpStatus> deleteEntrepriseFromProfile(@PathVariable(value = "Id") Long ProfileId, @PathVariable(value = "TVAnumero") Long TVAnumero) {
      Profile p = profileservice.findById(ProfileId)
          .orElseThrow(() -> new ResourceNotFoundException("Not found Profile with id = " + ProfileId));
      
      p.RemoveEntreprise(TVAnumero);
      profileservice.SaveProfile(p);
      
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
    @PostMapping("/profiles/{profileId}/entreprises")
    public ResponseEntity<Entreprise> addEntreprise(@PathVariable(value = "profileId") Long profileId, @RequestBody Entreprise entrepriseRequest) {
     Entreprise E = profileservice.findById(profileId).map(profile -> {
        long TVAnumero = entrepriseRequest.getTVAnumero();
        
        
        if (TVAnumero != 0L) {
          Entreprise _e = entrepriseservice.findById(TVAnumero)
              .orElseThrow(() -> new ResourceNotFoundException("Not found Entreprise with id = " + TVAnumero));
          profile.addEntreprise(_e);
          profileservice.SaveProfile(profile);
          return _e;
        }
        
        // add and create new Tag
        profile.addEntreprise(entrepriseRequest);
        return entrepriseservice.SaveEntreprise(entrepriseRequest);
      }).orElseThrow(() -> new ResourceNotFoundException("Not found profile with id = " + profileId));

      return new ResponseEntity<>(E, HttpStatus.CREATED);
    }

	

}
