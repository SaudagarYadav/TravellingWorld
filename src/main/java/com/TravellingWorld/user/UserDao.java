package com.TravellingWorld.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.TravellingWorld.validation.GenTool;
import com.TravellingWorld.validation.ValidationConstant;

import hibernate.HibernateDao;
import hibernate.table.DBConstant;
import hibernate.table.MUser;

@Service
public class UserDao {

	SessionFactory factory = null;
	Session session = null;
	HibernateDao hibernateDao = new HibernateDao();

	@SuppressWarnings("unchecked")
	public JSONObject registerUser(MUser user) throws SQLException {
		
		JSONObject userObj = new JSONObject();
		if (isUserAlreadyExits(user)) {
			user.setRegistrationDate(new Date());
			hibernateDao.insertIntoMUser(user);
		} else {
			userObj.put(UserJsonConstants.ERROR_MSG, ValidationConstant.ERROR_MSG_USER_ALREDY_EXITS);
			userObj.put(UserJsonConstants.ERROR_CODE, ValidationConstant.ERROR_CODE_USER_ALREDY_EXITS);
			userObj.put(UserJsonConstants.STATUS, false);
		}
		
		return userObj;
	}

	private boolean isUserAlreadyExits(MUser user) throws SQLException {

		Connection con = null;
		int count = 1;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myapplication", "root", "saudu@123");
			PreparedStatement pred = con.prepareStatement(UserQuery.getSameUserNameCount);
			pred.setString(count++, user.getEmailId());
			pred.setString(count++, user.getContactNo());
			ResultSet rs = pred.executeQuery();
			while (rs.next()) {
				if (rs.getInt("count")>0) {
					return false;
				} else {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		
		return false;

	}

	@SuppressWarnings("unchecked")
	public JSONObject loginUser(MUser user) throws SQLException {
		Connection con = null;
		int count = 1;
		JSONObject userObj = new JSONObject();
		userObj.put(UserJsonConstants.ERROR_MSG, ValidationConstant.ERROR_MSG_NO_SUCH_USER);
		userObj.put(UserJsonConstants.STATUS, false);
		userObj.put(UserJsonConstants.ERROR_CODE, ValidationConstant.ERROR_CODE_NO_SUCH_USER);
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myapplication", "root", "saudu@123");
			PreparedStatement pred = con.prepareStatement(UserQuery.getUserData);
			pred.setString(count++, user.getUserName());
			pred.setString(count++, user.getUserName());
			ResultSet rs = pred.executeQuery();
			while (rs.next()) {
				if (user.getPassword().equals(rs.getString(DBConstant.PASSWORD))) {
					GenTool.userResultsetToJson(userObj, rs, true);
				} else {
					GenTool.userResultsetToJson(userObj, rs, false);
				}
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return userObj;
	}

}
