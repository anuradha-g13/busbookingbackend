package com.BusbookingApplication.BusbookingApp.Model.Details;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "orderdetails")
public class OrderDetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int order_id;
	
	private String transaction_id;
	
	private String status;
	
	private String etstnum;

	
	//Getter and Setter methods
	
	public int getOrderid() {
		return order_id;
	}

	public void setOrderid(int order_id) {
		this.order_id = order_id;
	}

	public String getTransactionid() {
		return transaction_id;
	}

	public void setTransactionid(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEtstnum() {
		return etstnum;
	}

	public void setEtstnum(String etstnum) {
		this.etstnum = etstnum;
	}
	
	

}
