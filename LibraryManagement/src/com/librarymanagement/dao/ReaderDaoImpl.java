package com.librarymanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.librarymanagement.bean.Reader;
import com.librarymanagement.common.CommonConstants;
import com.librarymanagement.common.LibraryManagementException;

public class ReaderDaoImpl implements ReaderDao {
	
	private static Connection dbConnection;
	
	public ReaderDaoImpl() {
		dbConnection = DataBaseConnection.getDbConnection();
	}

	@Override
	public Reader get(int readerId) throws LibraryManagementException {
		
		PreparedStatement selectPreparedStatement = null;
		Reader reader = null;
		
		try {
			dbConnection.setAutoCommit(false);
			selectPreparedStatement = dbConnection.prepareStatement(CommonConstants.SELECT_READER_QUERY);
			selectPreparedStatement.setInt(1, readerId);
			ResultSet rs = selectPreparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("Id " + rs.getInt(1) + " Name " + rs.getString(2));
                reader = new Reader(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
            }
            
            selectPreparedStatement.close();
            dbConnection.commit();
		} catch (SQLException e) {
			throw new LibraryManagementException(e.getMessage());
		}
		
		return reader;
	}

	@Override
	public List<Reader> get() throws LibraryManagementException {
		PreparedStatement selectAllPreparedStatement = null;
		
		List<Reader> readerList = new ArrayList<>();
		
		try {
			dbConnection.setAutoCommit(false);
			selectAllPreparedStatement = dbConnection.prepareStatement(CommonConstants.SELECT_ALL_READER_QUERY);
			ResultSet rs = selectAllPreparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("Id " + rs.getInt(1) + " Name " + rs.getString(2));
                readerList.add(new Reader(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
            }
            
            selectAllPreparedStatement.close();
            dbConnection.commit();			
		} catch (SQLException e) {
			throw new LibraryManagementException(e.getMessage());
		}
		
		return readerList;
	}

	@Override
	public void create(Reader reader) throws LibraryManagementException {
		
		PreparedStatement insertPreparedStatement = null;
		
		 try {
			 dbConnection.setAutoCommit(false);
			insertPreparedStatement = dbConnection.prepareStatement(CommonConstants.INSERT_READER_QUERY);
			insertPreparedStatement.setInt(1, reader.getReaderId());
	        insertPreparedStatement.setString(2, reader.getReaderName());
	        insertPreparedStatement.setString(3, reader.getUserName());
	        insertPreparedStatement.setString(4, reader.getPassword());
	        insertPreparedStatement.setString(5, reader.getEmailId());
	        insertPreparedStatement.setInt(6, reader.getSubscriptionId());
	        
	        insertPreparedStatement.executeUpdate();
	        insertPreparedStatement.close();
	        dbConnection.commit();
		} catch (SQLException e) {
			throw new LibraryManagementException(e.getMessage());
		}		
	}

	@Override
	public String update(Reader reader) throws LibraryManagementException {
		
		PreparedStatement updatePreparedStatement = null;
		
		 try {
			 dbConnection.setAutoCommit(false);
			 updatePreparedStatement = dbConnection.prepareStatement(CommonConstants.UPDATE_READER_QUERY);
			 
			 updatePreparedStatement.setString(1, reader.getReaderName());
			 updatePreparedStatement.setString(2, reader.getUserName());
			 updatePreparedStatement.setString(4, reader.getPassword());
			 updatePreparedStatement.setInt(5, reader.getReaderId());
			 updatePreparedStatement.setInt(6, reader.getSubscriptionId());
	        
			 updatePreparedStatement.executeUpdate();
			 updatePreparedStatement.close();
	        dbConnection.commit();
		} catch (SQLException e) {
			throw new LibraryManagementException(e.getMessage());
		}
		 return "success";
	}

	@Override
	public void delete(int readerId) throws LibraryManagementException {
		PreparedStatement deletePreparedStatement = null;
		
		try {
			dbConnection.setAutoCommit(false);
			deletePreparedStatement = dbConnection.prepareStatement(CommonConstants.DELETE_READER_QUERY);
			deletePreparedStatement.setInt(1, readerId);
            
			deletePreparedStatement.executeUpdate();
			deletePreparedStatement.close();
			dbConnection.commit();
		} catch (SQLException e) {
			throw new LibraryManagementException(e.getMessage());
		}
	}
}
