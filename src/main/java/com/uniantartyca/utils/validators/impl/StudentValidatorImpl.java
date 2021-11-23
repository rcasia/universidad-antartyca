package com.uniantartyca.utils.validators.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.uniantartyca.domain.StudentDomain;
import com.uniantartyca.utils.validators.IStudentValidator;

@Service("studentValidatorImpl")
public class StudentValidatorImpl implements IStudentValidator{
	@Override
	public boolean validate(StudentDomain student) {
		

		if(!StringUtils.hasText(student.getName()))
			return false;
		
		if(!StringUtils.hasText(student.getSurname()))
			return false;
		
		if(student.getBirthdate() == null || student.getBirthdate().after( new Date() ))
			return false;
		
		if(!StringUtils.hasText(student.getNIF()) || !validateNIF(student.getNIF()))
			return false;
		
		Float mark = student.getAverageGlobalMark();
		if(mark != null && (0 > mark || mark > 10))
			return false;
		
		return true;
	}
	
	private boolean validateNIF(String NIF) {
		
		if(NIF.length() != 9)
			return false;
			
		return true;
	}
}
