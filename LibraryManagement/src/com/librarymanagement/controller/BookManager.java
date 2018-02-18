package com.librarymanagement.controller;

import com.librarymanagement.bean.Book;
import com.librarymanagement.common.LibraryManagementException;
import com.librarymanagement.dao.BookDao;
import com.librarymanagement.dao.BookDaoImpl;

public class BookManager {

	public Book getBook(long bookId) throws LibraryManagementException {
		BookDao bookDao = new BookDaoImpl();
		Book response = bookDao.get(bookId);	
		return response;
	}
	
	public String createBook(Book book) throws LibraryManagementException {
		BookDao bookDao = new BookDaoImpl();
		bookDao.create(book);
		return "success";
	}
	
	public String updateBook(Book book) throws LibraryManagementException {
		BookDao bookDao = new BookDaoImpl();
		bookDao.update(book);
		return "success";
	}

	public String deleteBook(long bookId) throws LibraryManagementException {
		BookDao bookDao = new BookDaoImpl();
		bookDao.delete(bookId);
		return "success";
	}
}
