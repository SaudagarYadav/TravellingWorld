package com.TravellingWorld.user;

import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hibernate.table.MUser;


@RestController
@RequestMapping("/api/v2/user")
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping 
	public JSONArray getBikelist() throws SQLException{
		System.out.println("getbike");
		return null;
	}
	
	@PostMapping 
	@ResponseStatus(HttpStatus.OK)
	public JSONObject registerNewUser(@RequestBody MUser user) throws SQLException {
		System.out.println("registration started");
		return userDao.registerUser(user);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JSONObject loginUser(@RequestBody MUser user) throws SQLException {
		System.out.println("Login started");
		return userDao.loginUser(user);
	}
}
