package com.project.demo.models;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
@NoArgsConstructor

@Entity
@Table(name="profile")
public class Profile {
	
	@jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
	private int id;
    
    @Column(nullable = false)
	private String nom;
    @Column(nullable = false)
	private String prenom;
    @Column(nullable = false)
	private String address;
    @Column(nullable = false)
    private Boolean freelance;
  



	@ManyToMany(fetch = FetchType.LAZY,
    	      cascade = {
    	          CascadeType.PERSIST,
    	          CascadeType.MERGE
    	      })
    	    @JoinTable(name = "Profile_Entreprise",
    	        joinColumns = { @JoinColumn(name = "id") },
    	        inverseJoinColumns = { @JoinColumn(name = "TVAnumero") })
    private Set<Entreprise> entreprise = new HashSet<>();
    
    
    
    public Profile() {
		super();
	}
	public Profile(String nom , String prenom , String address) {
    	
    	this.nom = nom;
    	this.prenom = prenom;
    	this.address = address;
    	
    }
    public Profile(String nom , String prenom , String address,Entreprise entreprise) {
    	
    	this.nom = nom;
    	this.prenom = prenom;
    	this.address = address;
    	this.entreprise.add(entreprise);
    }
	public Profile(int id, String nom, String prenom, String address, Boolean freelance, Set<Entreprise> entreprise) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.address = address;
		this.freelance = freelance;
		this.entreprise = entreprise;
	}
    
	public int getId() {
		return id;
	}
	public Set<Entreprise> getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Set<Entreprise> entreprise) {
		this.entreprise = entreprise;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	  
    public Boolean getFreelance() {
		return freelance;
	}
	public void setFreelance(Boolean freelance) {
		this.freelance = freelance;
	}

	
	public void addEntreprise(Entreprise e){
		 this.entreprise.add(e);
		 e.getProfiles().add(this);
		
	}
	
	public void RemoveEntreprise(long Tvanumero){
	    Entreprise entreprise = this.entreprise.stream().filter(t -> t.getTVAnumero() == Tvanumero).findFirst().orElse(null);
	    if (entreprise != null) {
	      this.entreprise.remove(entreprise);
	      entreprise.getProfiles().remove(this);
		
	     }
	}
	
 

	
	

}
