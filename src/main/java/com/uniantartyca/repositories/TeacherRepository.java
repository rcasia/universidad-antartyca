package com.uniantartyca.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.uniantartyca.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, String>, JpaSpecificationExecutor<Teacher> {
	boolean existsByNIF(String NIF);
	
	@Transactional
	Integer deleteByNIF(String NIF);

	Teacher findByNIF(String NIF);
}
