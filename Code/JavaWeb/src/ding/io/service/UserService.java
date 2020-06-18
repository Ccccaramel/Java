package ding.io.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List; 
import ding.io.dao.User;

public class UserService {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/ding?useSSL=false&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "root";
	static ResultSet resultset = null;
	static Connection CONN = null;
	
	public UserService() {
		super();
		this.connect();
		// TODO Auto-generated constructor stub
	}

	public void connect() {
		try {
//			Class.forName(JDBC_DRIVER);  // 注册 JDBC 驱动
			System.out.println("连接数据库...");
			CONN=DriverManager.getConnection(DB_URL,USER,PASS);  // 连接数据库
			System.out.println("实例化 Statement 对象...");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<User> select() {
		List<User> user=new ArrayList<>(); 
		
		String sql="select IDCard,Name,age,submissionDate from test";
		Statement stmt;
		try {
			stmt=CONN.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				int IDCard=rs.getInt("IDCArd");
				String Name=rs.getString("Name");
				int age=rs.getInt("age");
				String submissionDate=rs.getString("submissionDate");
				User u=new User(IDCard,Name,age,submissionDate);
				user.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
