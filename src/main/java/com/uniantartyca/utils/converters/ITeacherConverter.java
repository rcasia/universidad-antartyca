package com.uniantartyca.utils.converters;

import com.uniantartyca.domain.TeacherDomain;
import com.uniantartyca.domain.TeacherPresenter;
import com.uniantartyca.model.Teacher;

public interface ITeacherConverter {

	Teacher convert(TeacherDomain teacherDomain);

	TeacherPresenter convert(Teacher teacherModel);

}
