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
import com.softserve.validator.RegistrationValidation;

@RunWith(Parameterized.class)
public class RegistrationValidationNegativeTest {
	
	private RegistrationValidation registrationValidation = new RegistrationValidation();
	private Registration registration = new Registration();
	private UserService userService;
	
	public RegistrationValidationNegativeTest(String firstName, String lastName, 
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
    				"Fsd", "Fdsfsd", "gdfgfdg@gmail.com", "fdsFd54fd", "fsdfsd"
    			},
    			{
    				"fdsfs545dfsd", "fdsfsd//fsd", "fsdfds@fsdfs", "fsdfsdf54", "fsdfsdf54"
    			}, 
    			{
    				"Fdsfsdf54353sd", "fdsffdsf&**/sdfsdfsd", "fsdf45fds@fsd34fs", 
    				"fsdffsd4sdf54", "fsdffsd4sdf54*5%%"
    			},
    			{
    				"", "", "", "", ""
    			},
    			{
    				"fds", "fd", "fsdf45fds@fsd34fs.com", "fsd", "fsd"
    			},
    			{
    				"Fdsfsdf54353sd", "fdsffdsf&**/sdfsdfsd", "fsdf45fds@fsd34fs",
    				"fsdffsd4sdf54", "fsdffsd4sdf54"
    			},
    			{
    				"FdsfFd53sd", "FdsffdFfdsdfsdfsd", "fsdf45fds@fsd34fs", 
    				"fsdffsd4sdf54dfs43fd**fds?fds", "fsdffsd4sdf54dfs43fd**fds?fds"
    			},
    			{
    				"Fdsfsd%sd", "fdsffdsf&**/sdfsdfsd", "fsdf45fds@fsd34fs", 
    				"Fsdffsd4sdf54", "fsdffsd4sdf54"
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
		when(userService.isExist(registration.getEmail())).thenReturn(true);
		BindingResult errors = new BeanPropertyBindingResult(registration, "registration");
		registrationValidation.validate(registration, errors);
        assertTrue(errors.hasErrors());
	}

}
