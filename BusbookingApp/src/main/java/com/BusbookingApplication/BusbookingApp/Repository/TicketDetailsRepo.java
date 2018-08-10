package com.BusbookingApplication.BusbookingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BusbookingApplication.BusbookingApp.Model.Details.TicketDetails;

public interface TicketDetailsRepo extends JpaRepository<TicketDetails, Long> {

}
