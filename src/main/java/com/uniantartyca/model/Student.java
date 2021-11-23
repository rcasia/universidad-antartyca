package com.uniantartyca.model;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends Person {

	@ManyToMany(mappedBy = "setOfStudents")
	private Set<Subject> setOfSubjects = new HashSet<>();
	
	@Column
	private Float averageGlobalMark;
	
	public Set<Subject> getListOfSubjects() {
		return setOfSubjects;
	}
	
	public void setListOfSubjects(Set<Subject> setOfSubjects) {
		this.setOfSubjects = setOfSubjects;
	}
	
	public Float getAverageGlobalMark() {
		return averageGlobalMark;
	}
	
	public void setAverageGlobalMark(Float averageGlobalMark) {
		this.averageGlobalMark = averageGlobalMark;
	}

	
}
