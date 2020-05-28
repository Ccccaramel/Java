package ding.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaMySQL {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/xuesi";
	static final String USER="root";
	static final String PASS="root";
	public static void main(String[] args) {
		// TODO Auto-generated method stub   ?useSSL=false&serverTimezone=UTC
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("连接数据库...");
			conn=DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("实例化 Statement 对象...");
			stmt=conn.createStatement();
			String sql;
			sql="select courseName,courseId,courseClass from course";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				String courseName=rs.getString("courseName");
				int courseId=rs.getInt("courseId");
				String courseClass=rs.getString("courseClass");
				
				System.out.println("课程名称:"+courseName+
						",课程ID:"+courseId+
						",课程类型:"+courseClass);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			try {
				if(stmt!=null) {
					stmt.close();
				}
			}catch (SQLException e) {
				// TODO: handle exception
			}
			try {
				if(conn!=null) {
					conn.close();
				}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println("Bye!");
	}

}
