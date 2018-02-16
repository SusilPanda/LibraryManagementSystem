package com.librarymanagement.controller;

import com.librarymanagement.bean.Reader;
import com.librarymanagement.dao.ReaderDao;
import com.librarymanagement.dao.ReaderDaoImpl;

public class ReaderManager {
	
	public Reader getReader(int readerId) {
		ReaderDao daoImpl = new ReaderDaoImpl();
		Reader reader = daoImpl.get(readerId);
		return reader;
	}
	
	public String createReader(Reader reader) {
		
		ReaderDao daoImpl = new ReaderDaoImpl();
		daoImpl.create(reader);
		
		return "success";
	}
	
	public String updateReader(Reader reader) {
		
		return "success";
	}
	
	public String deleteReader(int readerId) {
		
		ReaderDao daoImpl = new ReaderDaoImpl();
		daoImpl.delete(readerId);
		return "success";
	}

	
	
}
