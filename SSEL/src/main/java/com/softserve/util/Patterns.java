package com.softserve.util;

public class Patterns {

	public static final String NAME_PATTERN = "[A-ZА-ЯІЇЄ]{1}[A-ZА-ЯІЇЄa-zа-яіїє]{1,30}";
	public static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[//@/./&/!#/$%/^/*/?])(?!.*\\s).{4,20}$";
	public static final String EMAIL_PATTERN = "[A-Za-z0-9_\\.-]{1,30}@[A-Za-z0-9_\\.-]{1,30}";
	public static final int PASSWORD_MIN_LENGTH = 4;
}