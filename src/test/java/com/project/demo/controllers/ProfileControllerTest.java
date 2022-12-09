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
import com.project.demo.services.ProfileServicesImpl;



public class ProfileControllerTest {
	
	
	@InjectMocks // This allows to inject Mock objects.
	ProfileController profileController;

	@Mock
	ProfileServicesImpl profileService;

	Profile profile;
	
	private final int Profile_ID = 123;
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

		profile = new Profile();
        
		profile.setId(Profile_ID);
		profile.setAddress("test_address");
		profile.setNom("Nom_Test");
		profile.setPrenom("Prenom_test");
		profile.setFreelance(true);
		
		Set<Entreprise> entreprise = new HashSet<>();
		
		Entreprise e = new Entreprise();
		
		e.setAddress("entreprise_address_test");
		e.setTVAnumero(TVAnumero);
		
        Entreprise ee = new Entreprise();
		
		ee.setAddress("entreprise_address_test2");
		ee.setTVAnumero(TVAnumero+1);
		
		entreprise.add(e);
		entreprise.add(ee);
		
		profile.setEntreprise(entreprise);
		

	}

	@Test
	void testGetProfile()
	{
		// Fake the getUserByUserId method call using mocked userService object
		
		Optional<Profile> profileOptional = Optional.of(profile);
		when(profileService.findById((long) Profile_ID)).thenReturn(profileOptional);
		ResponseEntity<Profile> profileRest = profileController.fetchProfileById(Profile_ID);
		assertNotNull(profileRest.getBody());
		assertEquals(Profile_ID, profileRest.getBody().getId());
		assertEquals(profile.getNom(),profileRest.getBody().getNom());
		assertEquals(profile.getPrenom(),profileRest.getBody().getPrenom());
		assertEquals(profile.getAddress(),profileRest.getBody().getAddress());
		assertEquals(profile.getFreelance(),profileRest.getBody().getFreelance());
		
		assertTrue(profile.getEntreprise().size()==profileRest.getBody().getEntreprise().size());
	}


}
