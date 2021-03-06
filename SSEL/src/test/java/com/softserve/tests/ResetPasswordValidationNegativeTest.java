package com.softserve.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
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

import com.softserve.form.ResetPassword;
import com.softserve.service.UserService;
import com.softserve.validator.ResetPasswordValidator;

@RunWith(Parameterized.class)
public class ResetPasswordValidationNegativeTest {
	
	private ResetPasswordValidator resetPasswordValidation = new ResetPasswordValidator();
	private ResetPassword resetPassword = new ResetPassword();
	private UserService userService;
	
	public ResetPasswordValidationNegativeTest(String newPassword, String confirmPassword) {
		resetPassword.setPassword(newPassword);
		resetPassword.setConfirmPassword(confirmPassword);
	}
	
	@Before
	public void setUp() {
		userService = mock(UserService.class);
		resetPasswordValidation.setUserService(userService);
	}
	
	@Parameters
    public static Collection<Object[]> data() {
    	Object[][] data = new Object[][] {
    			{
    				"", ""
    			},
    			{
    				"fdsfsdf", "fdsfSdf"
    			},
    			{
    				"fdsfsd44", "fdsfsd44"
    			},
    			{
    				"fdsfFdfsd4", "fdsfFdfsd4"
    			},
    			{
    				"fdsfsdf547&77?", "fdsfsdf547&77?"
    			},
    			{
    				"FdfgdFds&*&", "FdfgdFds&*&"
    			},
    			{
    				"D3&", "D3&"
    			},
    			{
    				"gfgdF$??54545Fdfsddsfsdfsdfsdfdsfsdfsdfsdf",
    				"gfgdF$??54545Fdfsddsfsdfsdfsdfdsfsdfsdfsdf"
    			}
    	};
    	return Arrays.asList(data);
    }
    
	@Test
	public void testSupports() {
		assertTrue(resetPasswordValidation.supports(ResetPassword.class));
	}
	
	@Test
	public void testNotSupports() {
		assertFalse(resetPasswordValidation.supports(Object.class));
	}
	
	@Test
	public void testHasErrors() {
		when(userService.getUserByKey(anyString())).thenReturn(null);
		BindingResult errors = new BeanPropertyBindingResult(resetPassword, "resetPassword");
		resetPasswordValidation.validate(resetPassword, errors);
        assertTrue(errors.hasErrors());
	}
}
