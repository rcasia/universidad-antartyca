package com.uniantartyca.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/*
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  scope = TeacherPresenter.class )
*/
public class TeacherPresenter extends PersonPresenter {
	
	@JsonIgnoreProperties({"setOfStudents", "setOfTeachers" })
	private Set<SubjectPresenter> setOfSubjectIsCoordinatorIn;
	
	@JsonIgnoreProperties({"setOfStudents", "setOfTeachers"})
	private Set<SubjectPresenter> setOfSubjects;
	
	private boolean isDepartmentChief;
	
	public Set<SubjectPresenter> getSetOfSubjectIsCoordinatorIn() {
		return setOfSubjectIsCoordinatorIn;
	}
	public void setSetOfSubjectIsCoordinatorIn(Set<SubjectPresenter> setOfSubjectIsCoordinatorIn) {
		this.setOfSubjectIsCoordinatorIn = setOfSubjectIsCoordinatorIn;
	}
	public Set<SubjectPresenter> getSetOfSubjects() {
		return setOfSubjects;
	}
	public void setSetOfSubjects(Set<SubjectPresenter> setOfSubjects) {
		this.setOfSubjects = setOfSubjects;
	}
	public boolean isDepartmentChief() {
		return isDepartmentChief;
	}
	public void setDepartmentChief(boolean isDepartmentChief) {
		this.isDepartmentChief = isDepartmentChief;
	}
	

	
}
