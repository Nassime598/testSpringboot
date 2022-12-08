package com.project.demo.models;



import java.util.Set;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
@NoArgsConstructor

@Entity
@Table(name="entreprise")
public class Entreprise {
	
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
	private int TVAnumero;

	@Column(nullable=false)
	private String Address;
	
	@Column(nullable=false)
	@ManyToMany(fetch = FetchType.LAZY,
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    },
    mappedBy = "entreprise")
    @JsonIgnore
	private Set<Profile> profiles;
	
	
	

	public Entreprise(int tVAnumero, String address) {
		super();
		TVAnumero = tVAnumero;
		Address = address;
	}

	public Entreprise() {
		super();
	}

	public Entreprise(int tVAnumero, String address, Set<Profile> profiles) {
		super();
		TVAnumero = tVAnumero;
		Address = address;
		this.profiles = profiles;
	}

	public Entreprise(String Adress) {
		this.Address = Adress;
	}

	public int getTVAnumero() {
		return TVAnumero;
	}
	public void setTVAnumero(int tVAnumero) {
		TVAnumero = tVAnumero;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}

	
	public Set<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles;
	}


	
	

}
