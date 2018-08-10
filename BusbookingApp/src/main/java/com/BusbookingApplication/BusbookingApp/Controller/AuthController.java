package com.BusbookingApplication.BusbookingApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.BusbookingApplication.BusbookingApp.Exception.AppException;
import com.BusbookingApplication.BusbookingApp.Model.Role;
import com.BusbookingApplication.BusbookingApp.Model.RoleName;
import com.BusbookingApplication.BusbookingApp.Model.User;
import com.BusbookingApplication.BusbookingApp.Repository.RoleRepository;
import com.BusbookingApplication.BusbookingApp.Repository.UserRepository;
import com.BusbookingApplication.BusbookingApp.payload.ApiResponse;
import com.BusbookingApplication.BusbookingApp.payload.JwtAuthenticationResponse;
import com.BusbookingApplication.BusbookingApp.payload.LoginRequest;
import com.BusbookingApplication.BusbookingApp.payload.SignUpRequest;
import com.BusbookingApplication.BusbookingApp.security.JwtTokenProvider;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
    
    

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces={"application/json"})
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
                
               
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces={"application/json"})
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
       if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getEmail(),signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{email}")
                .buildAndExpand(result.getEmail()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
    
   
  
}