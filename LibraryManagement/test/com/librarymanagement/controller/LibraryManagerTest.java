package com.librarymanagement.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.librarymanagement.bean.Book;
import com.librarymanagement.bean.BookingDetails;
import com.librarymanagement.bean.Reader;
import com.librarymanagement.common.LibraryManagementException;
import com.librarymanagement.dao.DataBaseConnection;

public class LibraryManagerTest {

	private static Book bookJava = null;
	private static Book bookSql = null;
	private static Reader readerJohn = null;
	private static Reader readerSam = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
		readerSam = new Reader(102, "Sam", "sam", "sam", "sam@gmail.com", 1);

	}

	public static void createInitialData() throws LibraryManagementException {
		BookManager bookManager = new BookManager();
		bookManager.createBook(bookJava);
		bookManager.createBook(bookSql);

		ReaderManager readerManager = new ReaderManager();
		readerManager.createReader(readerJohn);
		readerManager.createReader(readerSam);
	}

	@After
	public void tearDown() throws Exception {
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
	public void testGetAllAvailableBooks() {

	}

	@Test
	public void testGetAllBorrowedBooks() throws LibraryManagementException {
		LibraryManager libManager = new LibraryManager();

		libManager.borrowABook(readerJohn.getReaderId(), bookSql.getBookId());

		List<BookingDetails> listOfBookings = libManager.getAllBorrowedBooks();
		List<BookingDetails> resultList = listOfBookings.stream().filter(
				param -> param.getReaderId() == readerJohn.getReaderId() && param.getBookId() == bookSql.getBookId())
				.collect(Collectors.toList());

		assertEquals(bookSql.getBookId(), resultList.get(0).getBookId());
		assertEquals(readerJohn.getReaderId(), resultList.get(0).getReaderId());

	}

	@Test
	public void testBorrowABook() throws LibraryManagementException {
		LibraryManager libManager = new LibraryManager();

		libManager.borrowABook(readerJohn.getReaderId(), bookJava.getBookId());

		List<BookingDetails> listOfBookings = libManager.getAllBorrowedBooks();
		List<BookingDetails> resultList = listOfBookings.stream().filter(
				param -> param.getReaderId() == readerJohn.getReaderId() && param.getBookId() == bookJava.getBookId())
				.collect(Collectors.toList());

		assertEquals(bookJava.getBookId(), resultList.get(0).getBookId());
		assertEquals(readerJohn.getReaderId(), resultList.get(0).getReaderId());
		assertNull(resultList.get(0).getReturnDate());

		// verify number of books reduced
		BookManager bookManager = new BookManager();
		Book result = bookManager.getBook(bookJava.getBookId());
		assertEquals(4, result.getNumberOfAvailableBooks());

	}

	@Test
	public void testReturnABook() throws LibraryManagementException {
		LibraryManager libManager = new LibraryManager();

		libManager.borrowABook(readerJohn.getReaderId(), bookJava.getBookId());
		List<BookingDetails> listOfBookings = libManager.getAllBorrowedBooks();
		List<BookingDetails> resultList = listOfBookings.stream().filter(
				param -> param.getReaderId() == readerJohn.getReaderId() && param.getBookId() == bookJava.getBookId())
				.collect(Collectors.toList());

		int bookingId = resultList.get(0).getBookingId();
		libManager.returnABook(bookingId);
		BookingDetails result = libManager.getBookingDetails(bookingId);

		assertNotNull(result.getReturnDate());

	}

	@Test(expected = LibraryManagementException.class)
	public void testBorrowABookFailedAsMaximumLimitReached() throws LibraryManagementException {
		createMaximumBookings(readerSam.getReaderId());
		LibraryManager libManager = new LibraryManager();

		libManager.borrowABook(readerSam.getReaderId(), bookJava.getBookId());
	}

	private void createMaximumBookings(int readerId) throws LibraryManagementException {
		List<Book> bookList = new ArrayList<>();
		int n = 15;
		BookManager bookManager = new BookManager();
		for (int i = 10; i < n; i++) {
			bookList.add(new Book(i, "Core java" + i, "Programming", "schand", i, 0, i));
		}
		LibraryManager libManager = new LibraryManager();

		for (Book book : bookList) {
			bookManager.createBook(book);

			libManager.borrowABook(readerId, book.getBookId());
		}
	}
}
