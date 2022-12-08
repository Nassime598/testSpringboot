package com.project.demo.services;

import java.util.List;
import java.util.Optional;

import com.project.demo.models.Profile;

public interface ProfileServices {
	
	Profile SaveProfile(Profile profile);
	
	List<Profile> fetchProfilesList();
	
	Profile updateProfile(Profile profile , Long ProfileId);
	
	void DeleteProfileById(Long ProfileId);

	Optional<Profile> findById(Long profileId);

	void deleteAll();


}
