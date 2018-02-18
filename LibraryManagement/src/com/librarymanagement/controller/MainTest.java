package com.librarymanagement.controller;

import java.sql.Connection;
import java.util.List;

import com.librarymanagement.bean.Book;
import com.librarymanagement.bean.BookingDetails;
import com.librarymanagement.bean.Reader;
import com.librarymanagement.dao.DataBaseConnection;

public class MainTest {

	public static void main(String[] args) {
		ReaderManager readerManager = new ReaderManager();
		try {
			DataBaseConnection.setUp();
			DataBaseConnection.insertDefaultDataInDb(1, "Monthly", 30);
			DataBaseConnection.insertDefaultDataInDb(2, "Annually", 364);

			Reader reader = new Reader(103, "Veru2", "user", "user", "abc@gmail.com", 1);
			readerManager.createReader(reader);

			readerManager.getReader(103);

			Book book = new Book(1, "Core java", "Programming", "schand", 5, 0, 5);
			BookManager bookManager = new BookManager();
			bookManager.createBook(book);

			Book response = bookManager.getBook(1);
			System.out.println("Book : " + response);

			LibraryManager libManager = new LibraryManager();

			libManager.borrowABook(103, 1);

			List<BookingDetails> list = libManager.getAllBorrowedBooks();
			System.out.println("Borrowed Books details : " + list);

			Book responseBook = bookManager.getBook(list.get(0).getBookId());
			System.out.println("after borrow number of Books : " + responseBook);

			libManager.returnABook(list.get(0).getBookingId());
			Book returnBook = bookManager.getBook(list.get(0).getBookId());
			System.out.println("after returning number of Books : " + returnBook);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
