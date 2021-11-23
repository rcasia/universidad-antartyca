package com.uniantartyca.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uniantartyca.controllers.filters.DomainFilter;
import com.uniantartyca.domain.SubjectDomain;
import com.uniantartyca.domain.SubjectPresenter;

public interface ISubjectService {

	SubjectPresenter createSubject(SubjectDomain subjectDomain);

	SubjectPresenter addStudentToSubject(String studentId, String subjectId);

	SubjectPresenter getSubjectById(String subjectId);

	SubjectPresenter addTeacherToSubject(String teacherId, String subjectId);

	SubjectPresenter updateCoordinator(String teacherId, String subjectId);

	Page<SubjectPresenter> getSubjectPageFiltered(DomainFilter filter, Pageable pageable);

	List<SubjectPresenter> getAllSubjectsByDepartment(String departmentCode);

	List<SubjectPresenter> getSomeSubjectsByPartname(String partname);

	SubjectPresenter deleteStudentFromSubject(String studentId, String subjectId);

	SubjectPresenter deleteTeacherFromSubject(String teacherId, String subjectId);

	SubjectPresenter deactivateSubjectById(String subjectId);


}
