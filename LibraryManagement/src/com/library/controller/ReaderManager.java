package com.library.controller;

import com.library.bean.Reader;
import com.library.db.ReaderDao;
import com.library.db.ReaderDaoImpl;

public class ReaderManager {
	
	public Reader getReader(int readerId) {
		ReaderDao daoImpl = new ReaderDaoImpl();
		Reader reader = daoImpl.get(readerId);
		//System.out.println("Reader : " + reader.getReaderName());
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
