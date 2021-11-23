package com.uniantartyca.utils.converters.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniantartyca.domain.SubjectDomain;
import com.uniantartyca.domain.SubjectPresenter;
import com.uniantartyca.model.Subject;
import com.uniantartyca.model.enums.Department;
import com.uniantartyca.utils.converters.IDepartmentConverter;
import com.uniantartyca.utils.converters.ISubjectConverter;


@Service("subjectConverterImpl")
public class SubjectConverterImpl implements ISubjectConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IDepartmentConverter departmentConverter;
	
	@Override
	public SubjectPresenter convert(Subject subjectModel) {
		
		Converter<Department, String> DepartmentToString = c -> departmentConverter.convert(c.getSource());
		modelMapper.addConverter(DepartmentToString,  Department.class, String.class);
		
		return modelMapper.map(subjectModel, SubjectPresenter.class);
	}
	
	@Override
	public Subject convert(SubjectDomain subjectDomain) {
		
		Converter<String, Department> StringToEnum = c -> departmentConverter.convert(c.getSource());		
		modelMapper.addConverter(StringToEnum, String.class, Department.class);
		
		return modelMapper.map(subjectDomain, Subject.class);
		
		
	}
	
}
