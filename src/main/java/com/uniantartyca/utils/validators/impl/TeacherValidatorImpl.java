package com.uniantartyca.utils.validators.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.uniantartyca.domain.TeacherDomain;
import com.uniantartyca.utils.validators.ITeacherValidator;

@Service("teacherValidatorImpl")
public class TeacherValidatorImpl implements ITeacherValidator {
	@Override
	public boolean validate(TeacherDomain teacher) {
		

		if(!StringUtils.hasText(teacher.getName()))
			return false;
		
		if(!StringUtils.hasText(teacher.getSurname()))
			return false;
		
		if(teacher.getBirthdate() == null || teacher.getBirthdate().after( new Date() ))
			return false;
		
		if(!StringUtils.hasText(teacher.getNIF()) || !validateNIF(teacher.getNIF()))
			return false;
		
		
		return true;
	}
	
	private boolean validateNIF(String NIF) {
		
		if(NIF.length() != 9)
			return false;
			
		return true;
	}
}
