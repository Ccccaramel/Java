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

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import homepage.edu.dao.Student;
import homepage.edu.dao.Teacher;

public class StudentService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	
	private Student user=new Student(); 
	
	public StudentService() {
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
	public boolean registered(String userEmail,String password) {  //注册
		String sql="insert into "
				+ "user_basic_information(userEmail,userNewPassword) "
				+ "values(?,?)";
		PreparedStatement prep;
		ResultSet result;
		int row=0;
		boolean state=false;
		String passwordmd5=StudentService.md5(password);
		try {
			prep=CONN.prepareStatement(sql);
			prep.setString(1,userEmail);
			prep.setString(2, passwordmd5);
			row=prep.executeUpdate();
			if(row==1) {
				state=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	public Boolean EmailLogin(String email,String password) {  //email登录
		String passwordmd5=md5(password);
		String sql="SELECT userId,userAccountStatus from user_basic_information "
				+ "WHERE userEmail=? AND userNewPassword=?";
		PreparedStatement prep;
		ResultSet result=null;
		boolean state=false;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setString(1, email);
			prep.setString(2, passwordmd5);
			result=prep.executeQuery();
			while(result.next()){
				state=true;
				this.user.setUserId(result.getInt("userId"));
				this.user.setUserAccountStatus(result.getInt("userAccountStatus"));
				this.user.setUserNewPassword(password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	public Boolean TelLogin(String tel,String password) {  //tel登录
		String passwordmd5=md5(password);
		String sql="SELECT userId,userAccountStatus from user_basic_information "
				+ "WHERE userTel=? AND userNewPassword=?";
		PreparedStatement prep;
		ResultSet result=null;
		boolean state=false;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setString(1, tel);
			prep.setString(2, passwordmd5);
			result=prep.executeQuery();
			while(result.next()){
				state=true;
				this.user.setUserId(result.getInt("userId"));
				this.user.setUserAccountStatus(result.getInt("userAccountStatus"));
				this.user.setUserNewPassword(password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	public Boolean IdLogin(int userId,String userNewPassword) {  //id登录
		String passwordmd5=md5(userNewPassword);
		String sql="SELECT userId,userAccountStatus from user_basic_information "
				+ "WHERE userId=? AND userNewPassword=?";
		PreparedStatement prep;
		ResultSet result=null;
		boolean state=false;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setString(2, passwordmd5);
			result=prep.executeQuery();
			while(result.next()){
				state=true;
				this.user.setUserId(result.getInt("userId"));
				this.user.setUserAccountStatus(result.getInt("userAccountStatus"));
				this.user.setUserNewPassword(userNewPassword);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	public Student getUserMessage(int userId){  //获取登录用户信息
		String sql="SELECT userName,userEmail,userTel,userSex,userBirth from user_basic_information WHERE userId=?";
		PreparedStatement prep;
		ResultSet result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, userId);
			result=prep.executeQuery();
			result.next();
			this.user.setUserId(userId);
			this.user.setUserName(result.getString("userName"));
			this.user.setUserEmail(result.getString("userEmail"));
			this.user.setUserTel(result.getString("userTel"));
			this.user.setUserSex(result.getString("userSex"));
			this.user.setUserBirth(result.getString("userBirth"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.user;
	}
	public void updateUserMessage(String userName,String userEmail,String userTel,String userSex,String userBirth,int userId){  //用户更新信息
		String sql="update user_basic_information "
				+ "set userName=?,userEmail=?,userTel=?,userSex=?,userBirth=? "
				+ "WHERE userId=?";
		PreparedStatement prep;
		int row;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setString(1, userName);
			prep.setString(2, userEmail);
			prep.setString(3, userTel);
			prep.setString(4, userSex);
			prep.setString(5, userBirth);
			prep.setInt(6, userId);
			row=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getAccountStatus() {  //获取当前用户的账号状态-数字
		return this.user.getUserAccountStatus();
	}
	public Student getUser() {  //获取当前教师的对象
		return this.user;
	}

	public void setUser(Student user) {  //初始化教师对象
		this.user = user;
	}
	public boolean checkNewPassword(int id,String userNewPassword){  //检查新密码与上一次密码是否相同
		userNewPassword=md5(userNewPassword);
		String sql="select count(*) count from user_basic_information "
				+ "where userId=? and userOldPassword=?";
		PreparedStatement prep;
		ResultSet result=null;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, id);
			prep.setString(2, userNewPassword);
			result=prep.executeQuery();
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
	public void updatePassword(int id,String userOldPassword,String userNewPassword) {  //更改密码
		userOldPassword=md5(userOldPassword);
		userNewPassword=md5(userNewPassword);
		String sql="update user_basic_information "
				+ "set userOldPassword=?,userNewPassword=? "
				+ "where userId=?";
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setString(1, userOldPassword);
			prep.setString(2, userNewPassword);
			prep.setInt(3, id);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	public int getStudentTableRow(int startPage,int pageSize,String key) {  //获取表的数据量
		String sql="select count(*) count from user_basic_information "
				+ "where userName like ? "
				+ "or userEmail like ? "
				+ "or userTel like ? "
				+ "or userSex like ? "
				+ "or userBirth like ?";
		ResultSet result;
		PreparedStatement prep;
		int row=0;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setString(1,  '%'+key+'%');
			prep.setString(2, '%'+key+'%');
			prep.setString(3, '%'+key+'%');
			prep.setString(4, '%'+key+'%');
			prep.setString(5, '%'+key+'%');
			result=prep.executeQuery();
			result.next();
			row=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public List<Student> getAllStudentMessage(int startPage,int pageSize,String key) {  //获取指定用户的信息
		String sql="select u.userId as userId,"
				+ "u.userName as userName,"
				+ "u.userEmail as userEmail,"
				+ "u.userTel as userTel,"
				+ "u.userSex as userSex,"
				+ "u.userBirth as userBirth,"
				+ "u.userAccountStatus as userAccountStatus,"
				+ "a.accountClass as accountClass "
				+ "from user_basic_information u "
				+ "join account_status a "
				+ "on u.userAccountStatus=a.accountNumber "
				+ "where u.userName like ? "
				+ "or u.userEmail like ? "
				+ "or u.userTel like ? "
				+ "or u.userSex like ? "
				+ "or u.userBirth like ? "
				+ "limit ?,?";
		ResultSet result;
		PreparedStatement prep;
		List<Student> studentList=new ArrayList<>();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setString(1, '%'+key+'%');
			prep.setString(2, '%'+key+'%');
			prep.setString(3, '%'+key+'%');
			prep.setString(4, '%'+key+'%');
			prep.setString(5, '%'+key+'%');
			prep.setInt(6, startPage);
			prep.setInt(7, pageSize);
			result=prep.executeQuery();
			while(result.next()) {
				Student student=new Student();
				student.setUserId(result.getInt("userId"));
				student.setUserName(result.getString("userName"));
				student.setUserEmail(result.getString("userEmail"));
				student.setUserTel(result.getString("userTel"));
				student.setUserSex(result.getString("userSex"));
				student.setUserBirth(result.getString("userBirth"));
				student.setUserAccountStatus(result.getInt("userAccountStatus"));
				student.setAccountClass(result.getString("accountClass"));
				studentList.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentList;
	}
	
	public void updateStudentAccountStatus(int sign,int id) {  //更改指定用户的账号状态
		String sql="update user_basic_information "
				+ "set userAccountStatus=? "
				+ "where userId=?";
		ResultSet result;
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, sign);
			prep.setInt(2, id);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getStudentAccountStr(int id) {  //获取指定用户的账号状态
		String sql="select a.accountClass as accountClass "
				+ "from user_basic_information u "
				+ "join account_status a "
				+ "on u.userAccountStatus=a.accountNumber "
				+ "where u.userId=?";
		ResultSet result;
		PreparedStatement prep;
		String accountClass = null;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, id);
			result=prep.executeQuery();
			result.next();
			accountClass=result.getString("accountClass");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountClass;
	}
	public String getStudentName(int userId) {  //获取指定用户的昵称
		String sql="select userName from user_basic_information "
				+ "where userId=?";
		ResultSet result;
		PreparedStatement prep;
		String userName=null;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, userId);
			result=prep.executeQuery();
			result.next();
			userName=result.getString("userName");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userName;
	}
}
