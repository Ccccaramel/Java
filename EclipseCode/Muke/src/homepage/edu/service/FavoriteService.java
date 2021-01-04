package homepage.edu.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import homepage.edu.dao.Course;

public class FavoriteService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	
	public void connect() {  // 连接数据库
		try {
			CONN=DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public FavoriteService() {
		super();
		// TODO Auto-generated constructor stub
		this.connect();
	}
	
	public void addFavorite(int userId,int courseId) {
		String sql="insert into favorite(userId,courseId) VALUES(?,?) ON DUPLICATE KEY UPDATE userId=?,courseId=?";
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setInt(2, courseId);
			prep.setInt(3, userId);
			prep.setInt(4, courseId);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean checkFavorite(int userId,int courseId) {
		String sql="select count(*) count from favorite where userId=? and courseId=?";
		PreparedStatement prep;
		ResultSet result;
		boolean state=false;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setInt(2, courseId);
			result=prep.executeQuery();
			result.next();
			if(result.getInt("count")==1) {
				state=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("state:"+state);
		return state;
	}
	public void delFavorite(int userId,int courseId) {
		String sql="delete from favorite where userId=? and courseId=?";
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setInt(2, courseId);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
