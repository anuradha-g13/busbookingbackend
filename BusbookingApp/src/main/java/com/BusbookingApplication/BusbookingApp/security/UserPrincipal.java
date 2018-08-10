package com.BusbookingApplication.BusbookingApp.security;

import com.BusbookingApplication.BusbookingApp.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public class UserPrincipal implements UserDetails {
    private Long id;

    private String username;

    private String city;
    private String Dob;
    private String gender;
    private String Phonenum;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String name, String city, String Dob,String gender,String Phonenum,  String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = name;
        this.city = city;
        this.Dob = Dob;
        this.gender = gender;
        this.Phonenum = Phonenum;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getCity(),
                user.getDob(),
                user.getGender(),
                user.getPhonenum(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

   public String getCity() {
	   return city;
   }
   
   public String DOB () {
	   return Dob;
   }
   
   public String getGender() {
	   return gender;
   }
   
   public String getPhonenum() {
	   return Phonenum;
   }
   
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }


}