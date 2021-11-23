package com.uniantartyca.model;




import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Teacher extends Person {
	
	@ManyToMany(mappedBy = "setOfTeachers")
	private Set<Subject> setOfSubjects;
	
	@OneToMany(mappedBy = "coordinator")
	private Set<Subject> setOfSubjectIsCoordinatorIn;
	
	private boolean isDepartmentChief;
	
	
	public Set<Subject> getListOfSubjects() {
		return setOfSubjects;
	}
	
	public void setListOfSubjects(Set<Subject> setOfSubjects) {
		this.setOfSubjects = setOfSubjects;
	}
	
	public Set<Subject> getListOfSubjectIsCoordinatorIn() {
		return setOfSubjectIsCoordinatorIn;
	}
	
	public void setListOfSubjectIsCoordinatorIn(Set<Subject> setOfSubjectIsCoordinatorIn) {
		this.setOfSubjectIsCoordinatorIn = setOfSubjectIsCoordinatorIn;
	}
	
	public boolean isDepartmentChief() {
		return isDepartmentChief;
	}
	
	public void setDepartmentChief(boolean isDepartmentChief) {
		this.isDepartmentChief = isDepartmentChief;
	}
	
	
}
