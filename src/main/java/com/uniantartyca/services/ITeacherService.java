package com.uniantartyca.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uniantartyca.controllers.filters.DomainFilter;
import com.uniantartyca.domain.TeacherDomain;
import com.uniantartyca.domain.TeacherPresenter;
import com.uniantartyca.exceptions.PersonAlreadyExistException;

public interface ITeacherService {

	TeacherPresenter createTeacher(TeacherDomain teacherDomain) throws PersonAlreadyExistException;


	TeacherPresenter findTeacherByNIF(String teacherNIF);

	Page<TeacherPresenter> getPaginatedTeachers(int page, int size, String orElse);

	List<TeacherPresenter> getTeachersByPartname(String partname);

	Page<TeacherPresenter> getTeacherPageFiltered(DomainFilter filter, Pageable pageable);


	TeacherPresenter deactivateTeacherByNIF(String teacherNIF);


	TeacherPresenter updateTeacher(String teacherNIF, TeacherDomain teacherDomain);



}
