package com.uniantartyca.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniantartyca.controllers.filters.DomainFilter;
import com.uniantartyca.domain.SubjectDomain;
import com.uniantartyca.domain.SubjectPresenter;
import com.uniantartyca.services.ISubjectService;
import com.uniantartyca.utils.validators.ISubjectValidator;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/v1/subjects")
public class SubjectController {
	
	@Autowired
	private ISubjectService subjectService;
	
	@Autowired
	private ISubjectValidator subjectValidator;
	
	@GetMapping
	public ResponseEntity<?> getPaginatedSubjects(
			@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size,
			@RequestParam(name = "sortBy") Optional<String> sortBy,
			@RequestBody DomainFilter filter
			) {
		
		Pageable pageable = PageRequest.of(page, size);
		
		try {
			
		Page<SubjectPresenter> subjectPresenterPage = subjectService.getSubjectPageFiltered(filter, pageable);
		
		return new ResponseEntity<>(subjectPresenterPage, HttpStatus.OK);
		
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getSubjectById(@PathVariable(name = "id") String subjectId){
		try {
			
			SubjectPresenter subjectPresenter = subjectService.getSubjectById(subjectId);
			
			return new ResponseEntity<>( subjectPresenter, HttpStatus.OK);
			
			
		} catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/department/{department}")
	public ResponseEntity<?> getSubjectByDepartment(@PathVariable(name = "department") String department){
		try {
			
			List<SubjectPresenter> subjectPresenterList = subjectService.getAllSubjectsByDepartment(department);
			
			return new ResponseEntity<>( subjectPresenterList, HttpStatus.OK);
			
			
		} catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "partname/{partname}")
	public ResponseEntity<?> getSomeSubjectsByPartname(@PathVariable(name = "partname") String partname){
		try {
			
			List<SubjectPresenter> subjectPresenterList = subjectService.getSomeSubjectsByPartname(partname);
			
			return new ResponseEntity<>( subjectPresenterList, HttpStatus.OK);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createSubject(@RequestBody SubjectDomain subjectDomain){
		try {
			
			if(!subjectValidator.validate(subjectDomain))
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			
			SubjectPresenter createdSubject = subjectService.createSubject(subjectDomain);
			
			return new ResponseEntity<>(createdSubject, HttpStatus.OK);
		
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/{subjectId}/students/{studentId}")
	public ResponseEntity<?> addStudentToSubject(@PathVariable(name = "subjectId") String subjectId,
													@PathVariable(name = "studentId") String studentId){
		try {
			
			SubjectPresenter updatedSubject = subjectService.addStudentToSubject(studentId, subjectId);
			
			return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
			
			
		} catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{subjectId}/students/{studentId}")
	public ResponseEntity<?> deleteStudentFromSubject(@PathVariable(name = "subjectId") String subjectId,
													@PathVariable(name = "studentId") String studentId){
		try {
			
			SubjectPresenter updatedSubject = subjectService.deleteStudentFromSubject(studentId, subjectId);
			
			return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
			
			
		} catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/{subjectId}/teachers/{teacherId}")
	public ResponseEntity<?> addTeacherToSubject(@PathVariable(name = "subjectId") String subjectId,
													@PathVariable(name = "teacherId") String teacherId){
		try {
			
			SubjectPresenter updatedSubject = subjectService.addTeacherToSubject(teacherId, subjectId);
			
			return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
			
			
		} catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{subjectId}/teachers/{teacherId}")
	public ResponseEntity<?> deleteTeacherFromSubject(@PathVariable(name = "subjectId") String subjectId,
													@PathVariable(name = "teacherId") String teacherId){
		try {
			
			SubjectPresenter updatedSubject = subjectService.deleteTeacherFromSubject(teacherId, subjectId);
			
			return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
			
			
		} catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/{subjectId}/coordinator/{teacherId}")
	public ResponseEntity<?> updateCoordinator(@PathVariable(name = "subjectId") String subjectId,
													@PathVariable(name = "teacherId") String teacherId){
		try {
			
			SubjectPresenter updatedSubject = subjectService.updateCoordinator(teacherId, subjectId);
			
			return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
			
			
		} catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deactivateSubjectById(@PathVariable(name = "id") String subjectId){
		try {
			
			SubjectPresenter subjectPresenter = subjectService.deactivateSubjectById(subjectId);
			
			return new ResponseEntity<>( subjectPresenter, HttpStatus.OK);
			
			
		} catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
