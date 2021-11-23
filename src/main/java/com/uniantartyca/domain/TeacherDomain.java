package com.uniantartyca.domain;

public class TeacherDomain extends PersonDomain {
	private boolean isDepartmentChief;

	public boolean isDepartmentChief() {
		return isDepartmentChief;
	}

	public void setDepartmentChief(boolean isDepartmentChief) {
		this.isDepartmentChief = isDepartmentChief;
	}
}
