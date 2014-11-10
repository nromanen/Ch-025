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
public class RegistrationValidationPositiveTest {
<<<<<<< HEAD:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java

	private RegistrationValidation registrationValidation = new RegistrationValidation();
	private Registration registration = new Registration();
	private UserService userService;

	public RegistrationValidationPositiveTest(String firstName, String lastName,
=======
	
	private RegistrationValidation registrationValidation = new RegistrationValidation();
	private Registration registration = new Registration();
	private UserService userService;
	
	public RegistrationValidationPositiveTest(String firstName, String lastName, 
>>>>>>> f98bdf9d8c3c1c6923dc3908adeab57e6a2fe15c:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java
			String email, String password, String confirmPassword) {
		registration.setFirstName(firstName);
		registration.setLastName(lastName);
		registration.setEmail(email);
		registration.setPassword(password);
		registration.setConfirmPassword(confirmPassword);
	}
<<<<<<< HEAD:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java

=======
	
>>>>>>> f98bdf9d8c3c1c6923dc3908adeab57e6a2fe15c:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java
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
<<<<<<< HEAD:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java
    				"����", "�����", "vasya.pupkin@mail.ru", "fdsFd54fd*&", "fdsFd54fd*&"
    			},
    			{
    				"����", "��������", "vanya@yahoo.com", "QweFrty34*&", "QweFrty34*&"
=======
    				"Вася", "Пупкін", "vasya.pupkin@mail.ru", "fdsFd54fd*&", "fdsFd54fd*&"
    			},
    			{
    				"Іван", "Іваненко", "vanya@yahoo.com", "QweFrty34*&", "QweFrty34*&"
>>>>>>> f98bdf9d8c3c1c6923dc3908adeab57e6a2fe15c:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java
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
<<<<<<< HEAD:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java

=======
	
>>>>>>> f98bdf9d8c3c1c6923dc3908adeab57e6a2fe15c:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java
	@Before
	public void setUp() {
		userService = mock(UserService.class);
		registrationValidation.setUserService(userService);
	}
<<<<<<< HEAD:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java

=======
	
>>>>>>> f98bdf9d8c3c1c6923dc3908adeab57e6a2fe15c:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java
	@Test
	public void testSupports() {
		assertTrue(registrationValidation.supports(Registration.class));
	}
<<<<<<< HEAD:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java

=======
	
>>>>>>> f98bdf9d8c3c1c6923dc3908adeab57e6a2fe15c:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java
	@Test
	public void testNotSupports() {
		assertFalse(registrationValidation.supports(Object.class));
	}
<<<<<<< HEAD:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java

=======
	
>>>>>>> f98bdf9d8c3c1c6923dc3908adeab57e6a2fe15c:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java
	@Test
	public void testHasErrors() {
		when(userService.isExist(registration.getEmail())).thenReturn(false);
		BindingResult errors = new BeanPropertyBindingResult(registration, "registration");
		registrationValidation.validate(registration, errors);
        assertFalse(errors.hasErrors());
	}

<<<<<<< HEAD:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java
}
=======
}
>>>>>>> f98bdf9d8c3c1c6923dc3908adeab57e6a2fe15c:SSEL/src/test/java/com/softserve/tests/RegistrationValidationPositiveTest.java
