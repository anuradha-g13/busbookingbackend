package com.BusbookingApplication.BusbookingApp.Model;

import java.util.List;

import com.BusbookingApplication.BusbookingApp.Controller.BlockSeatPaxDetails;
import com.BusbookingApplication.BusbookingApp.Controller.BoardingPoint;

public class APIBlockTicketRequest {
	
	
	@SuppressWarnings("unused")
	private String sourceCity;
	
	private String destinationCity;

	private int inventoryType;


	private String routeScheduleId;
	
	private String customerName;
	
	private String customerLastName;
	
	private String customerEmail;
	
	private String doj;
	
	private BoardingPoint boardingPoint;

	private BoardingPoint droppingPoint;
	
	private String customerPhone;
	
	private String emergencyPhNumber;
	
	private String customerAddress;

	private List<BlockSeatPaxDetails> blockSeatPaxDetails;

	
	public void setSourceCity(String string) {
		// TODO Auto-generated method stub
		sourceCity= string;
		
	}
	
	public String getSourceCity()
	{
		return sourceCity;
	}

	public void setDestinationCity(String string) {
		// TODO Auto-generated method stub
		
		destinationCity = string;
		
	}
	
	public String getDestinationCity()
	{
		return destinationCity;
	}


	public void setInventoryType(int i) {
		// TODO Auto-generated method stub
		inventoryType = i;
	}

	public int getInventorytype()
	{
		return inventoryType;
	}

	public void setRouteScheduleId(String string) {
		// TODO Auto-generated method stub
		routeScheduleId = string;
	}

	public String getRouteScheduledId()
	{
		return routeScheduleId;
	}
	
	public void setCustomerName(String string) {
		// TODO Auto-generated method stub
		customerName = string;
		
	}

	public String getCustomerName()
	{
		return customerName;
	}

	

	public void setCustomerLastName(String custLastName) {
		// TODO Auto-generated method stub
		customerLastName = custLastName;
		
	}
	
	public String getCustomerLastName() {
		// TODO Auto-generated method stub
		return customerLastName;
	}
	
	
	public void setCustomerEmail(String string) {
		// TODO Auto-generated method stub
		customerEmail = string;
	}

	public String getCustomerEmail()
	{
		return customerEmail;
	}


	
	public void setDoj(String string) {
		// TODO Auto-generated method stub
		
		doj = string;
		
	}
	
	public String getDoj()
	{
		return doj;
	}



	
	public void setBoardingPoint(BoardingPoint boardingPoint) {
		// TODO Auto-generated method stub
		
		this.boardingPoint = boardingPoint;
		
	}
	
	public BoardingPoint getBoardingPoint()
	{
		return boardingPoint;
	}

	
	public void setDroppingPoint(BoardingPoint droppingPoint) {
		// TODO Auto-generated method stub
		
		this.droppingPoint = droppingPoint;
		
	}
	
	public BoardingPoint getDroppingPoint()
	{
		return droppingPoint;
	}

	


	public void setCustomerPhone(String custPhone) {
		// TODO Auto-generated method stub
		customerPhone = custPhone;
	}
	
	public String getCustomerPhone() {
		// TODO Auto-generated method stub
		return customerPhone;
	}

	
	
	public void setEmergencyPhNumber(String string) {
		// TODO Auto-generated method stub
		emergencyPhNumber = string;
	}

	public String getEmergencyPhNum()
	{
		return emergencyPhNumber;
	}

	
	public void setCustomerAddress(String custAddrss) {
		// TODO Auto-generated method stub
		customerAddress = custAddrss;
	}
	
	public String getCustomerAddress() {
		// TODO Auto-generated method stub
		return customerAddress;
		
	}


	public void setBlockSeatPaxDetails(List<BlockSeatPaxDetails> blockSeatPaxDetailsList) {
		// TODO Auto-generated method stub
	
		blockSeatPaxDetails = blockSeatPaxDetailsList;
	}
	
	public List<BlockSeatPaxDetails> getBlockSeatDetails()
	{
		return blockSeatPaxDetails;
	}


	
}
