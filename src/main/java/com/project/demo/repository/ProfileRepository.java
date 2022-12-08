package com.project.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.project.demo.models.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{

	List<Profile> findProfileByEntrepriseTVAnumero(Long tvaNumero);
}
