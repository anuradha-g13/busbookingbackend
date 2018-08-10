package com.BusbookingApplication.BusbookingApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BusbookingApplication.BusbookingApp.Model.Details.TravellerDetails;

public interface TravellerDetailsRepo extends JpaRepository<TravellerDetails, Long> {

	TravellerDetails findByEtstNumber(String etsTicketNo);

}
