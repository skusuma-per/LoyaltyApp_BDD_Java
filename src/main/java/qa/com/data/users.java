package qa.com.data;

import java.sql.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class users {
	
	String firstName;
	String lastName;
	String emailId;
	String PhoneNumber;
	String userName;
	int totalRewardPoints;
	int pointsRedeemed;
	int balance;
	int rewardprice;
	Date createDate;
	String createdBy;
	Random rand= new Random();

	
	
	public void Users(String firstName, String lastName, String emailId, String PhoneNumber, int totalRewardPoints, int balance) {

		this.firstName=firstName;
		this.lastName=lastName;
		this.emailId=emailId;
		this.PhoneNumber=PhoneNumber;
		this.totalRewardPoints=totalRewardPoints;
		this.balance=balance;
		}


	//getters and setters

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUserName() {
		String generatedString = RandomStringUtils.randomAlphanumeric(8);
		return generatedString;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	
	}
	
	public static String getPhoneNumber() {
		String generatedString = RandomStringUtils.randomNumeric(10);
		return generatedString;
	}

	public void setPhoneNumber(String phonenumber) {
		this.PhoneNumber = PhoneNumber;
	}

	
	public String getEmailid() {
		String generatedString = RandomStringUtils.randomAlphanumeric(10);
		return (generatedString+"email.com");
	}

	public void setEmailid(String emailId) {
		this.emailId = emailId;
	}

	
	public int getTotalrewardPoints() {
		return totalRewardPoints;
	}

	public int setTotalrewardPoints(int totalRewardPoints) {
		this.totalRewardPoints = totalRewardPoints;
		return totalRewardPoints;
	}

	public int getPointsRedeemed() {
		int generatedInt = rand.nextInt(5);
		return generatedInt;
	}

	public void setPointsredeemed(int pointsRedeemed) {
		this.pointsRedeemed = pointsRedeemed;
		
	}

	public int getBalance() {
		int balance = Math.subtractExact((totalRewardPoints), (pointsRedeemed));
		return balance;
	}


	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public int getRewardprice() {
		return rewardprice;
	}


	public void setRewardprice(int rewardprice) {
		this.rewardprice = rewardprice;
	}

}
