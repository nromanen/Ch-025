/**
 * 
 */
package config;

import java.io.IOException;
import java.util.Properties;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

/**
 * @author hash
 *
 */
public class DBUnitConfig extends DBTestCase {
    protected IDatabaseTester tester;
    private Properties prop;
    protected IDataSet beforeData;
 
    @Before
    public void setUp() throws Exception {
        tester = new JdbcDatabaseTester(prop.getProperty("jdbc.driverClassName"),
                                        prop.getProperty("jdbc.databaseurl"),
                                        prop.getProperty("jdbc.username"),
                                        prop.getProperty("jdbc.password"));
    }
 
    public DBUnitConfig(String name) {
        super(name);
        prop = new Properties();
        try {
            prop.load(Thread.currentThread()
                      .getContextClassLoader().getResourceAsStream("jdbc-test.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, prop.getProperty("jdbc.driverClassName"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, prop.getProperty("jdbc.databaseurl"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, prop.getProperty("jdbc.username"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, prop.getProperty("jdbc.password"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "");
    }
 
    @Override
    protected IDataSet getDataSet() throws Exception {
        return beforeData;
    }
 
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }
 
}
