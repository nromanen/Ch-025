package com.softserve.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.softserve.form.Registration;
import com.softserve.service.UserService;
import com.softserve.validator.RegistrationValidator;

@RunWith(Parameterized.class)
public class RegistrationValidationPositiveTest {

	private RegistrationValidator registrationValidation = new RegistrationValidator();
	private Registration registration = new Registration();
	private UserService userService;

	public RegistrationValidationPositiveTest(String firstName, String lastName,
			String email, String password, String confirmPassword) {
		registration.setFirstName(firstName);
		registration.setLastName(lastName);
		registration.setEmail(email);
		registration.setPassword(password);
		registration.setConfirmPassword(confirmPassword);
	}

    @Parameters
    public static Collection<Object[]> data() {
    	Object[][] data = new Object[][] {
    			{
    				"Fsd", "Fdsfsd", "gdfgfdg@gmail.com", "fdsFd54fd*&", "fdsFd54fd*&"
    			},
    			{
    				"Fsd", "Fdsfsd", "Fdfg43fdg@gmail23.com", "fdsFd54fd*&", "fdsFd54fd*&"
    			},
    			{
    				"Fsd", "Fdsfsd", "gdfg_fdg@gmail.com", "fdsFd54fd//*&", "fdsFd54fd//*&"
    			},
    			{
    				"Fsd", "Fdsfsd", "gdfgf.dg@i.ua", "12fdsF//d54fd*&", "12fdsF//d54fd*&"
    			},
    			{
    				"Вася", "Пупкін", "vasya.pupkin@mail.ru", "fdsFd54fd*&", "fdsFd54fd*&"
    			},
    			{
    				"Іван", "Іваненко", "vanya@yahoo.com", "QweFrty34*&", "QweFrty34*&"
    			},
    			{
    				"Fdsds", "Brbrbf", "VasYa@gmail.com", "f--dsfdFd54fd*&", "f--dsfdFd54fd*&"
    			},
    			{
    				"Fsd", "Fdsfsd", "gdfg_111@gmail.com", "f&fdfdsFd54fd*&", "f&fdfdsFd54fd*&"
    			}
    	};
    	return Arrays.asList(data);
    }

	@Before
	public void setUp() {
		userService = mock(UserService.class);
		registrationValidation.setUserService(userService);
	}

	@Test
	public void testSupports() {
		assertTrue(registrationValidation.supports(Registration.class));
	}

	@Test
	public void testNotSupports() {
		assertFalse(registrationValidation.supports(Object.class));
	}

	@Test
	public void testHasErrors() {
		when(userService.isExist(registration.getEmail())).thenReturn(false);
		BindingResult errors = new BeanPropertyBindingResult(registration, "registration");
		registrationValidation.validate(registration, errors);
        assertFalse(errors.hasErrors());
	}

}