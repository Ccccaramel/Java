package ding.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.xdevapi.PreparableStatement;

public class JavaMySQL {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/ding?useSSL=false&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "root";
	static Connection CONN = null;

	public static void main(String[] args) {
		connect();
		
		select();
		insert();
		select();
		update("Tom",20);
		select();
		delete("Cara");
		select();

		try {
			CONN.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Bye!");
	}

	public static void connect() {
		try {
			Class.forName(JDBC_DRIVER);  // 注册 jdbc 驱动
			System.out.println("连接数据库...");
			CONN = DriverManager.getConnection(DB_URL, USER, PASS); // 连接数据库
			System.out.println("实例化 Statement 对象...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void select() {  // 查询
		System.out.println("开始查询!");
		String sql = "select IDCard,Name,age from test";
		Statement stmt;
		try {
			stmt = CONN.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int IDCard = rs.getInt("IDCard");
				String Name = rs.getString("Name");
				int age = rs.getInt("age");
				System.out.println("学号:" + IDCard + ",姓名:" + Name + ",年龄:" + age);
			}
			rs.close();
			stmt.close();
			System.out.println("查询成功!");
		} catch (SQLException e) {
			e.printStackTrace();
		} // 实例化 Statement 对象
	}

	public static void insert() {  // 插入
		System.out.println("开始插入!");
		String sql = "insert into test(Name,age) values('" + "Cara" + "'," + 16 + ")";
		PreparedStatement insStmt;
		try {
			insStmt = (PreparedStatement) CONN.prepareStatement(sql);
			insStmt.executeUpdate();
			insStmt.close();
			System.out.println("插入成功!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void update(String name,int age) {  // 更新
		System.out.println("开始更新!");
		 String sql="update test set age=? where Name=?";
		PreparedStatement upStmt;
		try {
			upStmt = (PreparedStatement) CONN.prepareStatement(sql);
			upStmt.setInt(1, age);
			upStmt.setString(2, name);
			upStmt.executeUpdate();
			System.out.println("更新成功!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void delete(String name) {  // 删除
		System.out.println("开始删除!");
		String sql = "delete from test where Name=?";
		PreparedStatement delStmt;
		try {
			delStmt = (PreparedStatement) CONN.prepareStatement(sql);
			delStmt.setString(1, name);
			delStmt.executeUpdate();  // 别忘了此行
			delStmt.close();
			System.out.println("删除成功!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
