package com.softserve.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

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
	
	public void getLogById() {
		Log log = logDao.getLogById(1);
		assertNotNull(log);

	}

	
	
	
	
	
}
