package com.librarymanagement.dao;

import java.util.List;

import com.librarymanagement.bean.Reader;
import com.librarymanagement.common.LibraryManagementException;

public interface ReaderDao {
	
	public Reader get(int readerId) throws LibraryManagementException;
	public List<Reader> get() throws LibraryManagementException;
	public void create(Reader reader) throws LibraryManagementException;
	public String update(Reader reader) throws LibraryManagementException;
	public void delete(int readerId) throws LibraryManagementException;

}
