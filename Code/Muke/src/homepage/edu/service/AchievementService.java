package homepage.edu.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import homepage.edu.dao.Achievement;

public class AchievementService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	public AchievementService() {
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
	public int getAppointUserAchievementRow(int userId){
		String sql="select count(*) count "
				+ "from user_achievement "
				+ "where userId=?";
		PreparedStatement prep;
		int row = 0;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, userId);
			ResultSet result = prep.executeQuery();
			result.next();
			row=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public List<Achievement> getAppointUserAchievement(int userId,int startPage,int pageSize){
		String sql="select u.courseId as courseId,"
				+ "u.testId as testId,"
				+ "u.userAchievement as userAchievement,"
				+ "u.totalScore as totalScore,"
				+ "u.effectiveness as effectiveness,"
				+ "u.submitTestDate as submitTestDate,"
				+ "u.answerSheetId as answerSheetId,"
				+ "e.typeName as pass "
				+ "from user_achievement u "
				+ "join evaluate e on u.pass=e.typeValue "
				+ "where u.userId=? "
				+ "limit ?,?";
		PreparedStatement prep;
		ResultSet result;
		List<Achievement> achievementList=new ArrayList<>();
		ItemBackService itemBackService=new ItemBackService();
		CourseService courseService=new CourseService();
		System.out.println("userId:"+userId+startPage+pageSize);
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setInt(2, startPage);
			prep.setInt(3, pageSize);
			result=prep.executeQuery();
			while(result.next()) {
				Achievement achievement=new Achievement();
				int courseId=result.getInt("courseId");
				
				achievement.setCourseId(courseId);
				achievement.setCourseName(courseService.getCourseName(courseId));
				int testId=result.getInt("testId");
				achievement.setTestId(testId);
				achievement.setTestName(itemBackService.getTestName(testId));
				achievement.setUserAchievement(result.getInt("userAchievement"));
				achievement.setTotalScore(result.getInt("totalScore"));
				achievement.setPass(result.getString("pass"));
				achievement.setSubmitTestDate(result.getString("submitTestDate"));
				achievement.setAnswerSheetId(result.getString("answerSheetId"));
				achievement.setEffectiveness(result.getInt("effectiveness"));
				achievementList.add(achievement);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return achievementList;
	}
}
