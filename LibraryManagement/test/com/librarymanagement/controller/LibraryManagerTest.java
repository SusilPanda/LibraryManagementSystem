package com.librarymanagement.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.librarymanagement.bean.Book;
import com.librarymanagement.bean.BookingDetails;
import com.librarymanagement.bean.Reader;
import com.librarymanagement.common.LibraryManagementException;
import com.librarymanagement.dao.DataBaseConnection;

class LibraryManagerTest {

	private static Book bookJava = null;
	private static Book bookSql = null;
	private static Reader readerJohn = null;
	private static Reader readerSam = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DataBaseConnection.setUp();
		DataBaseConnection.insertDefaultDataInDb(1, "Monthly", 30);
		DataBaseConnection.insertDefaultDataInDb(2, "Annually", 365);
		
		setUp();
		createInitialData();
	}
	
	public static void setUp() throws Exception {
 		bookJava = new Book(1, "Core java", "Programming", "schand", 5, 0, 5);
		bookSql = new Book(2, "MySql", "Database", "Headfirst", 10, 0, 10);
 		readerJohn = new Reader(101, "john", "user", "user", "john@gmail.com", 1);
 		readerSam = new Reader(102, "Sam", "sam", "sam", "sam@gmail.com", 2);
 		
 	}
 	
   public static void createInitialData() throws LibraryManagementException {
 		BookManager bookManager = new BookManager();
 		bookManager.createBook(bookJava);
 		bookManager.createBook(bookSql);
 		
 		ReaderManager readerManager = new ReaderManager();
 		readerManager.createReader(readerJohn);
 		readerManager.createReader(readerSam);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	public void clearInitialData() throws LibraryManagementException {		
		BookManager bookManager = new BookManager();
		bookManager.deleteBook(bookJava.getBookId());
		bookManager.deleteBook(bookSql.getBookId());
		
		ReaderManager readerManager = new ReaderManager();
 		readerManager.deleteReader(readerJohn.getReaderId());
 		readerManager.deleteReader(readerSam.getReaderId());
	}

	@Test
	void testGetAllAvailableBooks() {
		
	}

	@Test
	void testGetAllBorrowedBooks() throws LibraryManagementException {
       LibraryManager libManager = new LibraryManager();
		
		//libManager.borrowABook(readerJohn.getReaderId(), bookJava.getBookId());
		libManager.borrowABook(readerJohn.getReaderId(), bookSql.getBookId());
		
		List<BookingDetails> listOfBookings = libManager.getAllBorrowedBooks();
		assertEquals(bookSql.getBookId(), listOfBookings.get(1).getBookId());
		assertEquals(readerJohn.getReaderId(), listOfBookings.get(1).getReaderId()); 
		
	}

	@Test
	void testBorrowABook() throws LibraryManagementException {
		//createInitialData();
		LibraryManager libManager = new LibraryManager();
		
		libManager.borrowABook(readerJohn.getReaderId(), bookJava.getBookId());
		
		List<BookingDetails> listOfBookings = libManager.getAllBorrowedBooks();
		
		assertEquals(bookJava.getBookId(), listOfBookings.get(0).getBookId());
		assertEquals(readerJohn.getReaderId(), listOfBookings.get(0).getReaderId());
		assertNull(listOfBookings.get(0).getReturnDate());
		
		// verify number of books reduced
		BookManager bookManager = new BookManager();
		Book result = bookManager.getBook(bookJava.getBookId());
		assertEquals(4, result.getNumberOfAvailableBooks());
		
	}

	@Test
	void testReturnABook() throws LibraryManagementException {
		//createInitialData();
        LibraryManager libManager = new LibraryManager();
		
		libManager.borrowABook(readerJohn.getReaderId(), bookJava.getBookId());
		List<BookingDetails> listOfBookings = libManager.getAllBorrowedBooks();
		
		int bookingId = listOfBookings.get(0).getBookingId();
		libManager.returnABook(bookingId);
		BookingDetails result = libManager.getBookingDetails(bookingId);
		
		assertEquals(bookingId, result.getBookId());
		assertNotNull(result.getReturnDate());
		
	}



}
