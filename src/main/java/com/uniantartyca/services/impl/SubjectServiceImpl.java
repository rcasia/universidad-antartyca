package com.uniantartyca.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.uniantartyca.controllers.filters.DomainFilter;
import com.uniantartyca.domain.SubjectDomain;
import com.uniantartyca.domain.SubjectPresenter;
import com.uniantartyca.model.Student;
import com.uniantartyca.model.Subject;
import com.uniantartyca.model.Teacher;
import com.uniantartyca.model.enums.Department;
import com.uniantartyca.repositories.StudentRepository;
import com.uniantartyca.repositories.SubjectRepository;
import com.uniantartyca.repositories.TeacherRepository;
import com.uniantartyca.services.ISubjectService;
import com.uniantartyca.utils.converters.ISubjectConverter;

@Service("subjectServiceImpl")
public class SubjectServiceImpl implements ISubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private ISubjectConverter subjectConverter;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public Page<SubjectPresenter> getSubjectPageFiltered(DomainFilter filter, Pageable pageable){
		
		//// FILTER CONSTANTS ////
		final String NAME = filter.getName();
		
		return subjectRepository.findAll(
				(Specification<Subject>) (root, query, criteriaBuilder) -> {
					
			Predicate predicate = criteriaBuilder.conjunction();
			
			// Excluye inactivos //
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("active"), true));
			
			if(StringUtils.hasText(NAME)) {
				predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%"+NAME+"%"));
			}
			
			return predicate;
			
		}, pageable)
				
				.map(subject -> subjectConverter.convert(subject));
		
		
	}
	
	@Override
	public List<SubjectPresenter> getSomeSubjectsByPartname(String partname){
	
		Pageable pageable = PageRequest.of(0, 5, Direction.DESC, "name");
		
		return subjectRepository.findAll(
				(Specification<Subject>) (root, query, criteriaBuilder) -> {
					
			Predicate predicate = criteriaBuilder.conjunction();
			
			// Excluye inactivos //
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("active"), true));
			
			if(StringUtils.hasText(partname)) {
				predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%"+partname+"%"));
			}
			
			return predicate;
			
		}, pageable)
				.get()
				.map(subject -> subjectConverter.convert(subject))
				.collect(Collectors.toList());
		
		
	}
	
	@Override
	public SubjectPresenter getSubjectById(String subjectId) {
		
		Subject subjectModel = subjectRepository
				.findById(subjectId)
				.orElseThrow();

		SubjectPresenter subjectPresenter = subjectConverter.convert(subjectModel);
		
		return subjectPresenter;
	}
	
	@Override
	public SubjectPresenter deactivateSubjectById(String subjectId) {
		
		Subject subjectModel = subjectRepository
				.findById(subjectId)
				.orElseThrow();
		
		subjectModel.setActive(false);
		
		subjectModel = subjectRepository.save(subjectModel);

		SubjectPresenter subjectPresenter = subjectConverter.convert(subjectModel);
		
		return subjectPresenter;
	}
	

	@Override
	public SubjectPresenter createSubject(SubjectDomain subjectDomain) {
		
		Subject subjectModel = subjectConverter.convert(subjectDomain);
		
		
		subjectModel = subjectRepository.save(subjectModel);
		
		SubjectPresenter subjectPresenter = subjectConverter.convert(subjectModel);
		
		return subjectPresenter;
	}
	
	@Override
	public SubjectPresenter addStudentToSubject(String studentId, String subjectId) {
		
		Subject subjectModel = subjectRepository
									.findById(subjectId)
									.orElseThrow();
		
		Student studentModel = studentRepository
									.findById(studentId)
									.orElseThrow();
		
		subjectModel.addStudent(studentModel);
		
		subjectModel = subjectRepository.save(subjectModel);
		
		SubjectPresenter subjectPresenter = subjectConverter.convert(subjectModel);
		
		return subjectPresenter;
	}
	
	@Override
	public SubjectPresenter deleteStudentFromSubject(String studentId, String subjectId) {
		
		Subject subjectModel = subjectRepository
									.findById(subjectId)
									.orElseThrow();
		
		Student studentModel = studentRepository
									.findById(studentId)
									.orElseThrow();
		
		subjectModel.deleteStudent(studentModel);
		
		subjectModel = subjectRepository.save(subjectModel);
		
		SubjectPresenter subjectPresenter = subjectConverter.convert(subjectModel);
		
		return subjectPresenter;
	}
	
	@Override
	public SubjectPresenter deleteTeacherFromSubject(String teacherId, String subjectId) {
		Subject subjectModel = subjectRepository
				.findById(subjectId)
				.orElseThrow();

		Teacher teacherModel = teacherRepository
						.findById(teacherId)
						.orElseThrow();
		
		subjectModel.deleteTeacher(teacherModel);
		
		subjectModel = subjectRepository.save(subjectModel);
		
		SubjectPresenter subjectPresenter = subjectConverter.convert(subjectModel);
		
		return subjectPresenter;
	}

	@Override
	public SubjectPresenter addTeacherToSubject(String teacherId, String subjectId) {
		Subject subjectModel = subjectRepository
				.findById(subjectId)
				.orElseThrow();

		Teacher teacherModel = teacherRepository
						.findById(teacherId)
						.orElseThrow();
		
		subjectModel.addTeacher(teacherModel);
		
		subjectModel = subjectRepository.save(subjectModel);
		
		SubjectPresenter subjectPresenter = subjectConverter.convert(subjectModel);
		
		return subjectPresenter;
	}
	
	@Override
	public SubjectPresenter updateCoordinator(String teacherId, String subjectId) {
		Subject subjectModel = subjectRepository
				.findById(subjectId)
				.orElseThrow();

		Teacher teacherModel = teacherRepository
						.findById(teacherId)
						.orElseThrow();
		
		subjectModel.setCoordinator(teacherModel);
		
		subjectModel = subjectRepository.save(subjectModel);
		
		SubjectPresenter subjectPresenter = subjectConverter.convert(subjectModel);
		
		return subjectPresenter;
	}
	
	@Override
	public List<SubjectPresenter> getAllSubjectsByDepartment(String departmentCode){
		
		
		return subjectRepository.findAllSubjectByDepartmentAndActiveTrue(Department.decode(departmentCode))
										.stream()
										.map(subject -> subjectConverter.convert(subject))
										.collect(Collectors.toList());
	}

	

}
