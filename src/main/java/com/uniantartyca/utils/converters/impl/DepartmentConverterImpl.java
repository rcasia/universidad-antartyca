package com.uniantartyca.utils.converters.impl;

import org.springframework.stereotype.Service;

import com.uniantartyca.model.enums.Department;
import com.uniantartyca.utils.converters.IDepartmentConverter;

@Service("departmentConverter")
public class DepartmentConverterImpl implements IDepartmentConverter {

	@Override
	public Department convert(String code) {
		return Department.decode(code);
	}

	@Override
	public String convert(Department department) {
		return department.getCode();
	}
	
}
