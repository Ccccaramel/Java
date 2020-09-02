package admin.edu.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.edu.dao.CoursePromotion;
import homepage.edu.dao.Course;

public class CoursePromotionService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	private CoursePromotion coursePromotion;
	public CoursePromotionService() {
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
	public int getCoursePromotionRow() {  //获取首页推荐的课程总数
		String sql="select count(*) count from course_promotion";
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
	
	public List<CoursePromotion> getAllCoursePromotionMessage(int startPage,int pageSize){  //返回指定页的首页推广课程信息
		String sql="SELECT c1.courseId as courseId,c2.courseName as courseName from course_promotion c1 "
				+ "JOIN course c2 ON c1.courseId=c2.courseId "
				+ "limit ?,?";
		PreparedStatement prep;
		ResultSet result;
		List<CoursePromotion> coursePromotionList=new ArrayList<CoursePromotion>();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, startPage);
			prep.setInt(2, pageSize);
			result=prep.executeQuery();
			while(result.next()) {
				CoursePromotion coursePromotion=new CoursePromotion();
				coursePromotion.setCourseId(result.getInt("courseId"));
				coursePromotion.setCourseName(result.getString("courseName"));
				coursePromotionList.add(coursePromotion);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coursePromotionList;
	}
	public List<Course> getAllCoursePromotionMessage(){  //返回指定页的首页推广课程信息
		String sql="SELECT c1.courseId as courseId,"
				+ "c2.courseName as courseName,"
				+ "c2.courseImgName as courseImgName "
				+ "from course_promotion c1 "
				+ "JOIN course c2 ON c1.courseId=c2.courseId";
		PreparedStatement prep;
		ResultSet result;
		List<Course> courseList=new ArrayList<Course>();
		try {
			prep=CONN.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()) {
				Course course=new Course();
				course.setCourseId(result.getInt("courseId"));
				course.setCourseName(result.getString("courseName"));
				course.setCourseImgName(result.getString("courseImgName"));
				courseList.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseList;
	}
	public boolean courseExist(int courseId) {  //检查指定课程是否已被推广
		String sql="select count(*) count from course_promotion where courseId=?";
		PreparedStatement prep;
		ResultSet result;
		boolean state=false;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			result=prep.executeQuery();
			result.next();
			if(result.getInt("count")==1) {
				state=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	
	public boolean addCoursePromotion(int courseId) {  //添加课程推广
		String sql="insert into course_promotion(courseId) values(?)";
		PreparedStatement prep;
		ResultSet result;
		boolean state=false;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			if(prep.executeUpdate()==1) {
				state=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	
	public boolean delCoursePromotion(int courseId) {  //删除课程推广
		String sql="delete from course_promotion where courseId=?";
		PreparedStatement prep;
		ResultSet result;
		boolean state=false;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			if(prep.executeUpdate()==1) {
				state=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
}
