package com.softserve.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.softserve.service.AdministratorService;

@RunWith(MockitoJUnitRunner.class)
public class AdministratorServiceTest {
	
	AdministratorService administratorService;
	
	@Before
	public void setUp() {
		administratorService = mock(AdministratorService.class);
	}
	
	@Test
	public void testAddCategory() {
		when(administratorService.addCategory(anyString())).thenReturn(false);
		assertFalse(administratorService.addCategory("Name"));
	}

}
