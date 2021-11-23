package com.uniantartyca.controllers;

import java.util.List;
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
import com.uniantartyca.domain.TeacherDomain;
import com.uniantartyca.domain.TeacherPresenter;
import com.uniantartyca.exceptions.PersonAlreadyExistException;
import com.uniantartyca.services.ITeacherService;
import com.uniantartyca.utils.validators.ITeacherValidator;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/v1/teachers")
public class TeacherController {
	
	@Autowired
	private ITeacherValidator teacherValidator;
	
	@Autowired
	private ITeacherService teacherService;
	
	@PostMapping(value = "/filter")
	public ResponseEntity<?> getTeachersPage(
			@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size,
			@RequestParam(name = "sortBy") Optional<String> sortBy,
			@RequestParam(name = "direction") Optional<String> direction,
			@RequestBody DomainFilter filter
			) {
		
		
		
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
			
			Page<TeacherPresenter> teachersPage = teacherService.getTeacherPageFiltered(filter, pageable);
			
			return new ResponseEntity<>(teachersPage, HttpStatus.OK);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createTeacher(@RequestBody TeacherDomain teacherDomain){
		try {
			
			if(!teacherValidator.validate(teacherDomain))
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			
			TeacherPresenter createdTeacher = teacherService.createTeacher(teacherDomain);
			
			return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
			
		} catch ( PersonAlreadyExistException paee ) {
			
			return new ResponseEntity<>(HttpStatus.CONFLICT);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{NIF}")
	public ResponseEntity<?> findTeacherByNIF(
						@PathVariable(name = "NIF") String teacherNIF) {
		try {
			
			TeacherPresenter teacherFound = teacherService
												.findTeacherByNIF(teacherNIF);
			
			return new ResponseEntity<>(teacherFound, HttpStatus.OK);
			
		} catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@GetMapping(value = "/partname/{partname}")
	public ResponseEntity<?> getTenTeachersByPartname(@PathVariable(name = "partname") String partname){
		
		try {
			
			List<TeacherPresenter> teacherPresenterList = teacherService.getTeachersByPartname(partname);
			
			return new ResponseEntity<>(teacherPresenterList, HttpStatus.OK);
		
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@DeleteMapping(value = "/{NIF}")
	public ResponseEntity<?> deactivateTeacher(
								@PathVariable(name = "NIF") String teacherNIF){
		try {
			
			TeacherPresenter teacherPresenter = teacherService.deactivateTeacherByNIF(teacherNIF);
			return new ResponseEntity<>(teacherPresenter, HttpStatus.OK);
			
		}catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/{NIF}")
	public ResponseEntity<?> updateTeacher(@PathVariable(name = "NIF") String TeacherNIF,
								@RequestBody TeacherDomain teacherDomain) {
		
		try {
			
			if(!teacherValidator.validate(teacherDomain))
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			
			TeacherPresenter updatedTeacher = teacherService.updateTeacher(TeacherNIF, teacherDomain);
			
			return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
