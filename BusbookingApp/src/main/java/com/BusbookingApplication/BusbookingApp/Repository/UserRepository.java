package com.BusbookingApplication.BusbookingApp.Repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BusbookingApplication.BusbookingApp.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	Optional<User> findByUsernameOrEmail(String username, String email);
	Boolean existsByEmail(String email);
}
