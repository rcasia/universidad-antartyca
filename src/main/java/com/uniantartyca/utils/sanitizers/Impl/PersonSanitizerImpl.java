package com.uniantartyca.utils.sanitizers.Impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.uniantartyca.domain.PersonDomain;
import com.uniantartyca.utils.sanitizers.IPersonSanitizer;

@Service("personSanitizerImpl")
public class PersonSanitizerImpl implements IPersonSanitizer {

	@Override
	public PersonDomain sanitize(PersonDomain person) {
		
		// Capitalize name and surname
		person.setName(StringUtils.capitalize(person.getName().toLowerCase()));
		person.setSurname(StringUtils.capitalize(person.getSurname().toLowerCase()));
		
		// NIF letters to upper case
		person.setNIF(person.getNIF().toUpperCase());
		
		
		return person;
	}

}
