package com.TravellingWorld.user;

public class UserQuery {

	final static String getUserData = "select * from m_user where email_id = ? or contact_no=?";
	final static String getEmailIdCount = "select count(*) count from m_user where email_id=?";
	final static String getMobileCount = "select count(*) count from m_user where contact_no=?";
}
