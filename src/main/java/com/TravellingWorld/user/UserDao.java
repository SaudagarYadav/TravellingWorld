package com.TravellingWorld.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.TravellingWorld.common.resource.TransactionManager;

import hibernate.HibernateDao;
import hibernate.table.DBConstant;
import hibernate.table.MUser;

@Service
public class UserDao {
	
	SessionFactory factory = null;
	Session session = null;
	HibernateDao hibernateDao = new HibernateDao();
	static TransactionManager transactionManager = null;
	
	static {
		transactionManager = TransactionManager.getInstance();
	}
	
	public boolean isMobileAlreadyExits(String mobileNumber) throws SQLException {
		Connection con = null;
		int count = 1;
		try {
			con = transactionManager.getConection();
			PreparedStatement pred = con.prepareStatement(UserQuery.getMobileCount);
			pred.setString(count++, mobileNumber);
			ResultSet rs = pred.executeQuery();
			while (rs.next()) {
				if (rs.getInt("count")>0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}
	
	public boolean isEmailIdAlreadyExits(String emailId) throws SQLException {
		Connection con = null;
		int count = 1;
		try {
			con = transactionManager.getConection();
			PreparedStatement pred = con.prepareStatement(UserQuery.getEmailIdCount);
			pred.setString(count++, emailId);
			ResultSet rs = pred.executeQuery();
			while (rs.next()) {
				if (rs.getInt("count")>0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}

	public JSONObject loginUser(MUser user, JSONObject userObj) throws SQLException {
		Connection con = null;
		int count = 1;
		try {
			con = transactionManager.getConection();
			PreparedStatement pred = con.prepareStatement(UserQuery.getUserData);
			pred.setString(count++, user.getUserName());
			pred.setString(count++, user.getUserName());
			ResultSet rs = pred.executeQuery();
			while (rs.next()) {
				UserHelper.userResultsetToJson(userObj, rs, user.getPassword().equals(rs.getString(DBConstant.PASSWORD)));
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
