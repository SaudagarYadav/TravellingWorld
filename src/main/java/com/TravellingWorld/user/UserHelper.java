package com.TravellingWorld.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.TravellingWorld.common.validation.ValidationConstant;

import hibernate.table.DBConstant;

public class UserHelper {

	@SuppressWarnings("unchecked")
	public static void userResultsetToJson(JSONObject userObj, ResultSet rs, boolean loginStatus) throws SQLException {
		if (loginStatus){
			JSONObject userDataObj = new JSONObject();
			userDataObj.put(UserJsonConstants.USER_PID, rs.getString(DBConstant.USER_PID));
			userDataObj.put(UserJsonConstants.NAME, rs.getString(DBConstant.NAME));
			userDataObj.put(UserJsonConstants.EMAIL_ID, rs.getString(DBConstant.EMAIL_ID));
			userDataObj.put(UserJsonConstants.CONTACT_NO, rs.getString(DBConstant.CONTACT_NO));
			userDataObj.put(UserJsonConstants.PASSWORD, rs.getString(DBConstant.PASSWORD));
			userDataObj.put(UserJsonConstants.REGISTRATION_DATE, rs.getDate(DBConstant.REG_DATE));
			userDataObj.remove(UserJsonConstants.ERROR_MSG);
			userDataObj.remove(UserJsonConstants.ERROR_CODE);
			userObj.put("userData", userDataObj);
		} else {
			JSONArray errorArray = new JSONArray();
			JSONObject errorObj = new JSONObject();
			errorObj.put(UserJsonConstants.ERROR_MSG, ValidationConstant.ERROR_MSG_INCORRECT_PASSWORD);
			errorObj.put(UserJsonConstants.ERROR_CODE, ValidationConstant.ERROR_CODE_INCORRECT_PASSWORD);
			errorArray.add(errorObj);
			errorArray.add(errorObj);
			userObj.replace(UserJsonConstants.ERROR_DATA, errorArray);
		}
		userObj.put(UserJsonConstants.STATUS, loginStatus);
		System.out.println("Login data :" + userObj);
	}
}
