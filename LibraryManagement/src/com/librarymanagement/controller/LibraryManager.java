package com.librarymanagement.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.librarymanagement.bean.Book;
import com.librarymanagement.bean.BookingDetails;
import com.librarymanagement.bean.Reader;
import com.librarymanagement.common.LibraryManagementException;
import com.librarymanagement.common.LibraryManagementUtility;
import com.librarymanagement.dao.BookDao;
import com.librarymanagement.dao.BookDaoImpl;
import com.librarymanagement.dao.BookManagementDao;
import com.librarymanagement.dao.BookManagementDaoImpl;
import com.librarymanagement.dao.ReaderDao;
import com.librarymanagement.dao.ReaderDaoImpl;

public class LibraryManager {

	public List<Book> getAllAvailableBooks() {

		return null;
	}

	public List<BookingDetails> getAllBorrowedBooks() throws LibraryManagementException {
		BookManagementDao bookingDao = new BookManagementDaoImpl();
		List<BookingDetails> bookingList = bookingDao.getAll();

		return bookingList;
	}

	public void borrowABook(int readerId, long bookId) throws LibraryManagementException {
		// validate the request
		if (!LibraryManagementUtility.validateParameters(readerId)
				&& !LibraryManagementUtility.validateParameters(bookId)) {
			throw new LibraryManagementException(
					"Invalid Parameters received, Library Management doesn't support these parameters");
		}
		
		// check subscription for user
		ReaderDao readerDao = new ReaderDaoImpl();
		Reader reader = readerDao.get(readerId);
		
		int numberOfBooksBorrowed = getNumberOfBooksBorrowed(readerId);
		
		if(reader.getSubscriptionId() == 1 && numberOfBooksBorrowed > 5) {
			throw new LibraryManagementException("Reader has borrowed the maximum number of books.");
		} else if(reader.getSubscriptionId() == 2 && numberOfBooksBorrowed > 10) {
			throw new LibraryManagementException("Reader has borrowed the maximum number of books.");
		}
		
		// insert in the bookingDetails table
		BookManagementDao bookingDao = new BookManagementDaoImpl();
		bookingDao.create(readerId, bookId);

		BookDao bookDao = new BookDaoImpl();
		// get book from db
		Book book = bookDao.get(bookId);

		if (null != book) {
			// update in the book table
			book.setNumberOfAvailableBooks(book.getNumberOfAvailableBooks() - 1);
			book.setNumberOfBorrowedBooks(book.getNumberOfBorrowedBooks() + 1);
			bookDao.update(book);
		} else {
			throw new LibraryManagementException("Lending of a book failed, due to book is not present in DB");
		}
	}

	private int getNumberOfBooksBorrowed(int readerId) throws LibraryManagementException {
		BookManagementDao bookingDao = new BookManagementDaoImpl();
		int numberOfBooksBorrowed = bookingDao.getCount(readerId);
		return numberOfBooksBorrowed;
	}

	public void returnABook(int bookingId) throws LibraryManagementException {

		// validate the request
		if (!LibraryManagementUtility.validateParameters(bookingId)) {
			throw new LibraryManagementException(
					"Invalid Parameter received, Library Management doesn't support this parameter");

		}

		// update return date in the bookingDetails table
		BookManagementDao bookingDao = new BookManagementDaoImpl();
		Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

		BookingDetails bookingDetails = bookingDao.get(bookingId);
		bookingDetails.setReturnDate(currentDate);

		bookingDao.update(bookingDetails);

		BookDao bookDao = new BookDaoImpl();
		// get book from db
		Book book = bookDao.get(bookingDetails.getBookId());

		if (null != book) {
			// update in the book table
			book.setNumberOfAvailableBooks(book.getNumberOfAvailableBooks() + 1);
			book.setNumberOfBorrowedBooks(book.getNumberOfBorrowedBooks() - 1);
			bookDao.update(book);
		} else {
			throw new LibraryManagementException("Return of a book failed, due to book is not present in DB");
		}

	}
	
	public BookingDetails getBookingDetails(int bookingId) throws LibraryManagementException {
		BookManagementDao bookingDao = new BookManagementDaoImpl();
		BookingDetails booking = bookingDao.get(bookingId);
		return booking;
	}

	public void removeBooking(int bookingId) throws LibraryManagementException {
		BookManagementDao bookingDao = new BookManagementDaoImpl();
		bookingDao.delete(bookingId);
	}
	public void reserveABook() {

	}

	public Book searchABook() {
		return new Book();
	}
}
