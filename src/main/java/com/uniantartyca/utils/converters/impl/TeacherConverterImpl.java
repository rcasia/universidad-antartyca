package com.uniantartyca.utils.converters.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniantartyca.domain.TeacherDomain;
import com.uniantartyca.domain.TeacherPresenter;
import com.uniantartyca.model.Teacher;
import com.uniantartyca.model.enums.Department;
import com.uniantartyca.utils.converters.IDepartmentConverter;
import com.uniantartyca.utils.converters.ITeacherConverter;

@Service("teacherConverterImpl")
public class TeacherConverterImpl implements ITeacherConverter {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private IDepartmentConverter departmentConverter;
	
	@Override
	public Teacher convert(TeacherDomain teacherDomain) {

		Converter<Department, String> DepartmentToString = c -> departmentConverter.convert(c.getSource());
		modelMapper.addConverter(DepartmentToString,  Department.class, String.class);
		
		return modelMapper.map(teacherDomain, Teacher.class);
	}

	@Override
	public TeacherPresenter convert(Teacher teacherModel) {

		Converter<Department, String> DepartmentToString = c -> departmentConverter.convert(c.getSource());
		modelMapper.addConverter(DepartmentToString,  Department.class, String.class);
		
		return modelMapper.map(teacherModel, TeacherPresenter.class);
	}
}
