package com.qsp.util;

public enum UserType {
	Go,PRO,MAX;
	public static UserType getUserType(int  ordinal) {
		switch(ordinal) {
		case 1:
			return PRO;
		case 2:
			return MAX;
		default:
			return Go;
		}
	}
	public static String[] getAllTypes() {
		return new String[] {Go.name(),PRO.name(),MAX.name()};
	}
}
