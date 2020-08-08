package com.TravellingWorld.common.resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Transaction {
		
	public static Connection getConection() {
		Connection con = null;
		try {
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/myapplication", "root", "saudu@123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static Session getHibernateSession() {
		SessionFactory factory = null;
		Session session = null;
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			
			factory = cfg.buildSessionFactory();
			session = factory.openSession();
		} catch (Exception e) {
			System.out.println("Error occured while creating transaction : "  +e);
		}

		return session;
	}
}
