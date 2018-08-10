package com.BusbookingApplication.BusbookingApp.Controller;

import java.util.List;

public class APICancelRequest {
	
	private String etsTicketNo;
	
	private List<String> seatNbrsToCancel;//seatNbrs

	public void setEtsTicketNo(String etsTicketNo) {
		// TODO Auto-generated method stub
		this.etsTicketNo= etsTicketNo;
	}
	//
	public String getEtsTicketNo()
	{
		return etsTicketNo;
	}

	public List<String> getSeatNbrsToCancel() {
		return seatNbrsToCancel;
	}

	public void setSeatNbrsToCancel(List<String> seatNbrsToCancel) {
		this.seatNbrsToCancel = seatNbrsToCancel;
	}

	@Override
	public String toString() {
		return "APICancelRequest [etsTicketNo=" + etsTicketNo + ", seatNbrsToCancel=" + seatNbrsToCancel + "]";
	}

}
