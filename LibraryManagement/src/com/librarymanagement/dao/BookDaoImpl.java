package com.librarymanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.librarymanagement.bean.Book;
import com.librarymanagement.common.CommonConstants;
import com.librarymanagement.common.LibraryManagementException;

public class BookDaoImpl implements BookDao {
	
	private static Connection dbConnection;
	private final static Logger LOGGER = Logger.getLogger(BookDaoImpl.class.getName());
	
	public BookDaoImpl() {
		dbConnection = DataBaseConnection.getDbConnection();
	}

	@Override
	public Book get(long bookId) throws LibraryManagementException {
		PreparedStatement selectPreparedStatement = null;
		Book book = null;
		
		try {
			dbConnection.setAutoCommit(false);
			selectPreparedStatement = dbConnection.prepareStatement(CommonConstants.SELECT_BOOK_QUERY);
			selectPreparedStatement.setLong(1, bookId);
			ResultSet rs = selectPreparedStatement.executeQuery();
            while (rs.next()) {
                book = new Book(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
                		rs.getInt(6), rs.getInt(7));
            }
            
            selectPreparedStatement.close();
            dbConnection.commit();			
		} catch (SQLException e) {
			LOGGER.severe("Exception in get book : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}
		return book;
	}

	@Override
	public List<Book> getAll() {
		return null;
	}

	@Override
	public void create(Book book) throws LibraryManagementException {
		PreparedStatement insertPreparedStatement = null;
		
		 try {
			 dbConnection.setAutoCommit(false);
			insertPreparedStatement = dbConnection.prepareStatement(CommonConstants.INSERT_BOOK_QUERY);
			insertPreparedStatement.setLong(1, book.getBookId());
	        insertPreparedStatement.setString(2, book.getName());
	        insertPreparedStatement.setString(3, book.getAuthor());
	        insertPreparedStatement.setString(4, book.getTitle());
	        insertPreparedStatement.setInt(5, book.getNumberOfBooks());
	        insertPreparedStatement.setInt(6, book.getNumberOfBorrowedBooks());
	        insertPreparedStatement.setInt(7, book.getNumberOfAvailableBooks());
	        
	        insertPreparedStatement.executeUpdate();	        
	        insertPreparedStatement.close();	        
	        dbConnection.commit();
		} catch (SQLException e) {
			LOGGER.severe("Exception in create book : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}		
	}

	@Override
	public String update(Book book) throws LibraryManagementException {
		
		PreparedStatement updatePreparedStatement = null;
		
		 try {
			 dbConnection.setAutoCommit(false);
			updatePreparedStatement = dbConnection.prepareStatement(CommonConstants.UPDATE_BOOK_QUERY);
	        updatePreparedStatement.setString(1, book.getName());
	        updatePreparedStatement.setString(2, book.getAuthor());
	        updatePreparedStatement.setString(3, book.getTitle());
	        updatePreparedStatement.setInt(4, book.getNumberOfBooks());
	        updatePreparedStatement.setInt(5, book.getNumberOfBorrowedBooks());
	        updatePreparedStatement.setInt(6, book.getNumberOfAvailableBooks());
	        updatePreparedStatement.setLong(7, book.getBookId());	        
	        
	        updatePreparedStatement.executeUpdate();
	        
	        updatePreparedStatement.close();
	        
	        dbConnection.commit();
		} catch (SQLException e) {
			LOGGER.severe("Exception in update book : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}	
		 return "success";
	}

	@Override
	public void delete(long bookId) throws LibraryManagementException {
        PreparedStatement deletePreparedStatement = null;
		
		try {
			dbConnection.setAutoCommit(false);
			deletePreparedStatement = dbConnection.prepareStatement(CommonConstants.DELETE_BOOK_QUERY);
			deletePreparedStatement.setLong(1, bookId);
            
			deletePreparedStatement.executeUpdate();
			deletePreparedStatement.close();
			dbConnection.commit();
		} catch (SQLException e) {
			LOGGER.severe("Exception in delete book : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}
	}
}
