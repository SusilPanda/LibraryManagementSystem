package com.librarymanagement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.librarymanagement.bean.BookingDetails;
import com.librarymanagement.common.CommonConstants;
import com.librarymanagement.common.LibraryManagementException;

public class BookManagementDaoImpl implements BookManagementDao {
	
	private static Connection dbConnection;
	
	public BookManagementDaoImpl() {
		dbConnection = DataBaseConnection.getDbConnection();
	}
	
	@Override
	public void create(int readerId, long bookId) throws LibraryManagementException {

		PreparedStatement insertPreparedStatement = null;
		
		 try {
			dbConnection.setAutoCommit(false);
			java.util.Date date = Calendar.getInstance().getTime();
			Date currentDate = new Date(date.getTime());
			
			insertPreparedStatement = dbConnection.prepareStatement(CommonConstants.INSERT_BOOKING_DETAILS_QUERY);
			insertPreparedStatement.setLong(1, bookId);
	        insertPreparedStatement.setInt(2, readerId);
	        insertPreparedStatement.setDate(3, currentDate);
	        
	        insertPreparedStatement.executeUpdate();	        
	        insertPreparedStatement.close();	        
	        dbConnection.commit();
		} catch (SQLException e) {
			throw new LibraryManagementException(e.getMessage());
		}		
	}

	@Override
	public BookingDetails get(int bookingId) throws LibraryManagementException {
		PreparedStatement selectPreparedStatement = null;
		BookingDetails bookingDetails = null;
		
		try {
			dbConnection.setAutoCommit(false);
			selectPreparedStatement = dbConnection.prepareStatement(CommonConstants.SELECT_BOOKING_DETAILS_QUERY);
			selectPreparedStatement.setInt(1, bookingId);
			ResultSet rs = selectPreparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("Booking Id " + rs.getInt(1) + " Borrow Date : " + rs.getString(2));
                bookingDetails = new BookingDetails(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getLong(4), rs.getInt(5));
            }
            selectPreparedStatement.close();

            dbConnection.commit();
		} catch (SQLException e) {
			throw new LibraryManagementException(e.getMessage());
		}
		
		return bookingDetails;
	}

	@Override
	public void update(BookingDetails booking) throws LibraryManagementException {
		PreparedStatement updatePreparedStatement = null;
		
		 try {
			 dbConnection.setAutoCommit(false);
			updatePreparedStatement = dbConnection.prepareStatement(CommonConstants.UPDATE_BOOKING_DETAILS_QUERY);
	        updatePreparedStatement.setDate(1, booking.getBorrowDate());
	        updatePreparedStatement.setDate(2, booking.getReturnDate());
	        updatePreparedStatement.setLong(3, booking.getBookId());
	        updatePreparedStatement.setInt(4, booking.getReaderId());
	       
	        updatePreparedStatement.setLong(5, booking.getBookingId());	        
	        
	        updatePreparedStatement.executeUpdate();
	        
	        updatePreparedStatement.close();
	        
	        dbConnection.commit();
		} catch (SQLException e) {
			throw new LibraryManagementException(e.getMessage());
		}	
	}

	@Override
	public void delete(int bookingId) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<BookingDetails> getAll() throws LibraryManagementException {
		PreparedStatement selectAllPreparedStatement = null;
		List<BookingDetails> bookingList = new ArrayList<>();
		
		try {
			dbConnection.setAutoCommit(false);
			selectAllPreparedStatement = dbConnection.prepareStatement(CommonConstants.SELECT_ALL_BOOKING_DETALS_QUERY);
			ResultSet rs = selectAllPreparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("Booking Id " + rs.getInt(1) + " Borrow Date : " + rs.getString(2));
                bookingList.add(new BookingDetails(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getLong(4), rs.getInt(5)));
                
            }
            selectAllPreparedStatement.close();

            dbConnection.commit();
			
		} catch (SQLException e) {
			throw new LibraryManagementException(e.getMessage());
		}
		return bookingList;
	}

	@Override
	public int getCount(int readerId) throws LibraryManagementException {
		PreparedStatement selectPreparedStatement = null;
		int numberOfBooksBorrowed = 0;
		
		try {
			dbConnection.setAutoCommit(false);
			selectPreparedStatement = dbConnection.prepareStatement(CommonConstants.SELECT_COUNT_BOOKING_DETAILS_QUERY);
			selectPreparedStatement.setInt(1, readerId);
			ResultSet rs = selectPreparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("Number of borrowed books : " + rs.getInt(1));
                numberOfBooksBorrowed = rs.getInt(1);
            }
            selectPreparedStatement.close();

            dbConnection.commit();
		} catch (SQLException e) {
			throw new LibraryManagementException(e.getMessage());
		}
		
		return numberOfBooksBorrowed;
	}
}
