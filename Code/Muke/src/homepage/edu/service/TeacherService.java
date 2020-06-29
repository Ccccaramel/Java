package homepage.edu.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import homepage.edu.dao.Teacher;

public class TeacherService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	
	private Teacher teacher=new Teacher(); 
	
	public TeacherService() {
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
	
	public static String md5(String str) {  // md5 加密
		byte[] digest = null;
		MessageDigest md5;
		String md5Str=null;
		try {
			md5 = MessageDigest.getInstance("md5");
			digest=md5.digest(str.getBytes("utf-8"));			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md5Str=new BigInteger(1,digest).toString(16);
		return md5Str;
	}
	
	public boolean registered(String teacherName,String teachersSchool,String teacherIDcard,String teacherTel,String teacherEmail,String teacherQualification,String teacherPassword,String teachersSchoolEmail){  //注册
		teacherPassword=md5(teacherPassword);
		String sql="insert into "
				+ "teacher_basic_information"
				+ "(teacherName,teachersSchool,teacherIDcard,teacherTel,teacherEmail,teacherQualification,teacherNewPassword,teachersSchoolEmail) "
				+ "values"
				+ "('"+teacherName+"','"+teachersSchool+"','"+teacherIDcard+"','"+teacherTel+"','"+teacherEmail+"','"+teacherQualification+"','"+teacherPassword+"','"+teachersSchoolEmail+"')";
		Statement stmt;
		boolean result=false;		
		try {
			stmt=CONN.createStatement();
			if(stmt.executeUpdate(sql)==1) {
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result:"+result);
		return result;
	}
	public Boolean EmailLogin(String email,String password) {  //email登录
		password=md5(password);
		String sql="SELECT teacherId,teacherAccountStatus from teacher_basic_information WHERE teacherEmail=\""+email+"\" AND teacherNewPassword=\""+password+"\"";
		Statement stmt;
		ResultSet result=null;
		boolean state=false;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			this.teacher=new Teacher();
			while(result.next()){
				state=true;
				this.teacher.setTeacherId(result.getInt("teacherId"));
				this.teacher.setTeacherAccountStatus(result.getInt("teacherAccountStatus"));
				this.teacher.setPassword(password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	public Boolean IdLogin(int id,String password) {  //id登录
		password=md5(password);
		String sql="SELECT teacherId,teacherAccountStatus from teacher_basic_information WHERE teacherId="+id+" AND teacherNewPassword=\""+password+"\"";
		Statement stmt;
		ResultSet result=null;
		boolean state=false;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			this.teacher=new Teacher();
			while(result.next()){
				state=true;
				this.teacher.setTeacherId(result.getInt("teacherId"));
				this.teacher.setTeacherAccountStatus(result.getInt("teacherAccountStatus"));
				this.teacher.setPassword(password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	public Boolean TelLogin(String tel,String password) {  //tel登录
		password=md5(password);
		String sql="SELECT teacherId,teacherAccountStatus from teacher_basic_information WHERE teacherTel=\""+tel+"\" AND teacherNewPassword=\""+password+"\"";
		Statement stmt;
		ResultSet result=null;
		boolean state=false;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			this.teacher=new Teacher();
			while(result.next()){
				state=true;
				this.teacher.setTeacherId(result.getInt("teacherId"));
				this.teacher.setTeacherAccountStatus(result.getInt("teacherAccountStatus"));
				this.teacher.setPassword(password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	public int getAccountStatus() {  //获取当前教师的账号状态-数字
		return this.teacher.getTeacherAccountStatus();
	}
	
	public String getAccountStatusStr(int teacherId) {  //获取当前教师的账号状态-中文
		String sql="SELECT accountClass from teacher_basic_information JOIN account_status ON teacherAccountStatus=accountNumber WHERE teacherId="+teacherId;
		Statement stmt;
		ResultSet result=null;
		String accountClass=null;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			result.next();
			accountClass=result.getString("accountClass");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountClass;
	}

	public Teacher getTeacher() {  //获取当前教师的对象
		return teacher;
	}

	public void setTeacher(Teacher teacher) {  //初始化教师对象
		this.teacher = teacher;
	}
	
	public List<Teacher> getAllTeacher(String key,int startPage,int pageSize){  //获取符合要求的教师的所有信息
		String sql="select "
				+ "teacherId,teacherName,teacherTel,teacherEmail,teacherIDcard,teacherQualification,teachersSchool,teachersSchoolEmail,teacherAccountStatus "
				+ "from teacher_basic_information "
				+ "WHERE teacherName LIKE \"%"+key+"%\" OR teacherId LIKE \"%"+key+"%\" OR teachersSchool LIKE \"%"+key+"%\" OR teacherIDCard LIKE \"%"+key+"%\" OR teacherTel LIKE \"%"+key+"%\" OR teacherEmail LIKE \"%"+key+"%\" OR teacherQualification LIKE \"%"+key+"%\" limit "+startPage+","+pageSize;
		Statement stmt;
		int teacherId,teacherAccountStatus;
		String teacherName,teacherTel,teacherEmail,teacherIDcard,teacherQualification,teachersSchool,teachersSchoolEmail,teacherAccountStatusStr;
		List<Teacher> teacherList=new ArrayList<>();
		try {
			stmt=CONN.createStatement();
			ResultSet result=stmt.executeQuery(sql);
			while(result.next()) {
				teacherId=result.getInt("teacherId");
				teacherName=result.getString("teacherName");
				teacherTel=result.getString("teacherTel");
				teacherEmail=result.getString("teacherEmail");
				teacherIDcard=result.getString("teacherIDcard");
				teacherQualification=result.getString("teacherQualification");
				teachersSchool=result.getString("teachersSchool");
				teachersSchoolEmail=result.getString("teachersSchoolEmail");
				teacherAccountStatus=result.getInt("teacherAccountStatus");
				teacherAccountStatusStr=this.getAccountStatusStr(teacherId);
				Teacher teacher=new Teacher(teacherName, teacherId, teachersSchool, teacherIDcard, teacherTel, teacherEmail, teacherQualification, teachersSchoolEmail, teacherAccountStatus, teacherAccountStatusStr);
				teacherList.add(teacher);
				System.out.println("id:"+teacherId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacherList;
	}
	
	public int getTeacherTableRow() {  //获取数据教师表数据总条数
		String sql="select COUNT(*) count from teacher_basic_information";
		int row=0;
		ResultSet result;
		Statement stmt;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			result.next();
			row=result.getInt("count");
			System.out.println("row:"+row);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public void updateTeacherAccountStatus(int sign,int id) {  //管理员更改账号状态
		String sql="update teacher_basic_information set teacherAccountStatus="+sign+" where teacherId="+id;
		Statement stmt;
		try {
			stmt=CONN.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	public void updatePassword(int id,String teacherOldPassword,String teacherNewPassword) {  //更改密码
		teacherOldPassword=md5(teacherOldPassword);
		teacherNewPassword=md5(teacherNewPassword);
		String sql="update teacher_basic_information set teacherOldPassword=\""+teacherOldPassword+"\",teacherNewPassword=\""+teacherNewPassword+"\" where teacherId="+id;
		Statement stmt;
		try {
			stmt=CONN.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	public boolean checkNewPassword(int id,String teacherNewPassword){  //检查新密码与上一次密码是否相同
		teacherNewPassword=md5(teacherNewPassword);
		String sql="select count(*) count from teacher_basic_information where teacherId="+id+" and teacherOldPassword=\""+teacherNewPassword+"\"";
		Statement stmt;
		ResultSet result=null;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			result.next();
			if(result.getInt("count")!=1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateTeacherMessage(int id,String teacherEmail,String teachersSchool,String teacherTel,String teachersSchoolEmail) {  //管理员更改账号状态
		String sql="update teacher_basic_information set teacherEmail='"+teacherEmail+"',teachersSchool='"+teachersSchool+"',teacherTel='"+teacherTel+"',teachersSchoolEmail='"+teachersSchoolEmail+"' where teacherId="+id;
		Statement stmt; 
		boolean state=false;
		try {
			stmt=CONN.createStatement();
			stmt.executeUpdate(sql);
			state=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return state;
	}
	public Teacher getTeacherMessage(int id){  //获取指定id教师的基本信息
		String sql="select "
				+ "teacherId,teacherName,teachersSchool,teacherTel,teacherEmail,teacherQualification,teachersSchoolEmail "
				+ "from teacher_basic_information where teacherId="+id;
		Statement stmt;
		ResultSet result=null;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			result.next();
			this.teacher.setTeacherId(result.getInt("teacherId"));
			this.teacher.setTeacherName(result.getString("teacherName"));
			this.teacher.setTeachersSchool(result.getString("teachersSchool"));
			this.teacher.setTeacherTel(result.getString("teacherTel"));
			this.teacher.setTeacherEmail(result.getString("teacherEmail"));
			this.teacher.setTeacherQualification(result.getString("teacherQualification"));
			this.teacher.setTeachersSchoolEmail(result.getString("teachersSchoolEmail"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.teacher;
	}
	
	public String getTeacherName(int teacherId) {  //获取指定教师的名字
		String sql="select teacherName from teacher_basic_information "
				+ "where teacherId=?";
		String teacherName=null;
		ResultSet result;
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, teacherId);
			result=prep.executeQuery();
			result.next();
			teacherName=result.getString("teacherName");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacherName;
	}
}
