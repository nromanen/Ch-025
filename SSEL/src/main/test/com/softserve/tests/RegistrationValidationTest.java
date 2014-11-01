package com.softserve.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.softserve.form.Registration;
import com.softserve.service.UserService;
import com.softserve.service.impl.UserServiceImpl;
import com.softserve.validator.RegistrationValidation;

@RunWith(Parameterized.class)
public class RegistrationValidationTest {
	
	private RegistrationValidation registrationValidation = new RegistrationValidation();
	private Registration registration = new Registration();
	private UserService userService = new UserServiceImpl();
	
	public RegistrationValidationTest(String firstName, String lastName, 
			String email, String password) {
		registration.setConfirmPassword(password);
		registration.setEmail(email);
		registration.setLastName(lastName);
		registration.setFirstName(firstName);
	}
	
    @Parameters
    public static Collection<Object[]> data() {
    	Object[][] data = new Object[][] {
    			{
    				"Fsd", "Fdsfsd", "gdfgfdg@gmail.com", "fdsFd54fd"
    			},
    			{
    				"fdsfs545dfsd", "fdsfsd//fsd", "fsdfds@fsdfs", "fsdfsdf54"
    			}, 
    			{
    				"fdsfsdf54353sd", "fdsffdsf&**/sdfsdfsd", "fsdf45fds@fsd34fs", "fsdffsd4sdf54"
    			},
    			{
    				"", "", "", ""
    			}
    	};
    	return Arrays.asList(data);
    }
	
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
		when(userService.isExist(registration.getEmail())).thenReturn(true);
		//assertTrue(userService.isExist(registration.getEmail()));
		Errors errors = new BeanPropertyBindingResult(registration, "registration");
		doCallRealMethod().when(registrationValidation).validate(registration, errors);
		//when(registrationValidation.validate(registration, errors)).thenReturn(true);
        assertTrue(errors.hasErrors());
	}

}
