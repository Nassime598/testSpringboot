package com.project.demo.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.models.Profile;
import com.project.demo.repository.ProfileRepository;

@Service
public class ProfileServicesImpl implements ProfileServices {

    @Autowired
    private ProfileRepository profileRepository;
	
	@Override
	public Profile SaveProfile(Profile profile) {
		return profileRepository.save(profile);
	}

	@Override
	public List<Profile> fetchProfilesList() {
		return (List<Profile>)profileRepository.findAll();
	}

	@Override
	public Profile updateProfile(Profile profile, Long ProfileId) {
		
		Profile profileDB
         = profileRepository.findById(ProfileId)
               .get();

        if (Objects.nonNull(profile.getNom())
            && !"".equalsIgnoreCase(
            		profile.getNom())) {
        	profileDB.setNom(
            		profile.getNom());
        }
 
        if (Objects.nonNull(
        		profile.getPrenom())
            && !"".equalsIgnoreCase(
            		profile.getPrenom())) {
        	profileDB.setPrenom(
            		profile.getPrenom());
        }
 
        if (Objects.nonNull(profile.getAddress())
            && !"".equalsIgnoreCase(
            		profile.getAddress())) {
        	profileDB.setAddress(
            		profile.getAddress());
        }
 
        return profileRepository.save(profileDB);
		
		
	}

	@Override
	public void DeleteProfileById(Long ProfileId) {
		profileRepository.deleteById(ProfileId);
		
	}

	@Override
	public Optional<Profile> findById(Long profileId) {
		
		return profileRepository.findById(profileId);
	}

	@Override
	public void deleteAll() {

		profileRepository.deleteAll();
		
	}

}
