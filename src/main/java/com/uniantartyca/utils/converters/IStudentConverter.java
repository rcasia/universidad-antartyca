package com.uniantartyca.utils.converters;

import com.uniantartyca.domain.StudentDomain;
import com.uniantartyca.domain.StudentPresenter;
import com.uniantartyca.model.Student;

public interface IStudentConverter {

	Student convert(StudentDomain studentDomain);
	
	StudentPresenter convert(Student studentModel);

}
