package homepage.edu.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import com.google.gson.Gson;

public class UserResultsService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	
	public UserResultsService() {
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
	public void saveUserTest(Map<Object,Object> data,int userId) {
		int usertotalScore=0;
		int testId= Integer.parseInt((String)data.get("testId"));
		int courseId=Integer.parseInt((String) data.get("courseId"));
		int totalScore=Integer.parseInt((String) data.get("hTotalScore"));
		int passLine=Integer.parseInt((String) data.get("hPassLine"));
		int pass=0;
		String answerSheetId=this.getAnswerSheetId();
		for(int i=1;;i++) {
			if( data.get("problemId-"+i)==null) {
				break;
			}else {
				int problemId=Integer.parseInt((String) data.get("problemId-"+i));
				String userKey=(String) data.get("question-"+i+"-user-key");
				int score=this.Revise(problemId, userKey);
				usertotalScore+=score;
				this.saveAnswerSheet(answerSheetId, userId, problemId, userKey, score);  //保存答题卡
			}
			if(usertotalScore>=passLine) {
				pass=1;
			}
		}
		this.saveGrades(answerSheetId, userId, courseId, testId, usertotalScore, totalScore, pass);//保存成绩
	}
	private void saveAnswerSheet(String answerSheetId,int userId,int problemId,String userKey,int score) {
		String sql="insert into user_results(answerSheetId,userId,questionId,userKey,userScore) "
				+ "VALUES(?,?,?,?,?)";
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setString(1, answerSheetId);
			prep.setInt(2, userId);
			prep.setInt(3, problemId);
			prep.setString(4, userKey);
			prep.setInt(5, score);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int Revise(int questionId,String userKey) {  //将题目id和用户的答案输入与正确答案对比并返回分值
		String sql="select score from test_question where questionId=? and rightKey=?";
		PreparedStatement prep;
		ResultSet result;
		int score=0;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, questionId);
			prep.setString(2, userKey);
			result=prep.executeQuery();
			while(result.next()) {
				score=result.getInt("score");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return score; 
	}
	private String getAnswerSheetId() {
		DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar=Calendar.getInstance();
		int num=1000+(int)(Math.random()*(9000));
		String name=df.format(calendar.getTime());
		return name;
	}
	
	public void saveGrades(String answerSheetId,int userId,int courseId,int testId,int usertotalScore,int totalScore,int pass) {
		String sql="insert into user_achievement(answerSheetId,userId,courseId,testId,userAchievement,effectiveness,totalScore,pass) "
				+ "VALUES(?,?,?,?,?,\"1\",?,?)";
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setString(1, answerSheetId);
			prep.setInt(2, userId);
			prep.setInt(3, courseId);
			prep.setInt(4, testId);
			prep.setInt(5, usertotalScore);
			prep.setInt(6, totalScore);
			prep.setInt(7, pass);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getUserKey(String answerSheetId,int questionId) {
		String sql="SELECT userKey from user_results "
				+ "WHERE answerSheetId=? AND questionId=?";
		PreparedStatement prep;
		ResultSet result;
		String userKey=null;
		try {
			System.out.println(answerSheetId+"+"+questionId);
			prep=CONN.prepareStatement(sql);
			prep.setString(1, answerSheetId);
			prep.setInt(2, questionId);
			result=prep.executeQuery();
			result.next();
			userKey=result.getString("userKey");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userKey;
	}
}
