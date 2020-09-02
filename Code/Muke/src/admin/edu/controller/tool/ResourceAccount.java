package admin.edu.controller.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourceAccount {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static Connection CONN=null;
	
	public void connect() {  // 连接数据库
		try {
			CONN=DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResourceAccount() {
		super();
		// TODO Auto-generated constructor stub
		this.connect();
	}
	
	public String getStateStr(int accountNumber) {
		String sql="select accountClass from resource_account where accountNumber=?";
		PreparedStatement prep;
		ResultSet result=null;
		String state=null;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, accountNumber);
			result=prep.executeQuery();
			result.next();
			state=result.getString("accountClass");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return state;
	}
}
