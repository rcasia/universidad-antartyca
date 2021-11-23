package com.uniantartyca.utils.validators.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.uniantartyca.domain.SubjectDomain;
import com.uniantartyca.utils.validators.ISubjectValidator;

@Service("subjectValidatorImpl")
public class SubjectValidatorImpl implements ISubjectValidator{
	@Override
	public boolean validate(SubjectDomain subject) {
		

		if(!StringUtils.hasText(subject.getName()))
			return false;

		if(!StringUtils.hasText(subject.getDepartment()))
			return false;
		
		if(subject.getCoordinator() == null)
			return false;
		
		return true;
	}
}
