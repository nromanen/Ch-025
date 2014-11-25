package com.softserve.service.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
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
	private LogServiceImpl logServiceImpl = new LogServiceImpl();
	private Log log = new Log();
	private List<Log> logList = new ArrayList<Log>();
	private GregorianCalendar startCalendar;
	private GregorianCalendar endCalendar;
	GregorianCalendar endCalendarForLogDao;

	@Before
	public void setUp() {
		logDao = mock(LogDao.class);
		logServiceImpl.setLogDao(logDao);
		log.setId(25);
		log.setLevel("FATAL");
		Log log2 = new Log();
		log2.setId(26);
		log2.setLogger("FATAL");
		logList.add(log);
		logList.add(log2);
		startCalendar = new GregorianCalendar(2014, 10, 10);
		endCalendar = new GregorianCalendar(2014, 10, 11);
		endCalendarForLogDao = new GregorianCalendar(
				endCalendar.get(Calendar.YEAR),
				endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DATE));
		// making "endOfDay"
		endCalendarForLogDao.set(Calendar.HOUR_OF_DAY, 23);
		endCalendarForLogDao.set(Calendar.MINUTE, 59);
		endCalendarForLogDao.set(Calendar.SECOND, 59);
	}

	@Test
	public void testDeleteLogsDueDate() {
		Date date = startCalendar.getTime();
		logServiceImpl.deleteLogsDueDate(startCalendar);
		verify((logDao), times(1)).deleteLogsDueDate(date);
		verifyNoMoreInteractions(logDao);

	}

	@Test
	public void testGetLogById() {
		when(logDao.getLogById(1)).thenReturn(log);
		Log testLog = logServiceImpl.getLogById(1);
		verify((logDao), times(1)).getLogById(1);
		verifyNoMoreInteractions(logDao);
		assertSame(testLog.getId(), 25);
	}

	@Test
	public void testGetRangeOfLogs() {
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendarForLogDao.getTime();
		String orderBy = "eventDate DESC";
		when(logDao.getRangeOfLogs(startDate, endDate, 25, 1, orderBy))
				.thenReturn(logList);

		List<Log> testLogList = logServiceImpl.getRangeOfLogs(startCalendar,
				endCalendar, 25, 1, orderBy);
		verify((logDao), times(1)).getRangeOfLogs(startDate, endDate, 25, 1,
				orderBy);
		verifyNoMoreInteractions(logDao);
		assertTrue((testLogList.size() == 2)
				& (testLogList.get(0).getId() == 25));
	}

	@Test
	public void testCountLogsInQuery() {
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendarForLogDao.getTime();
		when(logDao.countLogsInQuery(startDate, endDate)).thenReturn(100L);
		Long logsInQuery = logServiceImpl.countLogsInQuery(startCalendar,
				endCalendar);
		verify((logDao), times(1)).countLogsInQuery(startDate, endDate);
		verifyNoMoreInteractions(logDao);
		assertSame(logsInQuery, 100L);
	}

	@Test
	public void testGetNumberOfPages() {
		int firstResult;
		int secongResult;
		int thirdResult;
		firstResult = logServiceImpl.getNumberOfPages(100L, 25);
		secongResult = logServiceImpl.getNumberOfPages(172L, 10);
		thirdResult = logServiceImpl.getNumberOfPages(172L, 0);
		assertTrue((firstResult == 4) && (secongResult == 18)
				&& (thirdResult == 1));
	}

	@Test
	public void testParseDate() {
		GregorianCalendar calendarOne = new GregorianCalendar(2014, 10, 15);
		GregorianCalendar calendarTwo = new GregorianCalendar(2014, 5, 22);
		GregorianCalendar resultOne = logServiceImpl.parseDate("15-11-2014");
		GregorianCalendar resultTwo = logServiceImpl.parseDate("22-06-2014");
		assertTrue((resultOne.equals(calendarOne))
				&& (resultTwo).equals(calendarTwo));
	}

	@Test
	public void testCreateOrderByPart() {
		String resultOne = logServiceImpl.createOrderByPart("level-asc");
		String resultTwo = logServiceImpl.createOrderByPart("exception-desc");
		String resultThree = logServiceImpl.createOrderByPart("dlafldksfja");
		assertTrue((resultOne.equals("level ASC"))
				&& (resultTwo).equals("exception DESC")
				&& (resultThree.equals("eventDate DESC")));
	}

}
