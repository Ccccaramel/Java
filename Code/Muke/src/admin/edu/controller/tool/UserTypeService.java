package admin.edu.controller.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

public class UserTypeService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	public UserTypeService() {
		super();
		// TODO Auto-generated constructor stub
		this.connect();
	}
	public void connect() {  // 连接数据库
		try {
			CONN=DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getUserType(int typeValue) {
		String sql="select typeName from user_type where typeValue=?";
		PreparedStatement prep;
		ResultSet result;
		String typeName=null;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, typeValue);
			result=prep.executeQuery();
			result.next();
			typeName=result.getString("typeName");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return typeName;
	}
}
