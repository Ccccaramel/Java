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
			Class.forName(JDBC_DRIVER);  // ע�� jdbc ����
			System.out.println("�������ݿ�...");
			CONN = DriverManager.getConnection(DB_URL, USER, PASS); // �������ݿ�
			System.out.println("ʵ���� Statement ����...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void select() {  // ��ѯ
		System.out.println("��ʼ��ѯ!");
		String sql = "select IDCard,Name,age from test";
		Statement stmt;
		try {
			stmt = CONN.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int IDCard = rs.getInt("IDCard");
				String Name = rs.getString("Name");
				int age = rs.getInt("age");
				System.out.println("ѧ��:" + IDCard + ",����:" + Name + ",����:" + age);
			}
			rs.close();
			stmt.close();
			System.out.println("��ѯ�ɹ�!");
		} catch (SQLException e) {
			e.printStackTrace();
		} // ʵ���� Statement ����
	}

	public static void insert() {  // ����
		System.out.println("��ʼ����!");
		String sql = "insert into test(Name,age) values('" + "Cara" + "'," + 16 + ")";
		PreparedStatement insStmt;
		try {
			insStmt = (PreparedStatement) CONN.prepareStatement(sql);
			insStmt.executeUpdate();
			insStmt.close();
			System.out.println("����ɹ�!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void update(String name,int age) {  // ����
		System.out.println("��ʼ����!");
		 String sql="update test set age=? where Name=?";
		PreparedStatement upStmt;
		try {
			upStmt = (PreparedStatement) CONN.prepareStatement(sql);
			upStmt.setInt(1, age);
			upStmt.setString(2, name);
			upStmt.executeUpdate();
			System.out.println("���³ɹ�!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void delete(String name) {  // ɾ��
		System.out.println("��ʼɾ��!");
		String sql = "delete from test where Name=?";
		PreparedStatement delStmt;
		try {
			delStmt = (PreparedStatement) CONN.prepareStatement(sql);
			delStmt.setString(1, name);
			delStmt.executeUpdate();  // �����˴���
			delStmt.close();
			System.out.println("ɾ���ɹ�!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
