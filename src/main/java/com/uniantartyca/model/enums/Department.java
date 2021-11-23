package com.uniantartyca.model.enums;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Department {
	
	//
	// The index of this ENUM list
	// is stored in the database.
	// 
	// In order to add new values 
	// you should add it after all of them.
	//
	CienciasSociales("CS"),
	Humanidades("HH"),
	CienciasNaturales("CN"),
	;

	private final String code;
	private final String name;
	
	private Department(String code) {
		this.code = code;
		name = translateCodeToName(code);
	}
	
	@JsonCreator
	public static Department decode(String code) {
		return Stream.of(Department.values())
								.filter(department -> department.code.equals(code))
								.findFirst()
								.orElse(null);
	}
	
	@JsonValue
	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public String translateCodeToName(String code) {
		switch (code) {
		
			case "CS": return "Ciencias Sociales";
				
			case "HH": return "Humanidades";
				
			case "CN": return "Ciencias Naturales";
			
			default: return code;
		}
	}
	
}
