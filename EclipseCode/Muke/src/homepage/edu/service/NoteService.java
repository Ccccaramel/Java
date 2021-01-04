package homepage.edu.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import homepage.edu.dao.Note;

public class NoteService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	
	public void connect() {  // 连接数据库
		try {
			CONN=DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public NoteService() {
		super();
		// TODO Auto-generated constructor stub
		this.connect();
	}
	public void addNote(int courseId,int userId,int userType,String note) {
		String sql="insert into note(courseId,userId,userType,note) values(?,?,?,?)";
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setInt(2, userId);
			prep.setInt(3, userType);
			prep.setString(4, note);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getNoteRow(int courseId) {
		String sql="select count(*) count from note "
				+ "where courseId=?";
		PreparedStatement prep;
		ResultSet result;
		int row=0;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			result=prep.executeQuery();
			result.next();
			row=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public List<Note> getNote(int courseId,int startPage,int pageSize) {
		String sql="select note,userId,userType,sendingDate from note "
				+ "where courseId=? "
				+ "limit ?,?";
		PreparedStatement prep;
		ResultSet result;
		List<Note> notelist=new ArrayList<Note>();
		StudentService studentService=new StudentService();
		TeacherService teacherService=new TeacherService();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setInt(2, startPage);
			prep.setInt(3, pageSize);
			result=prep.executeQuery();
			while(result.next()) {
				Note note=new Note();
				int userId=result.getInt("userId");
				int userType=result.getInt("userType");
				String userName=null;
				if(userType==0) {
					userName=studentService.getStudentName(userId);
				}else {
					userName=teacherService.getTeacherName(userId);
				}
				note.setUserName(userName);
				note.setUserType(userType);
				note.setNote(result.getString("note"));
				note.setSendingDate(result.getString("sendingDate"));
				notelist.add(note);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notelist;
	}
}
