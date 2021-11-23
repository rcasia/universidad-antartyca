package com.uniantartyca.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.uniantartyca.model.enums.Department;

/*
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "id",
		  scope = SubjectPresenter.class 
		  )
*/
public class SubjectPresenter extends BasePresenter {
	
	private String name;
	
	@JsonIgnoreProperties({"setOfSubjectIsCoordinatorIn", "setOfSubjects"})
	private TeacherPresenter coordinator;
	
	private String department;
	private String departmentName;
	
	@JsonIgnoreProperties({"setOfSubjects" })
	private Set<TeacherPresenter> setOfTeachers;
	
	
	@JsonIgnoreProperties({"setOfSubjects"})
	private Set<StudentPresenter> setOfStudents;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	

	public Set<TeacherPresenter> getSetOfTeachers() {
		return setOfTeachers;
	}

	public void setSetOfTeachers(Set<TeacherPresenter> setOfTeachers) {
		this.setOfTeachers = setOfTeachers;
	}

	public Set<StudentPresenter> getSetOfStudents() {
		return setOfStudents;
	}

	public void setSetOfStudents(Set<StudentPresenter> setOfStudents) {
		this.setOfStudents = setOfStudents;
	}

	public TeacherPresenter getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(TeacherPresenter coordinator) {
		this.coordinator = coordinator;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
		setDepartmentName(Department.decode(department).getName());
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
