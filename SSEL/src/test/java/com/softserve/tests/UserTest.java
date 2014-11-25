/**
 * 
 */
package com.softserve.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.softserve.entity.User;
import com.softserve.service.UserService;
import com.softserve.service.impl.UserServiceImpl;

import config.DBUnitConfig;

/**
 * @author hash
 *
 */
public class UserTest extends DBUnitConfig{
	 
    public UserTest(String name) {
		super(name);
	}

	private UserService service = new UserServiceImpl();
    //private EntityManager em = Persistence.createEntityManagerFactory("DBUnitEx").createEntityManager();
 
    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(
                     Thread.currentThread().getContextClassLoader()
                     .getResourceAsStream("com/softserve/entity/user/user-data.xml"));
 
        tester.setDataSet(beforeData);
        tester.onSetup();
    }
 
    @Test
    public void testGetAll() throws Exception {
        List<User> users = service.getAllUsers();
 
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                                Thread.currentThread().getContextClassLoader()
                                .getResourceAsStream("com/softserve/entity/user/user-data.xml"));
 
        IDataSet actualData = tester.getConnection().createDataSet();
        Assertion.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedData.getTable("user").getRowCount(),users.size());
    }
 
    @Test
    
    public void testSave() throws Exception {
    	byte[] byteArr = new byte[1];
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date1 = df.parse("2015-11-14 00:24:28");
    	Date date2 = df.parse("2014-11-14 00:24:28");
        User user = new User();
        user.setBlocked(false);
        user.setEmail("hash.cv1@gmail.com");
        user.setExpired(date1);
        user.setFirstName("Student1");
        user.setLastName("Studentovich1");
        user.setPassword("$10$U76KpoHpYRrfb17R/eNi5OM/ncgHDw8i.IBhO9BmSFweu1aNsN2UG");
        user.setVerificationKey(" ");
        user.setImage(byteArr);
        user.setRegistration(date2);

        

 
        User us = service.addUser(user);
 
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                                Thread.currentThread().getContextClassLoader()
                                .getResourceAsStream("com/softserve/entity/user/user-data-save.xml"));
 
        IDataSet actualData = tester.getConnection().createDataSet();
 
        String[] ignore = {"id","role","social"};
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "user", ignore);
    }
 
 
 
}
