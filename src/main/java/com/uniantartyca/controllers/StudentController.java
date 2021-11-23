package com.uniantartyca.controllers;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import com.uniantartyca.domain.StudentDomain;
import com.uniantartyca.domain.StudentPresenter;
import com.uniantartyca.exceptions.PersonAlreadyExistException;
import com.uniantartyca.services.IStudentService;
import com.uniantartyca.utils.sanitizers.IPersonSanitizer;
import com.uniantartyca.utils.validators.IStudentValidator;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/v1/students")
public class StudentController {

	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private IStudentValidator studentValidator;
	
	
	@Autowired
	private IPersonSanitizer personSanitizer;
	
	@PostMapping(value = "/filter")
	public ResponseEntity<?> getPageOfStudentsFiltered(
			@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size,
			@RequestParam(name = "sortBy") Optional<String> sortBy,
			@RequestParam(name = "direction") Optional<String> direction,
			@RequestBody DomainFilter filter
			) {
		
		System.out.println(direction);
		
		try {
			Sort.Direction dir = Sort.Direction.ASC;
			
			if(direction.orElse("").equals("desc")) {
				dir = Sort.Direction.DESC;
			}
			
			String sortByField = sortBy.orElse("surname");
			if(direction.isEmpty()) {
				sortByField = "surname";
			}
			
			Pageable pageable = PageRequest.of(page, size, dir, sortByField);
			
			Page<StudentPresenter> pageOfStudents = studentService.getPageOfStudentsFiltered(pageable, filter);
			
			return new ResponseEntity<>(pageOfStudents, HttpStatus.OK);
		
		} catch (Exception e) {
			
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value = "/{NIF}")
	public ResponseEntity<?> findStudentbyNIF(
				@PathVariable(name = "NIF") String studentNIF) {
		
		
		try {
			
			StudentPresenter studentFound = studentService
												.findStudentByNIF(studentNIF);
			
			return new ResponseEntity<>(studentFound, HttpStatus.OK);
			
		} catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		
		
	}
	
	
	@PostMapping
	public ResponseEntity<?> createStudent(@RequestBody StudentDomain studentDomain) {
		
		try {
			
			if(!studentValidator.validate(studentDomain))
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			
			studentDomain = (StudentDomain) personSanitizer.sanitize(studentDomain);
			
			StudentPresenter createdStudent = studentService
													.createStudent(studentDomain);
			
			return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
			
		} catch (PersonAlreadyExistException paee) {
			
			return new ResponseEntity<>(HttpStatus.CONFLICT);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable(name = "id") String StudentId,
								@RequestBody StudentDomain studentDomain) {
		
		try {
			
			if(!studentValidator.validate(studentDomain))
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			
			StudentPresenter updatedStudent = studentService.updateStudent(StudentId, studentDomain);
			
			return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(value = "/{NIF}")
	public ResponseEntity<?> deactivateStudent(@PathVariable(name = "NIF") String StudentNIF) {
		try {
			
			StudentPresenter studentPresenter = studentService.deactivateStudentByNIF(StudentNIF);
			return new ResponseEntity<>(studentPresenter, HttpStatus.OK);
			
		}catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
