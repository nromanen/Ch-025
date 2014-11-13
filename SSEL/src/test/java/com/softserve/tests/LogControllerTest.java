package com.softserve.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.softserve.entity.Log;
import com.softserve.service.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(MockitoJUnitRunner.class)
public class LogControllerTest {
	private LogService logService;
	private Log log = new Log();
	private List<Log> logList = new ArrayList<Log>();
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext; 
	
	
	@Before
	public void setUp() {
		logService = mock(LogService.class);
		log.setId(25);
		log.setLevel("FATAL");
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
	}

	
	@Test
	public void testLogDetailsController() {
		when(logService.getLogById(2)).thenReturn(log);
//		mockMvc.perform(get("/logDetails?LogId=2")).andExpect(status().isOk());
		
		
		
	}
	
	
	
}
