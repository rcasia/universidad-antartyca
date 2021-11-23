package com.uniantartyca.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/*
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id"
		  //scope = StudentPresenter.class 
		  )
*/
public class StudentPresenter extends PersonPresenter {
	
	
	private Set<SubjectPresenter> setOfSubjects;
	
	private Float averageGlobalMark;
	
	public Float getAverageGlobalMark() {
		return averageGlobalMark;
	}
	
	public void setAverageGlobalMark(Float averageGlobalMark) {
		this.averageGlobalMark = averageGlobalMark;
	}
	
	@JsonIgnoreProperties({"setOfStudents", "setOfTeachers"})
	public Set<SubjectPresenter> getSetOfSubjects() {
		return setOfSubjects;
	}

	public void setSetOfSubjects(Set<SubjectPresenter> setOfSubjects) {
		
		this.setOfSubjects = setOfSubjects;
	}
}
