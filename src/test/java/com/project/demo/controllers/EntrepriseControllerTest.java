package com.project.demo.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.project.demo.models.Entreprise;
import com.project.demo.models.Profile;
import com.project.demo.services.EntrepriseServicesImpl;

public class EntrepriseControllerTest {

	
	@InjectMocks // This allows to inject Mock objects.
	EntrepriseController entrepriseController;

	@Mock
	EntrepriseServicesImpl entrepriseService;

	Entreprise entreprise;
	
	private final int TVAnumero = 123;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() throws Exception
	{
		/*
		 *  This is needed for Mockito to be able to instantiate the Mock Objects
		 *  and Inject into the userController object
		 */
		MockitoAnnotations.initMocks(this);

		entreprise = new Entreprise();
        
		entreprise.setTVAnumero(TVAnumero);
		entreprise.setAddress("test_address_Entreprise");

		Set<Profile> profiles = new HashSet<>();
		
		Profile p1 = new Profile();
		p1.setId(1);
		p1.setAddress("test_address");
		p1.setNom("Nom_Test");
		p1.setPrenom("Prenom_test");
		p1.setFreelance(false);
		Set<Entreprise> entreprise1 = new HashSet<>();
		
		Entreprise e = new Entreprise();
		e.setAddress("entreprise_address_test");
		e.setTVAnumero(TVAnumero);
		entreprise1.add(e);
		p1.setEntreprise(entreprise1);
		
        Profile p2 = new Profile();
        p2.setId(2);
        p2.setAddress("test_address2");
        p2.setNom("Nom_Test2");
        p2.setPrenom("Prenom_test2");
        p2.setFreelance(false);
		
		Set<Entreprise> entreprise2 = new HashSet<>();
        Entreprise ee = new Entreprise();	
     	ee.setAddress("entreprise_address_test2");
		ee.setTVAnumero(TVAnumero+1);;
		entreprise2.add(ee);		
		p2.setEntreprise(entreprise2);
		
        profiles.add(p1);
        profiles.add(p1);
        
        entreprise.setProfiles(profiles);
        
	}

	@Test
	void testGetEntreprise()
	{
		// Fake the getUserByUserId method call using mocked userService object
		
		Optional<Entreprise> entrepriseOptional = Optional.of(entreprise);
		
		when(entrepriseService.findById((long) TVAnumero)).thenReturn(entrepriseOptional);
		ResponseEntity<Entreprise> entrepriseRest = entrepriseController.fetchEntrepriseById(TVAnumero);
		assertNotNull(entrepriseRest.getBody());
		assertEquals(TVAnumero, entrepriseRest.getBody().getTVAnumero());
		assertEquals(entreprise.getAddress(),entrepriseRest.getBody().getAddress());
		assertTrue(entreprise.getProfiles().size()==entrepriseRest.getBody().getProfiles().size());
	}
	
	
	
}
