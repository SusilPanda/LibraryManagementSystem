package com.library.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.bean.Reader;

public class ReaderDaoImpl implements ReaderDao {
	
	private static Connection dbConnection;
	
	public ReaderDaoImpl() {
		dbConnection = DataBaseConnection.getDbConnection();
		DataBaseConnection.setUp();
	}

	@Override
	public Reader get(int readerId) {
		
		PreparedStatement selectPreparedStatement = null;
		String SelectQuery = "select readerid, name, username, password, emailid from READER where readerid = ?";
		Reader reader = null;
		
		try {
			dbConnection.setAutoCommit(false);
			selectPreparedStatement = dbConnection.prepareStatement(SelectQuery);
			selectPreparedStatement.setInt(1, readerId);
			ResultSet rs = selectPreparedStatement.executeQuery();
            System.out.println("H2 In-Memory Database inserted through PreparedStatement");
            while (rs.next()) {
                System.out.println("Id " + rs.getInt(1) + " Name " + rs.getString(2));
                reader = new Reader(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                
            }
            selectPreparedStatement.close();

            dbConnection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reader;
	}

	@Override
	public List<Reader> get() {
		PreparedStatement selectAllPreparedStatement = null;
		String SelectQuery = "select * from READER";
		List<Reader> readerList = new ArrayList<>();
		
		try {
			dbConnection.setAutoCommit(false);
			selectAllPreparedStatement = dbConnection.prepareStatement(SelectQuery);
			ResultSet rs = selectAllPreparedStatement.executeQuery();
            System.out.println("H2 In-Memory Database inserted through PreparedStatement");
            while (rs.next()) {
                System.out.println("Id " + rs.getInt(1) + " Name " + rs.getString(2));
                readerList.add(new Reader(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                
            }
            selectAllPreparedStatement.close();

            dbConnection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return readerList;
	}

	@Override
	public void create(Reader reader) {
		
		PreparedStatement insertPreparedStatement = null;
		String insertQuery = "INSERT INTO READER" + "(readerid, name, username, password, emailid) values" + "(?, ?, ?, ?, ?)";
		
		 try {
			 dbConnection.setAutoCommit(false);
			insertPreparedStatement = dbConnection.prepareStatement(insertQuery);
			insertPreparedStatement.setInt(1, reader.getReaderId());
	        insertPreparedStatement.setString(2, reader.getReaderName());
	        insertPreparedStatement.setString(3, reader.getUserName());
	        insertPreparedStatement.setString(4, reader.getPassword());
	        insertPreparedStatement.setString(5, reader.getEmailId());
	        
	        insertPreparedStatement.executeUpdate();
	        
	        insertPreparedStatement.close();
	        
	        dbConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public String update(Reader reader) {
		
		PreparedStatement updatePreparedStatement = null;
		String insertQuery = "UPDATE INTO READER" + "(name, username, password, emailid) values" + "(?, ?, ?, ?) where readerid = ?";
		
		 try {
			 dbConnection.setAutoCommit(false);
			 updatePreparedStatement = dbConnection.prepareStatement(insertQuery);
			 
			 updatePreparedStatement.setString(1, reader.getReaderName());
			 updatePreparedStatement.setString(2, reader.getUserName());
			 updatePreparedStatement.setString(4, reader.getPassword());
			 updatePreparedStatement.setInt(5, reader.getReaderId());
	        
			 updatePreparedStatement.executeUpdate();
	        
			 updatePreparedStatement.close();
	        
	        dbConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return "success";
	}

	@Override
	public void delete(int readerId) {
		PreparedStatement deletePreparedStatement = null;
		String deleteQuery = "DELETE FROM READER where readerid = ?";
		
		try {
			dbConnection.setAutoCommit(false);
			deletePreparedStatement = dbConnection.prepareStatement(deleteQuery);
			deletePreparedStatement.setInt(1, readerId);
            
			deletePreparedStatement.executeUpdate();
	        
			deletePreparedStatement.close();
			dbConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
