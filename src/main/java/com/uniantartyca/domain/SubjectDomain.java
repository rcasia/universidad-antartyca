package com.uniantartyca.domain;

import java.util.List;

import com.uniantartyca.model.Student;
import com.uniantartyca.model.Teacher;

public class SubjectDomain extends BaseDomain {
	
	private String name;
	private Teacher coordinator;
	private String department;
	private List<Teacher> listOfTeachers;
	private List<Student> listOfStudents;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Teacher getCoordinator() {
		return coordinator;
	}
	
	public void setCoordinator(Teacher coordinator) {
		this.coordinator = coordinator;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public List<Teacher> getListOfTeachers() {
		return listOfTeachers;
	}
	
	public void setListOfTeachers(List<Teacher> listOfTeachers) {
		this.listOfTeachers = listOfTeachers;
	}
	
	public List<Student> getListOfStudents() {
		return listOfStudents;
	}
	
	public void setListOfStudents(List<Student> listOfStudents) {
		this.listOfStudents = listOfStudents;
	}
}
