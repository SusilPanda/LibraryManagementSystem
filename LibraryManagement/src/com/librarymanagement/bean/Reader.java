package com.librarymanagement.bean;

public class Reader {
	

	private int readerId;
	private String readerName;
	private String userName;
	private String password;
	private String emailId;
	private int subscriptionId;
	
	
	public Reader(int id, String name, String userName, String password, String emailId, int subId) {
		this.readerId = id;
		this.readerName = name;
		this.userName = userName;
		this.password = password;
		this.emailId = emailId;
		this.subscriptionId = subId;
		
	}
	
	/**
	 * @return the subscriptionId
	 */
	public int getSubscriptionId() {
		return subscriptionId;
	}
	/**
	 * @param subscriptionId the subscriptionId to set
	 */
	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the readerId
	 */
	public int getReaderId() {
		return readerId;
	}
	/**
	 * @param readerId the readerId to set
	 */
	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}
	/**
	 * @return the readerName
	 */
	public String getReaderName() {
		return readerName;
	}
	/**
	 * @param readerName the readerName to set
	 */
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	/**
	 * @return the readerPassword
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param readerPassword the readerPassword to set
	 */
	public void setPassword(String readerPassword) {
		this.password = readerPassword;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "Reader [readerId=" + readerId + ", readerName=" + readerName + ", userName=" + userName + ", password="
				+ password + ", emailId=" + emailId + "]";
	}
}
