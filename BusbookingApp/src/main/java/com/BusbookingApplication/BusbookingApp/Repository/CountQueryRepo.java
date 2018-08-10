package com.BusbookingApplication.BusbookingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.BusbookingApplication.BusbookingApp.Model.Details.TravellerDetails;

public interface CountQueryRepo extends JpaRepository<TravellerDetails , Long >{
	public Integer countByEtstNumber(String etst_number);
    
}
