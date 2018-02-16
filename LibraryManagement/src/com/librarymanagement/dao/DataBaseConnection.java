package com.librarymanagement.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {

	private static Connection dbConnection = null;
	
	public static void setUp() {

		PreparedStatement createPreparedStatement = null;
		// check if table exists and if it doesn't then create one
		String createTableReaderQuery = "CREATE TABLE IF NOT EXISTS READER(readerid int primary key, name varchar(255),"
				+ "username varchar(255), password varchar(255), emailid varchar(30))";
		String createTableBookQuery = "CREATE TABLE IF NOT EXISTS BOOK(bookid long primary key, name varchar(255), title varchar(255),"
				+ "author varchar(255),  numberofbooks int, numberofavailablebooks int, numberofborrowedbooks int)";
		String createTableBorrowQuery = "CREATE TABLE IF NOT EXISTS BORROW(bookid long foreign key REFERENCES BOOK(bookid), readerid int foreign key REFERENCES READER(readerid),"
				+ "borrowdate varchar(255), returndate varchar(255))";
		String createTableSubscriptionQuery = "CREATE TABLE IF NOT EXISTS SUBSCRIPTION(subscriptionid int primary key, subscriptionname varchar(25), validity int)";

		try {
			dbConnection.setAutoCommit(false);

			PreparedStatement createTableReaderPreparedStatement = dbConnection.prepareStatement(createTableReaderQuery);
			PreparedStatement createTableBookPreparedStatement = dbConnection.prepareStatement(createTableBookQuery);
			//PreparedStatement createTableBorrowPreparedStatement = dbConnection.prepareStatement(createTableBorrowQuery);
			PreparedStatement createTableSubscriptionPreparedStatement = dbConnection.prepareStatement(createTableSubscriptionQuery);
			
			/*createPreparedStatement.addBatch(createTableReaderQuery);
			createPreparedStatement.addBatch(createTableBookQuery);
			createPreparedStatement.addBatch(createTableBorrowQuery);	*/		
			
			//createPreparedStatement.executeBatch();  //executeUpdate();
			createTableReaderPreparedStatement.executeUpdate();
			createTableBookPreparedStatement.executeUpdate();
			//createTableBorrowPreparedStatement.executeUpdate();
			createTableSubscriptionPreparedStatement.executeUpdate();
			
			createTableReaderPreparedStatement.close();
			createTableBookPreparedStatement.close();
			//createTableBorrowPreparedStatement.close();
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
	String insertSubscriptionQuery = "INSERT INTO SUBSCRIPTION" + "(subscriptionid, subscriptionname, validity) values" + "(?, ?, ?)";
	try {
		dbConnection.setAutoCommit(false);
		PreparedStatement insertSubscriptionPreparedStatement = dbConnection.prepareStatement(insertSubscriptionQuery);
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
			InputStream input = new FileInputStream("resources/application.properties");
			// load a properties file
			properties.load(input);
			
			Class.forName(properties.getProperty("database"));
		
			dbConnection = DriverManager.getConnection(properties.getProperty("dbConnection"),
					properties.getProperty("dbUser"), properties.getProperty("dbPassword"));
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
