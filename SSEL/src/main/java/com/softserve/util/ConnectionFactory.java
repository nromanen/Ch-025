package com.softserve.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.dao.impl.TopicDaoImpl;

public class ConnectionFactory {
	private static interface Singleton {
		final ConnectionFactory INSTANCE = new ConnectionFactory();
	}

	private static final Logger LOG = LoggerFactory
			.getLogger(TopicDaoImpl.class);
	
	private final DataSource dataSource;

	private ConnectionFactory() {
		Properties properties = new Properties();
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream("jdbc.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			LOG.info(e.toString());
		}

		String username = properties.getProperty("jdbc.username");
		String password = properties.getProperty("jdbc.password");
		String url = properties.getProperty("jdbc.databaseurl");
		String driver = properties.getProperty("jdbc.driverClassName");

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			LOG.info(e.toString());
		}

		GenericObjectPool pool = new GenericObjectPool();
		DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
				url, username, password);
		new PoolableConnectionFactory(connectionFactory, pool, null,
				"SELECT 1", 3, false, false,
				Connection.TRANSACTION_READ_COMMITTED);

		this.dataSource = new PoolingDataSource(pool);
	}

	public static Connection getDatabaseConnection() throws SQLException {
		return Singleton.INSTANCE.dataSource.getConnection();
	}
}