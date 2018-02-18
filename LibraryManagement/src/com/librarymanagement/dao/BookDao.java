package com.librarymanagement.dao;

import java.util.List;

import com.librarymanagement.bean.Book;
import com.librarymanagement.common.LibraryManagementException;

public interface BookDao {
	
	public Book get(long bookId) throws LibraryManagementException;
	public List<Book> getAll() throws LibraryManagementException;
	public void create(Book book) throws LibraryManagementException;
	public String update(Book book) throws LibraryManagementException;
	public void delete(long bookId) throws LibraryManagementException;

}
