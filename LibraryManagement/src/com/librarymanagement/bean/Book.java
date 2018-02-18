package com.librarymanagement.bean;

public class Book {

	
	public long bookId;
	public String name;
	public String title;
	public String author;
	public int numberOfBooks;
	public int numberOfBorrowedBooks;
	public int numberOfAvailableBooks;
	
	public Book() {
	}
	
	public Book(long bookId, String name, String title, String author, int numberOfBooks, int numberOfBorrowedBooks,
			int numberOfAvailableBooks) {
		this.bookId = bookId;
		this.name = name;
		this.title = title;
		this.author = author;
		this.numberOfBooks = numberOfBooks;
		this.numberOfBorrowedBooks = numberOfBorrowedBooks;
		this.numberOfAvailableBooks = numberOfAvailableBooks;
	}

	/**
	 * @return the bookId
	 */
	public long getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the numberOfBooks
	 */
	public int getNumberOfBooks() {
		return numberOfBooks;
	}
	/**
	 * @param numberOfBooks the numberOfBooks to set
	 */
	public void setNumberOfBooks(int numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}
	/**
	 * @return the numberOfBorrowedBooks
	 */
	public int getNumberOfBorrowedBooks() {
		return numberOfBorrowedBooks;
	}
	/**
	 * @param numberOfBorrowedBooks the numberOfBorrowedBooks to set
	 */
	public void setNumberOfBorrowedBooks(int numberOfBorrowedBooks) {
		this.numberOfBorrowedBooks = numberOfBorrowedBooks;
	}
	/**
	 * @return the numberOfAvailableBooks
	 */
	public int getNumberOfAvailableBooks() {
		return numberOfAvailableBooks;
	}
	/**
	 * @param numberOfAvailableBooks the numberOfAvailableBooks to set
	 */
	public void setNumberOfAvailableBooks(int numberOfAvailableBooks) {
		this.numberOfAvailableBooks = numberOfAvailableBooks;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", name=" + name + ", title=" + title + ", author=" + author
				+ ", numberOfBooks=" + numberOfBooks + ", numberOfBorrowedBooks=" + numberOfBorrowedBooks
				+ ", numberOfAvailableBooks=" + numberOfAvailableBooks + "]";
	}
	
	
}
