package com.softserve.tests;

import static org.junit.Assert.*;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Suite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import junitparams.JUnitParamsRunner;
import junitparams.converters.ConvertParam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.softserve.dao.LogDao;
import com.softserve.entity.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;


@RunWith(Suite.class)
@SuiteClasses({ SpringJUnit4ClassRunner.class, Parameterized.class})

@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/fortest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })

public class GetRangeOfLogsTest {
	private Date startDate; 
	private Date endDate; 
	private int logsPerPage; 
	private int pageNumb; 
	private String orderBy;
	private int numbOfLogsInResult;
	private String expectedLevel;

	
	@Autowired
	private LogDao logDao;

	public GetRangeOfLogsTest() {}
		public GetRangeOfLogsTest(Date startDate, Date endDate, int logsPerPage,
			int pageNumb, String orderBy, int numbOfLogsInResult,
			String expectedLevel) {
		
		this.startDate = startDate;
		this.endDate = endDate;
		this.logsPerPage = logsPerPage;
		this.pageNumb = pageNumb;
		this.orderBy = orderBy;
		this.numbOfLogsInResult = numbOfLogsInResult;
		this.expectedLevel = expectedLevel;
		
	}
	
	 @Parameters
	    public static Collection<Object[]> data() {
	    	Object[][] data = new Object[][] {
	    			{
	    				(new GregorianCalendar(2013, 0, 1)).getTime(),
	    				(new GregorianCalendar(2015, 0, 1)).getTime(),
	    				10, 0, "eventDate ASC",
	    				10, "ERROR"
	    			},
	    			{
	    				(new GregorianCalendar(2013, 0, 1)).getTime(),
	    				(new GregorianCalendar(2015, 0, 1)).getTime(),
	    				3, 3, "eventDate ASC",
	    				1, "ERROR2"
	    			},
	    			{
	    				(new GregorianCalendar(2013, 0, 1)).getTime(),
	    				(new GregorianCalendar(2015, 0, 1)).getTime(),
	    				10, 0, "eventDate DESC",
	    				10, "ERROR2"
	    			}
	    	};
	    	return Arrays.asList(data);
	    }
	    
/*	    @Test
		@DatabaseSetup("classpath:logsData.xml")
		@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:logsData.xml")
	    @Parameters({"10,0,eventDate ASC,10,ERROR"})
		public void testGetRangeOfLogs(int logsPerPage,
				int pageNumb, String orderBy, int numbOfLogsInResult,
				String expectedLevel) {
	    	Date startDate =	(new GregorianCalendar(2013, 0, 1)).getTime();
	    	Date endDate = (new GregorianCalendar(2015, 0, 1)).getTime();
	     
	    	ArrayList <Log> logList = (ArrayList <Log>) logDao.getRangeOfLogs(startDate, endDate, logsPerPage, pageNumb, orderBy);
	    	assertTrue((logList.size() == numbOfLogsInResult) && (logList.get(0).getLevel().equals(expectedLevel)));
	    
	    
	    }*/
	    
	
	    @Test
		@DatabaseSetup("classpath:logsData.xml")
		@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:logsData.xml")
		public void testGetRangeOfLogs() {
	    	ArrayList <Log> logList = (ArrayList <Log>) logDao.getRangeOfLogs(startDate, endDate, logsPerPage, pageNumb, orderBy);
	    	assertTrue((logList.size() == numbOfLogsInResult) && (logList.get(0).getLevel().equals(expectedLevel)));
	    	
	    	
	    }
	
	

}
