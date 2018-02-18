package com.librarymanagement.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.librarymanagement.bean.Reader;
import com.librarymanagement.common.LibraryManagementException;
import com.librarymanagement.controller.ReaderManager;

public class ReaderManagerTest {
	
	private static Reader reader;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		reader = new Reader(5, "Veru5", "user", "user", "veru5@gmail.com", 1);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
		ReaderManager readerManager = new ReaderManager();
		Reader response = readerManager.getReader(reader.getReaderId());
		if (response != null) {
			readerManager.deleteReader(reader.getReaderId());
		}
		
	}

	@Test
	void testGetReader() {
		ReaderManager readerManager = new ReaderManager();
		Reader responseReader = null;
		try {
			responseReader = readerManager.getReader(reader.getReaderId());
		} catch (LibraryManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals(responseReader.getReaderId(), reader.getReaderId());
	}

	@Test
	void testCreateReader() {
		ReaderManager readerManager = new ReaderManager();
		String response = null;
		try {
			response = readerManager.createReader(reader);
		} catch (LibraryManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertEquals(response, "success");

	}

	@Test
	void testUpdateReader() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteReader() {
		ReaderManager readerManager = new ReaderManager();
		String response = null;
		try {
			response = readerManager.deleteReader(reader.getReaderId());
		} catch (LibraryManagementException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(response, "success");
	}

}
