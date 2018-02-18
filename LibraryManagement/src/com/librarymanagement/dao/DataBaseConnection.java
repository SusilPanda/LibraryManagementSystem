package com.librarymanagement.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.librarymanagement.common.CommonConstants;

public class DataBaseConnection {

	private static Connection dbConnection = null;
	
	public static void setUp() {

		PreparedStatement createPreparedStatement = null;
		Connection dbConnection = DataBaseConnection.getDbConnection();
		
		try {
			dbConnection.setAutoCommit(false);

			PreparedStatement createTableReaderPreparedStatement = dbConnection.prepareStatement(CommonConstants.CREATE_TABLE_READER_QUERY);
			PreparedStatement createTableBookPreparedStatement = dbConnection.prepareStatement(CommonConstants.CREATE_TABLE_BOOK_QUERY);
			PreparedStatement createTableBookingDetailsPreparedStatement = dbConnection.prepareStatement(CommonConstants.CREATE_TABLE_BOOKING_DETAILS_QUERY);
			PreparedStatement createTableSubscriptionPreparedStatement = dbConnection.prepareStatement(CommonConstants.CREATE_TABLE_SUBSCRIPTION_QUERY);
			
			/*createPreparedStatement.addBatch(createTableReaderQuery);
			createPreparedStatement.addBatch(createTableBookQuery);
			createPreparedStatement.addBatch(createTableBorrowQuery);	*/		
			
			//createPreparedStatement.executeBatch();  //executeUpdate();
			createTableReaderPreparedStatement.executeUpdate();
			createTableBookPreparedStatement.executeUpdate();
			createTableBookingDetailsPreparedStatement.executeUpdate();
			createTableSubscriptionPreparedStatement.executeUpdate();
			
			createTableReaderPreparedStatement.close();
			createTableBookPreparedStatement.close();
			createTableBookingDetailsPreparedStatement.close();
			createTableSubscriptionPreparedStatement.close();
			dbConnection.commit();
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void insertDefaultDataInDb(int id, String name, int validity)
	{
	try {
		dbConnection.setAutoCommit(false);
		PreparedStatement insertSubscriptionPreparedStatement = dbConnection.prepareStatement(CommonConstants.INSERT_SUBSCRIPTION_QUERY);
		insertSubscriptionPreparedStatement.setInt(1, id);
		insertSubscriptionPreparedStatement.setString(2, name);
		insertSubscriptionPreparedStatement.setInt(3, validity);
		
		insertSubscriptionPreparedStatement.executeUpdate();
		insertSubscriptionPreparedStatement.close();
		dbConnection.commit();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	private static Connection createConnection() {
		Connection dbConnection = null;
		Properties properties = new Properties();
		try {
			InputStream input = new FileInputStream(CommonConstants.PROPERTIES_FILE_URL);
			// load a properties file
			properties.load(input);
			
			Class.forName(properties.getProperty(CommonConstants.DATABASE_KEY));
		
			dbConnection = DriverManager.getConnection(properties.getProperty(CommonConstants.CONNECTION_KEY),
					properties.getProperty(CommonConstants.USER_NAME_KEY), properties.getProperty(CommonConstants.USER_PASSWORD_KEY));
			return dbConnection;
		
		} catch (SQLException | IOException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return dbConnection;
	}

	public static Connection getDbConnection() {
		
		if(dbConnection == null ) {
			dbConnection = createConnection();
		}
		return dbConnection;
	}
	
	public static void closeAllConnection() {
		try {
			dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
