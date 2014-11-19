package com.softserve.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.softserve.controller.LogController;
import com.softserve.entity.Log;
import com.softserve.service.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:root-context.xml")
public class LogControllerTest {
	private Log log = new Log();
	private List<Log> logList = new ArrayList<Log>();
	private MockMvc mockMvc;
	// private LogController logController;
	@Autowired
	private LogService logService;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		logService = mock(LogService.class);
		// logController = new LogController();
		// logController.setLogService(logService);
		log.setId(25);
		log.setLevel("FATAL");
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void testLogDetailsController() {
		when(logService.getLogById(2)).thenReturn(log);
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/logDetails?LogId=2"))
					.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}