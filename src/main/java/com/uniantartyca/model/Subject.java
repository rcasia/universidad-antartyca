package com.uniantartyca.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.uniantartyca.model.enums.Department;

@Entity
public class Subject extends Base {
	
	@Column
	private String name;
	
	@ManyToOne
	@JoinColumn(name =  "coordinator_id")
	private Teacher coordinator;
	
	private Department department;
	
	@ManyToMany
	@JoinTable(
			  name = "subject_teacher", 
			  joinColumns = @JoinColumn(name = "subject_id") , 
			  inverseJoinColumns = @JoinColumn(name = "teacher_id")
			  )
	private Set<Teacher> setOfTeachers;
	
	@ManyToMany
	@JoinTable(
			  name = "subject_student", 
			  joinColumns = @JoinColumn(name = "subject_id"), 
			  inverseJoinColumns = @JoinColumn(name = "student_id")
			  )
	private Set<Student> setOfStudents = new HashSet<>();
	
	
	//// Methods ////
	
	public void addStudent(Student student) {
		setOfStudents.add(student);
	}
	
	public boolean deleteStudent(Student student) {
		return setOfStudents.remove(student);
	}
	
	public void addTeacher(Teacher teacher) {
		setOfTeachers.add(teacher);	
	}
	
	public boolean deleteTeacher(Teacher teacher) {
		return setOfTeachers.remove(teacher);	
	}
	
	//// Getters and setters ////
	
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
	
	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	public Set<Teacher> getSetOfTeachers() {
		return setOfTeachers;
	}
	
	public void setSetOfTeachers(Set<Teacher> setOfTeachers) {
		this.setOfTeachers = setOfTeachers;
	}
	
	public Set<Student> getSetOfStudents() {
		return setOfStudents;
	}
	
	public void setSetOfStudents(Set<Student> setOfStudents) {
		this.setOfStudents = setOfStudents;
	}

	
	
}
