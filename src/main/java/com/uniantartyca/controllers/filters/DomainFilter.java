package com.uniantartyca.controllers.filters;

import java.util.Date;

public class DomainFilter {
	
	private String NIF;
	private String name;
	private String surname;
	private Date startRangeBirthdate;
	private Date endRangeBirthdate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Date getStartRangeBirthdate() {
		return startRangeBirthdate;
	}
	public void setStartRangeBirthdate(Date startRangeBirthdate) {
		this.startRangeBirthdate = startRangeBirthdate;
	}
	public Date getEndRangeBirthdate() {
		return endRangeBirthdate;
	}
	public void setEndRangeBirthdate(Date endRangeBirthdate) {
		this.endRangeBirthdate = endRangeBirthdate;
	}
	public String getNif() {
		return NIF;
	}
	public void setNif(String nif) {
		this.NIF = nif;
	}
	
	

	
}
