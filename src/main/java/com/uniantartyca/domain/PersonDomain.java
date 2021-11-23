package com.uniantartyca.domain;

import java.util.Date;

public class PersonDomain extends BaseDomain {
	
	private String name;
	private String surname;
	private Date birthdate;
	private String NIF;
	
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
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getNIF() {
		return NIF;
	}
	
	public void setNIF(String nationalId) {
		this.NIF = nationalId;
	}

}
