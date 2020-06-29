package homepage.edu.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import homepage.edu.dao.Course;

public class FeedBackService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	private Course course=new Course();
	
	public void connect() {  // 连接数据库
		try {
			CONN=DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public FeedBackService() {
		super();
		// TODO Auto-generated constructor stub
		this.connect();
	}
	public void addFeedBackService(int userId,String ideaContent) {
		String sql="insert into suggestions(userId,ideaContent) "
				+ "VALUES(?,?)";
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setString(2, ideaContent);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
