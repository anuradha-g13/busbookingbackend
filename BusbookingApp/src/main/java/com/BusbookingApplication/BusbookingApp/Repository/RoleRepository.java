package com.BusbookingApplication.BusbookingApp.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BusbookingApplication.BusbookingApp.Model.Role;
import com.BusbookingApplication.BusbookingApp.Model.RoleName;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}