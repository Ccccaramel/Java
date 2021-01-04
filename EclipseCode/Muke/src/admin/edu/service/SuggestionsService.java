package admin.edu.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.edu.dao.Admin;
import admin.edu.dao.Suggestions;

public class SuggestionsService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	private Admin user;
	public SuggestionsService() {
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
	public int getSuggestionsTableRow(){
		String sql="select count(*) count from suggestions";
		PreparedStatement prep;
		ResultSet result;
		int row=0;
		try {
			prep=CONN.prepareStatement(sql);
			result=prep.executeQuery();
			result.next();
			row=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block      
			e.printStackTrace();
		}
		return row;
	}
	public List<Suggestions> getAllSuggestions(){
		String sql="select ideaId,userId,ideaContent,sendingDate from suggestions";
		List<Suggestions> suggestionsList=new ArrayList<Suggestions>();
		ResultSet result;
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()) {
				Suggestions suggestions=new Suggestions();
				suggestions.setIdeaId(result.getInt("ideaId"));
				suggestions.setUserId(result.getInt("userId"));
				suggestions.setIdeaContent(result.getString("ideaContent"));
				suggestions.setSendingDate(result.getString("sendingDate"));
				suggestionsList.add(suggestions);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return suggestionsList;
	}
}
