package com.softserve.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.softserve.dao.LogDao;
import com.softserve.dao.impl.LogDaoImpl;
import com.softserve.entity.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class LogDaoTest {
	
	
	@Configuration
    static class ContextConfiguration {

        // this bean will be injected into the OrderServiceTest class
        @Bean
        public LogDao LogDao() {
        	LogDao logDao = new LogDaoImpl();
            return logDao;
        }
    }
	

	@Autowired
	private LogDao logDao;

	
	@Test
	@DatabaseSetup("logsData.xml")
	@DatabaseTearDown("logsData.xml")
	public void testGetLogById() {
		Log log = logDao.getLogById(1);
		assertTrue((log.getId() == 1) && (log.getLevel().equals("ERROR")));
	}

		
	@Test
	@DatabaseSetup("logsData.xml")
	@DatabaseTearDown("logsData.xml")
	public void testCountLogsInQuery() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR) - 5));
		Date startDate = calendar.getTime();
		calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR) + 10));
		Date endDate = calendar.getTime();
			long allLogsNumb = logDao.countLogsInQuery(startDate, endDate);
			
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DATE, 2);
		startDate = calendar.getTime();
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, 3);
		calendar.set(Calendar.DATE, 1);
		endDate = calendar.getTime();
			long partOfLogsNumb = logDao.countLogsInQuery(startDate, endDate);
			
		assertTrue((allLogsNumb == 10) && (partOfLogsNumb == 5));
			
	}
	
	@Test
	@DatabaseSetup("logsData.xml")
	@DatabaseTearDown("logsData.xml")
	public void testdeleteLogsDueDate() {
		GregorianCalendar calendar = new GregorianCalendar(2014, 6, 1);
		logDao.deleteLogsDueDate(calendar.getTime());
		int firstResult = countAllLogsInDatabase();
		calendar = new GregorianCalendar(2014, 10, 2);
		logDao.deleteLogsDueDate(calendar.getTime());
		int secondResult = countAllLogsInDatabase();
		
		assertTrue((firstResult == 4) && (secondResult == 1));
	}
	
	@Test
	@DatabaseSetup("logsData.xml")
	@DatabaseTearDown("logsData.xml")
	public void testGetRangeOfLogs() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR) - 5));
		Date startDate = calendar.getTime();
		calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR) + 10));
		Date endDate = calendar.getTime();
		int logsPerPage = 10;
		int pageNumb = 1;
		String orderBy = "eventDate ASC";
		
		ArrayList <Log> logList1 = (ArrayList <Log>)logDao.getRangeOfLogs(startDate, endDate, logsPerPage, pageNumb, orderBy);
		assertTrue(logList1.get(0).getId() == 1);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	private int countAllLogsInDatabase() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR) - 5));
		Date startDate = calendar.getTime();
		calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR) + 10));
		Date endDate = calendar.getTime();
			long allLogsNumb = logDao.countLogsInQuery(startDate, endDate);		
		return (int) allLogsNumb;
	}
	
	
	
	
	
}
