package com.BusbookingApplication.BusbookingApp.Model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.BusbookingApplication.BusbookingApp.Model.audit.DateAudit;

import java.util.HashSet;
import java.util.Set;


@SuppressWarnings("serial")
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "email"
        })
})

public class User extends DateAudit {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

	 @NaturalId
	    @NotBlank
	    @Size(max = 40)
	    @Email
	    private String email;
	 
	 @NotBlank
	    @Size(max = 100)
	    private String password;
	 
    
    @Size(max = 40)
    private String username;
    
   
    @Size(max = 11)
    private String phonenum;
    
    @Size(max = 40)
    private String city;
    
    @Size(max = 1)
    private String gender;
    
    @Size(max = 20)
    private String dob;
    

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User() {

    }

        
    public User(String email, String password) {
    	 this.email = email;
         this.password = password;
    }

	// Getters and Setters ... (Omitted for brevity)
     public Long getId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
}
