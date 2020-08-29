package com.TravellingWorld.user;

import java.sql.SQLException;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TravellingWorld.common.resource.TransactionManager;
import com.TravellingWorld.common.validation.ValidationConstant;

import hibernate.HibernateDao;
import hibernate.table.MUser;

@SuppressWarnings("unchecked")
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public JSONObject registerUser(MUser user) throws SQLException {
		JSONObject userObj = new JSONObject();
		JSONArray errorArray = new JSONArray();
		JSONObject errorObj = null;
		
		boolean flag = false;
		if (userDao.isEmailIdAlreadyExits(user.getEmailId())) {
			errorObj = new JSONObject();
			errorObj.put(UserJsonConstants.ERROR_CODE, ValidationConstant.ERROR_CODE_EMAIL_ID_ALREDY_EXITS);
			errorObj.put(UserJsonConstants.ERROR_MSG, ValidationConstant.ERROR_MSG_EMAIL_ID_ALREDY_EXITS);
			errorArray.add(errorObj);
			flag = true;
		}
		
		if (userDao.isMobileAlreadyExits(user.getContactNo())) {
			errorObj = new JSONObject();
			errorObj.put(UserJsonConstants.ERROR_CODE, ValidationConstant.ERROR_CODE_CONTACT_NO_ALREDY_EXITS);
			errorObj.put(UserJsonConstants.ERROR_MSG, ValidationConstant.ERROR_MSG_CONTACT_NO_ALREDY_EXITS);
			errorArray.add(errorObj);
			flag = true;			
		}
		
		if (flag) {
			userObj.put(UserJsonConstants.ERROR_DATA, errorArray);
			userObj.put(UserJsonConstants.STATUS, false);
			System.out.println("User already exists");
		} else {
			user.setRegistrationDate(new Date());
			HibernateDao.insertIntoMUser(user);
			userObj.put(UserJsonConstants.STATUS, true);
		}

		return userObj;
	}

	@SuppressWarnings("null")
	public JSONObject loginUser(MUser user) throws SQLException {
		JSONObject userObj = new JSONObject();
		userObj.put(UserJsonConstants.STATUS, false);
		
		JSONObject errorObj = new JSONObject();
		errorObj.put(UserJsonConstants.ERROR_MSG, ValidationConstant.ERROR_MSG_NO_SUCH_USER);
		errorObj.put(UserJsonConstants.ERROR_CODE, ValidationConstant.ERROR_CODE_NO_SUCH_USER);

		JSONArray errorArray = new JSONArray();
		errorArray.add(errorObj);
		
		userObj.put(UserJsonConstants.ERROR_DATA, errorArray);
		try {
			return userDao.loginUser(user, userObj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userObj;
	}
}
