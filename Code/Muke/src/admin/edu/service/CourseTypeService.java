package admin.edu.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import admin.edu.dao.CourseType;

public class CourseTypeService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	public CourseTypeService() {
		super();
		// TODO Auto-generated constructor stub
		this.connect();
	}
	public void connect() {  // �������ݿ�
		try {
			CONN=DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int addNewCourseType(String courseType){  // ����¿γ�����
		String sql="insert into course_type(typeName) values('"+courseType+"')";
		Statement stmt;
		int result = 0;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeUpdate(sql);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;  // ������Ӱ�������(����0��Ϊ��ӳɹ�)
	}
	public List<CourseType> getcourseType(){  //��ȡ���пγ������Լ�id
		List<CourseType> courseTypeList=new ArrayList<>();
		String sql="select typeValue,typeName from course_type";
		Statement stmt;
		ResultSet result;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			while(result.next()) {
				CourseType courseType=new CourseType(result.getInt("typeValue"), result.getString("typeName"));
				courseTypeList.add(courseType);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseTypeList;
	}
	public List<CourseType> getCourseType(int amount){  //��ȡָ�������Ŀγ������Լ�id
		List<CourseType> courseTypeList=new ArrayList<>();
		String sql="select typeValue,typeName from course_type "
				+ "order by typeValue desc limit ?";
		PreparedStatement prep;
		ResultSet result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, amount);
			result=prep.executeQuery();
			while(result.next()) {
				CourseType courseType=new CourseType(result.getInt("typeValue"), result.getString("typeName"));
				courseTypeList.add(courseType);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseTypeList;
	}
	
	public List<CourseType> getCourseType(int startPage,int pageSize){  // ��ȡָ��ҳ���Ŀγ�����
		List<CourseType> courseTypeList=new ArrayList<>();
		String sql="select typeValue,typeName from course_type limit "+startPage+","+pageSize;
		Statement stmt;
		try {
			stmt=CONN.createStatement();
			ResultSet result=stmt.executeQuery(sql);
			while(result.next()) {
				int typeValue=result.getInt("typeValue");
				String typeName=result.getString("typeName");
				CourseType courseType=new CourseType(typeValue, typeName);
				courseTypeList.add(courseType);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseTypeList;
	}
	public int getCourseTypeRow(){  // ��ȡ�����ݵ�������
		String sql="select typeValue,typeName from course_type";
		int row=0;
		Statement stmt;
		try {
			stmt=CONN.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet result=stmt.executeQuery(sql);
			result.last(); 
			row = result.getRow();
			System.out.println(",row:"+row);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	
	public void setAsDefault(int id) {  // ɾ��ĳ�γ�����֮ǰ�����ڸ����͵Ŀγ̸���Ϊ"δ֪"����
		String sql="update course set courseClass=1010 where courseClass="+id;
		Statement stmt;
		ResultSet result=null;
		try {
			stmt=CONN.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int delCourseType(int id) {  // ɾ��ָ���γ�����
		String sql="delete from course_type where typeValue="+id;
		Statement stmt;
		int result = 0;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public String getcourseType(int typeValue){  //��ȡָ���γ�����(����)�����ĺ���
		String sql="select typeName from course_type where typeValue="+typeValue;
		Statement stmt;
		ResultSet result;
		String typeName=null;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			result.next();
			typeName=result.getString("typeName");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return typeName;
	}
}
