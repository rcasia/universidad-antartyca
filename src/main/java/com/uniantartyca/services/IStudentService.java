package com.uniantartyca.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uniantartyca.controllers.filters.DomainFilter;
import com.uniantartyca.domain.StudentDomain;
import com.uniantartyca.domain.StudentPresenter;
import com.uniantartyca.exceptions.PersonAlreadyExistException;

public interface IStudentService {

	StudentPresenter createStudent(StudentDomain studentDomain) 
					throws PersonAlreadyExistException;

	StudentPresenter findStudentByNIF(String studentNIF);

	StudentPresenter deactivateStudentByNIF(String studentNIF);

	StudentPresenter updateStudent(String studentNIF, 
									StudentDomain studentDomain);

	Page<StudentPresenter> getPageOfStudentsFiltered(Pageable pageable, DomainFilter filter);


}
