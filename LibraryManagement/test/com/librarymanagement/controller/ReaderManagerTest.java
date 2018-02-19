package com.librarymanagement.controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.librarymanagement.bean.Reader;
import com.librarymanagement.common.LibraryManagementException;
import com.librarymanagement.controller.ReaderManager;
import com.librarymanagement.dao.DataBaseConnection;

public class ReaderManagerTest {
	
	private static Reader readerJohn;
	private static Reader readerSam;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataBaseConnection.setUp();
		DataBaseConnection.insertDefaultDataInDb(1, "Monthly", 30);
		DataBaseConnection.insertDefaultDataInDb(2, "Annually", 365);
		
	}
	
	@Before
	public void setUp() throws Exception {
		readerJohn = new Reader(101, "john", "user", "user", "john@gmail.com", 1);
		readerSam = new Reader(102, "Sam", "sam", "sam", "sam@gmail.com", 2);		
	}
	
	@After
	public void destroy() throws LibraryManagementException {
		readerJohn = null;
		readerSam = null;
	}

	@Test
	public void testGetReader() throws LibraryManagementException {
		ReaderManager readerManager = new ReaderManager();
		readerManager.createReader(readerJohn);
		Reader responseReader = readerManager.getReader(readerJohn.getReaderId());
		
		assertEquals(responseReader.getReaderId(), readerJohn.getReaderId());
	}

	@Test
	public void testCreateReader() throws LibraryManagementException {
		ReaderManager readerManager = new ReaderManager();
		String response = readerManager.createReader(readerJohn);

		assertEquals(response, "success");

	}

	@Test
	public void testUpdateReader() throws LibraryManagementException {
		ReaderManager readerManager = new ReaderManager();
		readerManager.createReader(readerSam);
		String updatedEmailId = "samNew@gmail.com";
		
		readerSam.setEmailId(updatedEmailId);
		String response = readerManager.updateReader(readerSam);
		assertEquals(response, "success");
		
		Reader result = readerManager.getReader(readerSam.getReaderId());
		assertEquals(result.getEmailId(), updatedEmailId);
	}

	@Test
	public void testDeleteReader() throws LibraryManagementException {
		ReaderManager readerManager = new ReaderManager();
		String response = readerManager.deleteReader(readerJohn.getReaderId());
		assertEquals(response, "success");
	}
}
