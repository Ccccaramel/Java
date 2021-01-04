package admin.edu.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.edu.dao.GMPower;

public class GMPowerService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	public GMPowerService() {
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
	public List<GMPower> getGMPowerMessage(int powerNumber){  //获取指定权限等级以下的权限级别
		String sql="select powerNumber,powerClass from gm_power where powerNumber<?";
		PreparedStatement prep;
		ResultSet result;
		List<GMPower> gmPowerList=new ArrayList<>();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, powerNumber);
			result=prep.executeQuery();
			while(result.next()) {
				GMPower gmPower=new GMPower();
				gmPower.setPowerNumber(result.getInt("powerNumber"));
				gmPower.setPowerClass(result.getString("powerClass"));
				gmPowerList.add(gmPower);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gmPowerList;
	}
}
