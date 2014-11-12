package com.softserve.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.softserve.dao.LogDao;
import com.softserve.entity.Log;
import com.softserve.service.impl.LogServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class LogServiceTest {
	
	private LogDao logDao;
	private Log log = new Log();
	private List<Log> logList = new ArrayList<Log>();
	
	@Before
	public void setUp() {
		logDao = mock(LogDao.class);
		log.setId(25);
		log.setLevel("FATAL");
		Log log2 = new Log();
		log2.setId(26);
		log2.setLogger("FATAL");
		logList.add(log);
		logList.add(log2);
	}

	@Test
	public void testGetLogById() {
		when(logDao.getLogById(1)).thenReturn(log);
		LogServiceImpl logServiceImpl = new LogServiceImpl();
		logServiceImpl.setLogDao(logDao);
		Log testLog = logServiceImpl.getLogById(1);
		assertSame(testLog.getId(), 25);
	}
	
	@Test
	public void testDeleteLogsDueDate() {
		
		GregorianCalendar calendar = new GregorianCalendar(2014, 10, 10);
		Date date = calendar.getTime();
		Date date1 = new Date();
//		doThrow(new RuntimeException()).when(logDao).deleteLogsDueDate(date);
		LogServiceImpl logServiceImpl = new LogServiceImpl();
		logServiceImpl.setLogDao(logDao);
		logServiceImpl.deleteLogsDueDate(calendar);
		verify((logDao), times(1)).deleteLogsDueDate(date1);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
