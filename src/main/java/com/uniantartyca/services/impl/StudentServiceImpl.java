package com.uniantartyca.services.impl;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.uniantartyca.controllers.filters.DomainFilter;
import com.uniantartyca.domain.StudentDomain;
import com.uniantartyca.domain.StudentPresenter;
import com.uniantartyca.exceptions.PersonAlreadyExistException;
import com.uniantartyca.model.Student;
import com.uniantartyca.repositories.StudentRepository;
import com.uniantartyca.services.IStudentService;
import com.uniantartyca.utils.converters.IStudentConverter;

@Service("studentServiceImpl")
public class StudentServiceImpl implements IStudentService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private IStudentConverter studentConverter;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public StudentPresenter createStudent(StudentDomain studentDomain) throws PersonAlreadyExistException {
		
		if(studentRepository.existsByNIF(studentDomain.getNIF()))
			throw new PersonAlreadyExistException();
		
		Student studentModel = studentConverter.convert(studentDomain);
		
		studentModel = studentRepository.save(studentModel);
		
		StudentPresenter studentPresenter = studentConverter.convert(studentModel);
		
		return studentPresenter;
		
	}
	
	@Override
	public Page<StudentPresenter> getPageOfStudentsFiltered(Pageable pageable, DomainFilter filter){
		
			//// FILTER CONSTANTS ////
			final String NIF = filter.getNif();
			final String NAME = filter.getName();
			final String SURNAME = filter.getSurname();
			final Date START_DATE_RANGE = filter.getStartRangeBirthdate();
			final Date END_DATE_RANGE = filter.getEndRangeBirthdate();

		return studentRepository.findAll(
				(Specification<Student>) (root, query, criteriaBuilder) -> {
					
					Predicate predicate = criteriaBuilder.conjunction();
					
					// Excluye inactivos //
					predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("active"), true));
					
					if(StringUtils.hasText(NIF)) {
						predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("NIF"), NIF));
					}
					
					if(StringUtils.hasText(NAME)) {
						predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), NAME));
					}
					
					if(StringUtils.hasText(SURNAME)) {
						predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("surname"), "%"+SURNAME+"%"));
					}
					
					if(START_DATE_RANGE != null && END_DATE_RANGE != null && START_DATE_RANGE.before(END_DATE_RANGE) ) {
						predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("birthdate"), START_DATE_RANGE, END_DATE_RANGE));
					}
						
					return predicate;
			
		}, pageable)
				.map(student -> studentConverter.convert(student));
		
		
	}
	
	
	@Override
	public StudentPresenter findStudentByNIF(String studentNIF) {
		
		Optional<Student> studentOpt = Optional
					.ofNullable(studentRepository.findByNIF(studentNIF));
		
		Student studentModel = studentOpt.orElseThrow();
		
		StudentPresenter studentPresenter = studentConverter.convert(studentModel);
		
		return studentPresenter;
	}

	@Override
	public StudentPresenter deactivateStudentByNIF(String studentNIF) {		
		
		Optional<Student> studentIdOpt = Optional.ofNullable(studentRepository.findByNIF(studentNIF));
		Student studentModel = studentIdOpt.orElseThrow();
		
		studentModel.setActive(false);
		
		studentModel = studentRepository.save(studentModel);
		
		StudentPresenter studentPresenter = studentConverter.convert(studentModel);
		
		return studentPresenter;
	}

	@Override
	public StudentPresenter updateStudent(String studentId, StudentDomain studentDomain) {
		
		Optional<Student> studentOpt = studentRepository.findById(studentId);
		Student studentModel = studentOpt.orElseThrow();
		
		studentModel = studentConverter.convert(studentDomain);
		
		studentModel = studentRepository.save(studentModel);
		
		StudentPresenter studentPresenter = studentConverter.convert(studentModel);
		
		return studentPresenter;
	}

	
	
}
