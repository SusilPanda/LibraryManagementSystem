package com.librarymanagement.dao;

import java.util.List;

import com.librarymanagement.bean.BookingDetails;
import com.librarymanagement.common.LibraryManagementException;

public interface BookManagementDao {

	public List<BookingDetails> getAll() throws LibraryManagementException;
	public BookingDetails get(int bookingId) throws LibraryManagementException;
	public void create(int userId, long BookId) throws LibraryManagementException;
	public void update(BookingDetails booking) throws LibraryManagementException;
	public void delete(int bookingId) throws LibraryManagementException;
	public int getCount(int readerId) throws LibraryManagementException;
	
}
