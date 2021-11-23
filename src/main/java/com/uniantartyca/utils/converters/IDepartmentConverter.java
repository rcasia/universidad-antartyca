package com.uniantartyca.utils.converters;

import com.uniantartyca.model.enums.Department;

public interface IDepartmentConverter {
	
	Department convert(String code);
	
	String convert(Department department);
}
