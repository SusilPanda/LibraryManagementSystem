package com.librarymanagement.controller;

import com.librarymanagement.bean.Reader;

public class MainTest {
	
	public static void main(String[] args) {
		ReaderManager readerManager = new ReaderManager();
	    
		Reader reader = new Reader(3, "Veru2", "user", "user", "abc@gmail.com");
		readerManager.createReader(reader);

		readerManager.getReader(3);
		
	}
	
}
