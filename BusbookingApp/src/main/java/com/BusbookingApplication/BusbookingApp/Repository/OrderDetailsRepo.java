package com.BusbookingApplication.BusbookingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BusbookingApplication.BusbookingApp.Model.Details.OrderDetails;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Long>{

}
