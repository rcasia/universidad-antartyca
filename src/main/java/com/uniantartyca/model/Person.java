package com.uniantartyca.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person extends Base {
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column
	private Date birthdate;
	
	@Column
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
