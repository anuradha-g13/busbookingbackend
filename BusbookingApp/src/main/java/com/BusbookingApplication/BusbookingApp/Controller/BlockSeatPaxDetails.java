package com.BusbookingApplication.BusbookingApp.Controller;

import java.math.BigDecimal;

public class BlockSeatPaxDetails {

	
	private String age;
	private String name;
	private String seatNbr;
	private int row;
	private int column;
	private int zIndex;
	private int length;
	private int width;
	private String sex;
	private BigDecimal fare;
	private BigDecimal serviceTaxAmount;
	private double operatorServiceChargeAbsolute;
	private BigDecimal totalFareWithTaxes;
	private boolean ladiesSeat;
	private String lastName;
	private String mobile;
	private String title;
	private String email;
	private String idType;
	private String idNumber;
	private String nameOnId;
	private boolean primary;
	private boolean ac;
	private boolean sleeper;
	
	
	
	public void setAge(String string) {
		// TODO Auto-generated method stub
		age= string;
	}
	
	public String getAge() {
		return age;
	}

	public void setName(String string) {
		// TODO Auto-generated method stub
		name = string;
	}

	public String getName() {
		return name;
	}
	
	public void setSeatNbr(String string) {
		// TODO Auto-generated method stub
		seatNbr = string;
	}

	public String getSeatNbr() {
		return seatNbr;
	}
	
	public void setRow(int i) {
		// TODO Auto-generated method stub
		row =i;
	}
	
	public int getRow() {
		return row;
	}

	public void setColumn(int i) {
		// TODO Auto-generated method stub
		column = i;
	}
	
	public int getColumn() {
		return column;
	}
	

	public void setZIndex(int i) {
		// TODO Auto-generated method stub
		zIndex = i ;
	}

	public int getZIndex()
	{
		return zIndex;
	}
	


	public void setLength(int length) {
		// TODO Auto-generated method stub
		this.length=length;
	}
	public int getLength()
	{
		return length;
	}


	public void setWidth(int width) {
		// TODO Auto-generated method stub
		this.width = width;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setSex(String string) {
		// TODO Auto-generated method stub
		sex = string;
	}
	

	public String getSex() {
		return sex;
	}
	

	public void setFare(BigDecimal bigDecimal) {
		// TODO Auto-generated method stub
		fare = bigDecimal;
		
	}

	public BigDecimal getFare() {
		return fare;
	}
	
	
	public void setServiceTax(BigDecimal serviceTaxAmount) {
		// TODO Auto-generated method stub
		
		this.serviceTaxAmount= serviceTaxAmount;
		
		
	}
	
	public BigDecimal getServiceTax() {
		return serviceTaxAmount;
	}


	public void setOperatorServiceCharge(double operatorServiceChargeAbsolute) {
		// TODO Auto-generated method stub
		this.operatorServiceChargeAbsolute = operatorServiceChargeAbsolute;
	}

	
	public double getOperatorService() {
		return operatorServiceChargeAbsolute;
	}

	public void setTotalFare(BigDecimal totalFareWithTaxes) {
		// TODO Auto-generated method stub
		this.totalFareWithTaxes= totalFareWithTaxes;
	}
	
	public BigDecimal getTotalFare() {
		return totalFareWithTaxes;
	}

	public void setLadiesSeat(boolean b) {
		// TODO Auto-generated method stub
		ladiesSeat = b;
	}

	public boolean getLadiesSeat() {
		return ladiesSeat;
	}
	
	public void setLastName(String string) {
		// TODO Auto-generated method stub
		lastName = string;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setMobile(String string) {
		// TODO Auto-generated method stub
		mobile = string;
	}

	public String getMobile() {
		return mobile;
	}
	
	public void setTitle(String string) {
		// TODO Auto-generated method stub
		title = string;
	}

	
	public String gettitle() {
		return title;
	}


	public void setEmail(String string) {
		// TODO Auto-generated method stub
		email = string;
	}
	
	public String getEmail() {
		return email;
	}

	public void setIdType(String string) {
		// TODO Auto-generated method stub
		idType = string;
	}

	public String getIdType() {
		return idType;
	}
	

	
	public void setIdNumber(String string) {
		// TODO Auto-generated method stub
		idNumber = string;
	}

	public String getIdNumber() {
		return idNumber;
	}
	

		
	public void setNameOnId(String string) {
		// TODO Auto-generated method stub
		nameOnId = string;
	}
	
	public String getNameonId() {
		return nameOnId;
	}

	public void setPrimary(boolean b) {
		// TODO Auto-generated method stub
		primary = b;
	}
	
	public boolean getPrimary() {
		return primary;
	}

	

	
	public void setAc(boolean b) {
		// TODO Auto-generated method stub
		ac=b;
		
	}
	
	public boolean getAc() {
		return ac;
	}

	
	
	public void setSleeper(boolean b) {
		// TODO Auto-generated method stub
		sleeper = b;
	}
	public boolean getSleeper() {
		return sleeper;
	}


	

}
