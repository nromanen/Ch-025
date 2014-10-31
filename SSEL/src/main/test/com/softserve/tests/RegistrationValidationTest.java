package com.softserve.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import com.softserve.form.Registration;
import com.softserve.service.UserService;
import com.softserve.validator.RegistrationValidation;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationValidationTest {
	
	private RegistrationValidation registrationValidation = new RegistrationValidation();
	private Registration registration = new Registration();
	private UserService userService;
	private BindException errors;
	
	
	@Before
	public void setUp() {
		registrationValidation = mock(RegistrationValidation.class);
		registration = mock(Registration.class);
		userService = mock(UserService.class);
	}
	
	@Test
	public void testSupports() {
		when(registrationValidation.supports(Registration.class)).thenReturn(true);
		assertTrue(registrationValidation.supports(Registration.class));
	}
	
	@Test
	public void testNotSupports() {
		assertFalse(registrationValidation.supports(Object.class));
	}
	
	@Test
	public void testHasErrors() {
		registration.setConfirmPassword("gdfgf45tfdf");
		registration.setEmail("fdsfdssfdsfds54353fd");
		registration.setFirstName("fsdfsdfsd");
		registration.setLastName("fdsfsdf");
		registration.setPassword("fsdfdsfsdfsd534543fdf");
		when(userService.isExist(registration.getEmail())).thenReturn(true);
		assertTrue(userService.isExist(registration.getEmail()));
		Errors errors = new BeanPropertyBindingResult(registration, "registration");
        registrationValidation.validate(registration, errors);
        assertFalse(errors.hasErrors());
	}

}
