package com.uniantartyca.utils.converters;

import com.uniantartyca.domain.SubjectDomain;
import com.uniantartyca.domain.SubjectPresenter;
import com.uniantartyca.model.Subject;

public interface ISubjectConverter {


	SubjectPresenter convert(Subject subjectModel);

	Subject convert(SubjectDomain subjectDomain);

}
