package com.BusbookingApplication.BusbookingApp.Controller;



import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BusbookingApplication.BusbookingApp.Exception.ResourceNotFoundException;
import com.BusbookingApplication.BusbookingApp.Model.User;
import com.BusbookingApplication.BusbookingApp.Repository.UserRepository;
import com.BusbookingApplication.BusbookingApp.payload.UserIdentityAvailability;
import com.BusbookingApplication.BusbookingApp.security.UserPrincipal;

@RestController
@RequestMapping("/api")
public class UserController {
	

    @Autowired
    UserRepository userRepo;
   
    
    //get All users
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces={"application/json"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    
 
    // Get a Single User
    	
    	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces={"application/json"})
    	@Secured({"ROLE_USER", "ROLE_ADMIN"})
    	public User getUserById(@PathVariable(value = "id") Long userId) throws Exception{
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
    		if(userPrincipal.getId()==userId)
    		return userRepo.findById(userId)
    	            .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    		else
    		{	
    			throw new Exception("Sorry, you are not authorize");
    		}
    		
    	}
    	
    	
    	
    	//to check email is available or not
    	@RequestMapping(value = "/user/checkEmailAvailability", method = RequestMethod.POST, produces={"application/json"})
    	@Secured({"ROLE_USER", "ROLE_ADMIN"})
    	public UserIdentityAvailability checkEmailAvailability(@Valid @RequestBody String email){
    		System.out.println(email);
            Boolean isAvailable = !userRepo.existsByEmail(email);
           return new UserIdentityAvailability(isAvailable);
           
        }

    	// Update a User
    	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces={"application/json"})
    	@Secured({"ROLE_USER", "ROLE_ADMIN"})
    	public User updateUser(@PathVariable(value = "id") Long userId,
    	                                        @Valid @RequestBody User userDetails) {
    		
    	    User user = userRepo.findById(userId)
    	            .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

    	    if(userDetails.getUsername()!=null && userDetails.getUsername()!="")
    	    user.setUsername(userDetails.getUsername());
    	    if(userDetails.getPhonenum()!=null && userDetails.getPhonenum()!="")
    	    user.setPhonenum(userDetails.getPhonenum());
    	    if(userDetails.getGender()!=null && userDetails.getGender()!="")
    	    user.setGender(userDetails.getGender());
    	    if(userDetails.getCity()!=null && userDetails.getCity()!="")
    	    user.setCity(userDetails.getCity());
    	    if(userDetails.getDob()!=null && userDetails.getDob()!="")
        	user.setDob(userDetails.getDob());
    	    

    	    User updatedUser = userRepo.save(user);
    	    return updatedUser;
    	    
    		}

    	 // Delete a User
    	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces={"application/json"})
    	@PreAuthorize("hasRole('ROLE_ADMIN')")
    	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
    	    User user = userRepo.findById(userId)
    	            .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

    	    userRepo.delete(user);

    	    return ResponseEntity.ok().build();
    	}
    	
    	
    	
    	

}
