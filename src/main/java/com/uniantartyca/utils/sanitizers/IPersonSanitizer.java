package com.uniantartyca.utils.sanitizers;

import com.uniantartyca.domain.PersonDomain;

public interface IPersonSanitizer {
	PersonDomain sanitize(PersonDomain personDomain);
}
