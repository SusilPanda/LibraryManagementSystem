/**
 * 
 */
package com.librarymanagement.controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.librarymanagement.bean.Book;
import com.librarymanagement.common.LibraryManagementException;
import com.librarymanagement.dao.DataBaseConnection;

/**
 * @author esusich
 *
 */
public class BookManagerTest {

	private static Book bookJava = null;
	private static Book bookSql = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataBaseConnection.setUp();
		DataBaseConnection.insertDefaultDataInDb(1, "Monthly", 30);
		DataBaseConnection.insertDefaultDataInDb(2, "Annually", 365);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bookJava = new Book(1, "Core java", "Programming", "schand", 5, 0, 5);
		bookSql = new Book(2, "MySql", "Database", "Headfirst", 10, 0, 10);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		BookManager bookManager = new BookManager();
		if (bookManager.getBook(bookJava.getBookId()) != null) {
			bookManager.deleteBook(bookJava.getBookId());
		}
	}

	/**
	 * Test method for
	 * {@link com.librarymanagement.controller.BookManager#getBook(long)}.
	 * 
	 * @throws LibraryManagementException
	 */
	@Test
	public void testGetBook() throws LibraryManagementException {
		BookManager bookManager = new BookManager();
		bookManager.createBook(bookJava);

		Book response = bookManager.getBook(bookJava.getBookId());
		assertEquals(response.getBookId(), bookJava.getBookId());
	}

	/**
	 * Test method for
	 * {@link com.librarymanagement.controller.BookManager#createBook(com.librarymanagement.bean.Book)}.
	 * 
	 * @throws LibraryManagementException
	 */
	@Test
	public void testCreateBook() throws LibraryManagementException {
		BookManager bookManager = new BookManager();
		String result = bookManager.createBook(bookJava);

		assertEquals(result, "success");

	}

	/**
	 * Test method for
	 * {@link com.librarymanagement.controller.BookManager#updateBook(com.librarymanagement.bean.Book)}.
	 * 
	 * @throws LibraryManagementException
	 */
	@Test
	public void testUpdateBook() throws LibraryManagementException {
		BookManager bookManager = new BookManager();
		bookManager.createBook(bookSql);

		bookSql.setNumberOfAvailableBooks(0);

		String res = bookManager.updateBook(bookSql);
		assertEquals(res, "success");

		Book resBook = bookManager.getBook(bookSql.getBookId());
		assertEquals(resBook.getNumberOfAvailableBooks(), 0);
	}

	/**
	 * Test method for
	 * {@link com.librarymanagement.controller.BookManager#deleteBook(long)}.
	 * 
	 * @throws LibraryManagementException
	 */
	@Test
	public void testDeleteBook() throws LibraryManagementException {
		BookManager bookManager = new BookManager();
		bookManager.createBook(bookJava);

		String response = bookManager.deleteBook(bookJava.getBookId());
		assertEquals(response, "success");
	}
}
