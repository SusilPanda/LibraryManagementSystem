package com.librarymanagement.controller;

import com.librarymanagement.bean.Reader;
import com.librarymanagement.common.LibraryManagementException;
import com.librarymanagement.dao.ReaderDao;
import com.librarymanagement.dao.ReaderDaoImpl;

public class ReaderManager {
	
	/**
	 * @param readerId
	 * @return
	 * @throws LibraryManagementException
	 */
	public Reader getReader(int readerId) throws LibraryManagementException {
		ReaderDao daoImpl = new ReaderDaoImpl();
		Reader reader = daoImpl.get(readerId);
		return reader;
	}
	
	/**
	 * @param reader
	 * @return
	 * @throws LibraryManagementException
	 */
	public String createReader(Reader reader) throws LibraryManagementException {
		
		ReaderDao daoImpl = new ReaderDaoImpl();
		daoImpl.create(reader);
		
		return "success";
	}
	
	/**
	 * @param reader
	 * @return
	 * @throws LibraryManagementException
	 */
	public String updateReader(Reader reader) throws LibraryManagementException {
		ReaderDao daoImpl = new ReaderDaoImpl();
		daoImpl.update(reader);
		
		return "success";
	}
	
	/**
	 * @param readerId
	 * @return
	 * @throws LibraryManagementException
	 */
	public String deleteReader(int readerId) throws LibraryManagementException {
		
		ReaderDao daoImpl = new ReaderDaoImpl();
		daoImpl.delete(readerId);
		return "success";
	}
}
