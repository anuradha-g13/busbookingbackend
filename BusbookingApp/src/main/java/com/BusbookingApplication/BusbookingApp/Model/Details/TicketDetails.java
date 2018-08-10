package com.BusbookingApplication.BusbookingApp.Model.Details;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticketdetails")
public class TicketDetails {
	
	@Id
	private String etstnum;
	
	private String pnrnum;
	
	private String ticketStatus;

	private String ticketdump;
	
	//Getter and Setter methods

	public String getEtstnum() {
		return etstnum;
	}

	public void setEtstnum(String etstnum) {
		this.etstnum = etstnum;
	}

	public String getPnrnum() {
		return pnrnum;
	}

	public void setPnrnum(String pnrnum) {
		this.pnrnum = pnrnum;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	
	public String getTicketdump() {
		return ticketdump;
	}

	public void setTicketdump(String ticketdump) {
		this.ticketdump = ticketdump;
	}
	
	
	

}
