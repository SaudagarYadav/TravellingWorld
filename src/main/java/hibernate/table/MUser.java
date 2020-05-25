package hibernate.table;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class MUser {

	@Id
	@GeneratedValue
	private int userPid;
	private String name;
	private String emailId;
	private String contactNo;
	private String password;
	private Date registrationDate;
	private String userName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserPid() {
		return userPid;
	}
	public void setUserPid(int userPid) {
		this.userPid = userPid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
}
