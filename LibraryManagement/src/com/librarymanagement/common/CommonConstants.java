package com.librarymanagement.common;

public class CommonConstants {
	
	// table creation query string
	public static final String CREATE_TABLE_READER_QUERY = "CREATE TABLE IF NOT EXISTS READER(readerid int primary key, name varchar(255),"
			+ "username varchar(35), password varchar(35), emailid varchar(30), subscriptionid int)";
	
	public static final String CREATE_TABLE_BOOK_QUERY = "CREATE TABLE IF NOT EXISTS BOOK(bookid long primary key, name varchar(255), title varchar(255),"
			+ "author varchar(255),  numberofbooks int, numberofavailablebooks int, numberofborrowedbooks int)";
	
	public static final String CREATE_TABLE_SUBSCRIPTION_QUERY = "CREATE TABLE IF NOT EXISTS SUBSCRIPTION(subscriptionid int primary key,"
			+ "subscriptionname varchar(25), validity int)";
	
	public static final String CREATE_TABLE_BOOKING_DETAILS_QUERY =  "CREATE TABLE IF NOT EXISTS BOOKINGDETAILS(bookingid int primary key auto_increment,"
			+ "borrowdate date, returndate date, bookid long, readerid int, foreign key(bookid) REFERENCES BOOK(bookid),"
			+ "foreign key(readerid) REFERENCES READER(readerid))";
	
	
	// insert, select , update, delete query string
	public static final String INSERT_SUBSCRIPTION_QUERY = "INSERT INTO SUBSCRIPTION(subscriptionid, subscriptionname, validity) values(?, ?, ?)";
	public static final String INSERT_BOOKING_DETAILS_QUERY = "INSERT INTO BOOKINGDETAILS(bookid, readerid, borrowdate) values(?, ?, ?)";
	public static final String SELECT_BOOKING_DETAILS_QUERY = "SELECT * from BOOKINGDETAILS where bookingid = ?";
	public static final String UPDATE_BOOKING_DETAILS_QUERY = "UPDATE BOOKINGDETAILS SET borrowdate = ?, returndate = ?, bookid = ?, readerid = ? where bookingid = ?";
	public static final String SELECT_ALL_BOOKING_DETALS_QUERY = "SELECT * from BOOKINGDETAILS";
	public static final String SELECT_COUNT_BOOKING_DETAILS_QUERY = "SELECT count(*) from BOOKINGDETAILS where readerId = ?";
	
	public static final String SELECT_READER_QUERY = "SELECT readerid, name, username, password, emailid, subscriptionid from READER where readerid = ?";
	public static final String SELECT_ALL_READER_QUERY = "SELECT * from READER";
	public static final String INSERT_READER_QUERY = "INSERT INTO READER(readerid, name, username, password, emailid, subscriptionid) values(?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_READER_QUERY = "UPDATE READER SET name = ?, username = ?, password = ?, emailid = ?, subscriptionid = ? where readerid = ?";
	public static final String DELETE_READER_QUERY = "DELETE FROM READER where readerid = ?";
	
	public static final String SELECT_BOOK_QUERY = "select bookid, name, title, author, numberofbooks, numberofborrowedbooks, numberofavailablebooks from Book where bookid = ?";
	public static final String INSERT_BOOK_QUERY = "INSERT INTO BOOK(bookid, name, title, author, numberofbooks, numberofborrowedbooks, numberofavailablebooks) values(?, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_BOOK_QUERY = "UPDATE BOOK SET name = ?, title = ?, author = ?, numberofbooks = ?, numberofborrowedbooks = ?, numberofavailablebooks = ? where bookid = ?";
    public static final String DELETE_BOOK_QUERY = "DELETE FROM BOOK where bookid = ?";
    
	// application common constants
	public static final String PROPERTIES_FILE_URL  = "resources/application.properties";
	
	// database connection constants
	public static final String DATABASE_KEY = "database";
	public static final String CONNECTION_KEY = "dbConnection";
	public static final String USER_NAME_KEY = "dbUser";
	public static final String USER_PASSWORD_KEY = "dbPassword";	
}
