package com.TravellingWorld.validation;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import com.TravellingWorld.user.UserJsonConstants;

import hibernate.table.DBConstant;

public class GenTool {

	@SuppressWarnings("unchecked")
	public static void userResultsetToJson(JSONObject userObj, ResultSet rs, boolean loginStatus) throws SQLException {

		if (loginStatus){
			userObj.put(UserJsonConstants.USER_PID, rs.getString(DBConstant.USER_PID));
			userObj.put(UserJsonConstants.NAME, rs.getString(DBConstant.NAME));
			userObj.put(UserJsonConstants.EMAIL_ID, rs.getString(DBConstant.EMAIL_ID));
			userObj.put(UserJsonConstants.CONTACT_NO, rs.getString(DBConstant.CONTACT_NO));
			userObj.put(UserJsonConstants.PASSWORD, rs.getString(DBConstant.PASSWORD));
			userObj.put(UserJsonConstants.REGISTRATION_DATE, rs.getDate(DBConstant.REG_DATE));
			userObj.remove(UserJsonConstants.ERROR_MSG);
			userObj.remove(UserJsonConstants.ERROR_CODE);
		} else {
			userObj.put(UserJsonConstants.ERROR_MSG, ValidationConstant.ERROR_MSG_INCORRECT_PASSWORD);
			userObj.put(UserJsonConstants.ERROR_CODE, ValidationConstant.ERROR_CODE_INCORRECT_PASSWORD);
		}
		userObj.put(UserJsonConstants.STATUS, loginStatus);
	}
}
