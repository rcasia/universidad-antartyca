package com.uniantartyca.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.uniantartyca.model.Student;

public interface StudentRepository extends JpaRepository<Student, String>, JpaSpecificationExecutor<Student> {
	
	Student findByNIF(String NIF);
	
	@Transactional
	Integer deleteByNIF(String NIF);
	
	boolean existsByNIF(String NIF);
	
}
