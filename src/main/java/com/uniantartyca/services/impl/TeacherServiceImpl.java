package com.uniantartyca.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.uniantartyca.controllers.filters.DomainFilter;
import com.uniantartyca.domain.TeacherDomain;
import com.uniantartyca.domain.TeacherPresenter;
import com.uniantartyca.exceptions.PersonAlreadyExistException;
import com.uniantartyca.model.Teacher;
import com.uniantartyca.repositories.TeacherRepository;
import com.uniantartyca.services.ITeacherService;
import com.uniantartyca.utils.converters.ITeacherConverter;

@Service("teacherServiceImpl")
public class TeacherServiceImpl implements ITeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private ITeacherConverter teacherConverter;

	@Override
	public Page<TeacherPresenter> getTeacherPageFiltered(DomainFilter filter,Pageable pageable ) {
		
		//// FILTER CONSTANTS ////
		final String NIF = filter.getNif();
		final String NAME = filter.getName();
		final String SURNAME = filter.getSurname();
		final Date START_DATE_RANGE = filter.getStartRangeBirthdate();
		final Date END_DATE_RANGE = filter.getEndRangeBirthdate();

		
		return teacherRepository.findAll(
				(Specification<Teacher>) (root, query, criteriaBuilder) -> {
					
			Predicate predicate = criteriaBuilder.conjunction();
			
			// Excluye inactivos //
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("active"), true));
			
			if(StringUtils.hasText(NIF)) {
				predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("NIF"), NIF));
			}
			
			if(StringUtils.hasText(NAME)) {
				predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%"+NAME+"%"));
			}
			
			if(StringUtils.hasText(SURNAME)) {
				predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("surname"), "%"+SURNAME+"%"));
			}
			
			if(START_DATE_RANGE != null && END_DATE_RANGE != null && START_DATE_RANGE.before(END_DATE_RANGE) ) {
				predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("birthdate"), START_DATE_RANGE, END_DATE_RANGE));
			}
				
			return predicate;
			
		}, pageable)
				
				.map(teacher -> teacherConverter.convert(teacher));
		
		
	}

	@Override
	public List<TeacherPresenter> getTeachersByPartname(String partname) {
	
		Pageable pageable = PageRequest.of(0, 5, Sort.by("name"));
		
		return teacherRepository.findAll(
				(Specification<Teacher>) (root, query, criteriaBuilder) -> {
					
			Predicate predicate = criteriaBuilder.conjunction();
			
			// Excluye inactivos //
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("active"), true));
			
			if(StringUtils.hasText(partname)) {
				predicate = criteriaBuilder.or(
											criteriaBuilder.like(root.get("name"), "%"+partname+"%"),
											criteriaBuilder.like(root.get("surname"), "%"+partname+"%")
											);
				
			}

			return predicate;
			
		}, pageable)
				.stream()
				.map(teacher -> teacherConverter.convert(teacher))
				.collect(Collectors.toList());
		
	}
	
	
	@Override
	public Page<TeacherPresenter> getPaginatedTeachers(int page, int size, String sortBy) {
		Page<Teacher> paginatedTeachers = teacherRepository
				.findAll(
						PageRequest.of(
								page, 
								size,
								Sort.Direction.ASC, sortBy)
						);

		Page<TeacherPresenter> pageOfTeacherPresenter = paginatedTeachers
				.map(teacher -> teacherConverter.convert(teacher));

		
		return pageOfTeacherPresenter;

	}

	@Override
	public TeacherPresenter createTeacher(TeacherDomain teacherDomain) throws PersonAlreadyExistException {
		
		if(teacherRepository.existsByNIF(teacherDomain.getNIF()))
			throw new PersonAlreadyExistException();
		
		Teacher teacherModel = teacherConverter.convert(teacherDomain);
		
		teacherModel = teacherRepository.save(teacherModel);
		
		TeacherPresenter teacherPresenter = teacherConverter.convert(teacherModel);
		
		return teacherPresenter;
	}
	
	@Override
	public TeacherPresenter findTeacherByNIF(String teacherNIF) {
		Optional<Teacher> teacherOpt = Optional
						.ofNullable(teacherRepository.findByNIF(teacherNIF));
	
		Teacher teacherModel = teacherOpt.orElseThrow();
		
		TeacherPresenter teacherPresenter = teacherConverter.convert(teacherModel);
	
		return teacherPresenter;
	}

	@Override
	public TeacherPresenter deactivateTeacherByNIF(String teacherNIF) {
		Optional<Teacher> studentIdOpt = Optional.ofNullable(teacherRepository.findByNIF(teacherNIF));
		Teacher teacherModel = studentIdOpt.orElseThrow();
		
		teacherModel.setActive(false);
		
		teacherModel = teacherRepository.save(teacherModel);
		
		TeacherPresenter teacherPresenter = teacherConverter.convert(teacherModel);
		
		return teacherPresenter;
		
	}

	@Override
	public TeacherPresenter updateTeacher(String teacherNIF, TeacherDomain teacherDomain) {
		Optional<Teacher> teacherOpt = Optional.ofNullable(teacherRepository.findByNIF(teacherNIF));
		String teacherId = teacherOpt.orElseThrow().getId();
		
		Teacher teacherModel = teacherConverter.convert(teacherDomain);
		
		teacherModel.setId(teacherId);
		
		teacherModel = teacherRepository.save(teacherModel);
		
		TeacherPresenter teacherPresenter = teacherConverter.convert(teacherModel);
		
		return teacherPresenter;
		
	}


	
	
	
}
