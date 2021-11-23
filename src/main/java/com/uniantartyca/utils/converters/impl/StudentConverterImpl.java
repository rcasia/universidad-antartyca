package com.uniantartyca.utils.converters.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniantartyca.domain.StudentDomain;
import com.uniantartyca.domain.StudentPresenter;
import com.uniantartyca.model.Student;
import com.uniantartyca.model.enums.Department;
import com.uniantartyca.utils.converters.IDepartmentConverter;
import com.uniantartyca.utils.converters.IStudentConverter;

@Service("studentConverterImpl")
public class StudentConverterImpl implements IStudentConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IDepartmentConverter departmentConverter;
	
	@Override
	public Student convert(StudentDomain studentDomain) {
		
		Converter<Department, String> DepartmentToString = c -> departmentConverter.convert(c.getSource());
		modelMapper.addConverter(DepartmentToString,  Department.class, String.class);
		
		return modelMapper.map(studentDomain, Student.class);
	}

	@Override
	public StudentPresenter convert(Student studentModel) {
		
		Converter<Department, String> DepartmentToString = c -> departmentConverter.convert(c.getSource());
		modelMapper.addConverter(DepartmentToString,  Department.class, String.class);
		
		return modelMapper.map(studentModel, StudentPresenter.class);
	}
}
