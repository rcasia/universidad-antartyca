package com.uniantartyca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.uniantartyca.model.Subject;
import com.uniantartyca.model.enums.Department;

public interface SubjectRepository extends JpaRepository<Subject, String>,  JpaSpecificationExecutor<Subject> {
	
	List<Subject> findAllSubjectByDepartmentAndActiveTrue(Department department);
	
}
