package com.library.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseConnection {

	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_CONNECTION ="jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";

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

		try {
			dbConnection.setAutoCommit(false);

			PreparedStatement createTableReaderPreparedStatement = dbConnection.prepareStatement(createTableReaderQuery);
			PreparedStatement createTableBookPreparedStatement = dbConnection.prepareStatement(createTableBookQuery);
			//PreparedStatement createTableBorrowPreparedStatement = dbConnection.prepareStatement(createTableBorrowQuery);
			
			/*createPreparedStatement.addBatch(createTableReaderQuery);
			createPreparedStatement.addBatch(createTableBookQuery);
			createPreparedStatement.addBatch(createTableBorrowQuery);	*/		
			
			//createPreparedStatement.executeBatch();  //executeUpdate();
			createTableReaderPreparedStatement.executeUpdate();
			createTableBookPreparedStatement.executeUpdate();
			//createTableBorrowPreparedStatement.executeUpdate();
			
			createTableReaderPreparedStatement.close();
			createTableBookPreparedStatement.close();
			//createTableBorrowPreparedStatement.close();
			dbConnection.commit();
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private static Connection createConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
