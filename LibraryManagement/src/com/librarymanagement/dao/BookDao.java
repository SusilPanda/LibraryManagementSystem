package com.librarymanagement.dao;

import java.util.List;

import com.librarymanagement.bean.Book;

public interface BookDao {
	
	public Book get(long bookId);
	public List<Book> get();
	public void create(Book book);
	public Book update(Book book);
	public void delete(long bookId);

}
