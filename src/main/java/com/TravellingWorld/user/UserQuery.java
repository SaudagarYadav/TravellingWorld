package com.TravellingWorld.user;

public class UserQuery {

	final static String getUserData = "select * from m_user where email_id = ? or contact_no=?";
	final static String getSameUserNameCount = "select count(*) count from m_user where email_id= ? or contact_no = ?";
}
