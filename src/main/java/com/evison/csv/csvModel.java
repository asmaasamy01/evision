package com.evison.csv;

public class csvModel {
	private String nationalId;
	public csvModel(String nationalId, String name, int amount) {
		super();
		this.nationalId = nationalId;
		this.name = name;
		this.amount = amount;
	}
	public String getNationalId() {
		return nationalId;
	}
	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	private String name;
	private int amount;
}
