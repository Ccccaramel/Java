package homepage.edu.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.edu.controller.tool.ResourceAccount;
import admin.edu.controller.tool.UserTypeService;
import homepage.edu.dao.CourseResource;

public class CourseResourceService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	public CourseResourceService() {
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
	public void addCourseResource(String resourceName,int courseId,String linkAdd,int userId,int userType){
		String sql="insert into curriculum_resource(resourceName,courseId,linkAdd,userId,userType) VALUES(?,?,?,?,?)";
		PreparedStatement prep;
		ResultSet result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setString(1, resourceName);
			prep.setInt(2, courseId);
			prep.setString(3, linkAdd);
			prep.setInt(4, userId);
			prep.setInt(5, userType);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<CourseResource> getCourseResource(int userId,int userType,int startPage,int pageSize){
		String sql="select resourceId,resourceName,courseId,resourceAccount from curriculum_resource "
				+ "where userId=? and userType=? and resourceAccount!=3 "
				+ "limit ?,?";
		PreparedStatement prep;
		ResultSet result;
		List<CourseResource> courseResourceList=new ArrayList<>();
		ResourceAccount resourceAccount=new ResourceAccount();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setInt(2, userType);
			prep.setInt(3, startPage);
			prep.setInt(4, pageSize);
			result=prep.executeQuery();
			while(result.next()) {
				CourseResource courseResource=new CourseResource();
				courseResource.setResourceId(result.getInt("resourceId"));
				courseResource.setResourceName(result.getString("resourceName"));
				courseResource.setCourseId(result.getInt("courseId"));
				courseResource.setResourceAccount(result.getInt("resourceAccount"));
				courseResource.setResourceAccountStr(resourceAccount.getStateStr(result.getInt("resourceAccount")));
				courseResourceList.add(courseResource);				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseResourceList;
	}
	public int getAppointCourseResourceRow(int courseId,int userType){
		String sql="select count(*) count from curriculum_resource "
				+ "where courseId=? and userType=? and resourceAccount=1";
		PreparedStatement prep;
		ResultSet result;
		int row=0;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setInt(2, userType);
			result=prep.executeQuery();
			result.next();
			row=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public List<CourseResource> getAppointCourseResource(int courseId,int userType){
		String sql="select resourceId,resourceName,linkAdd from curriculum_resource "
				+ "where courseId=? and userType=? and resourceAccount=1";
		PreparedStatement prep;
		ResultSet result;
		List<CourseResource> courseResourceList=new ArrayList<>();
		ResourceAccount resourceAccount=new ResourceAccount();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setInt(2, userType);
			result=prep.executeQuery();
			while(result.next()) {
				CourseResource courseResource=new CourseResource();
				courseResource.setResourceId(result.getInt("resourceId"));
				courseResource.setResourceName(result.getString("resourceName"));
				courseResource.setLinkAdd(result.getString("linkAdd"));
				courseResourceList.add(courseResource);				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseResourceList;
	}
	public List<CourseResource> getAllCourseResource(int startPage,int pageSize,String key){  //获取所有课程资源的信息
		String sql="select c.resourceId as resourceId,"
				+ "c.resourceName as resourceName,"
				+ "c.courseId as courseId,"
				+ "c.userId as userId,"
				+ "c.userType as userType,"
				+ "r.accountClass as accountClass "
				+ "from curriculum_resource c "
				+ "join resource_account r on c.resourceAccount "
				+ "where c.resourceAccount=r.accountNumber and ("
				+ "c.resourceId like ? or "
				+ "c.resourceName like ? or "
				+ "c.courseId like ? or "
				+ "c.userId like ? "
				+ ") "
				+ "limit ?,?";
		PreparedStatement prep;
		ResultSet result;
		List<CourseResource> courseResourceList=new ArrayList<>();
		ResourceAccount resourceAccount=new ResourceAccount();
		UserTypeService userTypeService=new UserTypeService();
		TeacherService teacherService=new TeacherService();
		StudentService studentService=new StudentService();
		CourseService courseService=new CourseService();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setString(1, '%'+key+'%');
			prep.setString(2, '%'+key+'%');
			prep.setString(3, '%'+key+'%');
			prep.setString(4, '%'+key+'%');
			prep.setInt(5, startPage);
			prep.setInt(6, pageSize);
			result=prep.executeQuery();
			while(result.next()) {
				CourseResource courseResource=new CourseResource();
				courseResource.setResourceId(result.getInt("resourceId"));
				courseResource.setResourceName(result.getString("resourceName"));
				int courseId=result.getInt("courseId");
				courseResource.setCourseId(courseId);
				courseResource.setCourseName(courseService.getCourseName(courseId));
				int userId=result.getInt("userId");
				courseResource.setUserId(userId);
				int userType=result.getInt("userType");
				String userTypeStr=userTypeService.getUserType(userType);
				String userName=null;
				if(userTypeStr.equals("普通用户")) {
					userName=studentService.getStudentName(userId);
				}else if(userTypeStr.equals("教师")){
					userName=teacherService.getTeacherName(userId);
				}
				courseResource.setUserName(userName);
				courseResource.setUserTypeStr(userTypeStr);
				courseResource.setResourceAccountStr(result.getString("accountClass"));
				courseResourceList.add(courseResource);				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseResourceList;
	}
	public List<CourseResource> searchCourseResource(int startPage,int pageSize,String key){  //获取所有课程资源的信息
		String sql="select c1.resourceId as resourceId,"
				+ "c1.resourceName as resourceName,"
				+ "c1.courseId as courseId,"
				+ "c1.userId as userId,"
				+ "c1.userType as userType,"
				+ "c1.linkAdd as linkAdd,"
				+ "c2.courseName as courseName "
				+ "from curriculum_resource c1 "
				+ "join course c2 on c1.courseId=c2.courseId "
				+ "where ("
				+ "c1.resourceId like ? or "
				+ "c1.resourceName like ? or "
				+ "c2.courseName like ? ) "
				+ "and c1.resourceAccount=1 "
				+ "limit ?,?";
		PreparedStatement prep;
		ResultSet result;
		List<CourseResource> courseResourceList=new ArrayList<>();
		UserTypeService userTypeService=new UserTypeService();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setString(1, '%'+key+'%');
			prep.setString(2, '%'+key+'%');
			prep.setString(3, '%'+key+'%');
			prep.setInt(4, startPage);
			prep.setInt(5, pageSize);
			result=prep.executeQuery();
			while(result.next()) {
				CourseResource courseResource=new CourseResource();
				courseResource.setResourceId(result.getInt("resourceId"));
				courseResource.setResourceName(result.getString("resourceName"));
				courseResource.setCourseId(result.getInt("courseId"));
				courseResource.setCourseName(result.getString("courseName"));
				int userId=result.getInt("userId");
				courseResource.setUserId(userId);
				int userType=result.getInt("userType");
				String userTypeStr=userTypeService.getUserType(userType);
				courseResource.setUserTypeStr(userTypeStr);
				courseResource.setLinkAdd(result.getString("linkAdd"));
				courseResourceList.add(courseResource);				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseResourceList;
	}
	public int searchCourseResourceRow(int startPage,int pageSize,String key){  //获取所有课程资源的信息
		String sql="select count(*) count "
				+ "from curriculum_resource c1 "
				+ "join course c2 on c1.courseId=c2.courseId "
				+ "where ("
				+ "c1.resourceId like ? or "
				+ "c1.resourceName like ? or "
				+ "c2.courseName like ? ) "
				+ "and c1.resourceAccount=1";
		PreparedStatement prep;
		ResultSet result;
		List<CourseResource> courseResourceList=new ArrayList<>();
		UserTypeService userTypeService=new UserTypeService();
		int row=0;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setString(1, '%'+key+'%');
			prep.setString(2, '%'+key+'%');
			prep.setString(3, '%'+key+'%');
			result=prep.executeQuery();
			result.next();
			row=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public int getUserCourseResourceRow(int userId,int userType){  //获取用户资源总数
		String sql="select count(*) count from curriculum_resource "
				+ "where userId=? and userType=? and resourceAccount!=3";
		PreparedStatement prep;
		ResultSet result;
		int row=0;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setInt(2, userType);
			result=prep.executeQuery();
			result.next();
			row=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public int getUserCourseResourceRow(String key){  //获取所有资源总数
		String sql="select count(*) count from curriculum_resource "
				+ "where "
				+ "resourceId like ? or "
				+ "resourceName like ? or "
				+ "courseId like ? or "
				+ "userId like ? ";
		PreparedStatement prep;
		ResultSet result;
		int row=0;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setString(1, '%'+key+'%');
			prep.setString(2, '%'+key+'%');
			prep.setString(3, '%'+key+'%');
			prep.setString(4, '%'+key+'%');
			result=prep.executeQuery();
			result.next();
			row=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	public void updateUserCourseResourceRow(int resourceAccount,int resourceId){  //更改用户资源状态
		String sql="update curriculum_resource "
				+ "set resourceAccount=? "
				+ "where resourceId=?";
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, resourceAccount);
			prep.setInt(2, resourceId);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getUserCourseResourceresourceAccountStr(int resourceId){  //更改用户资源状态(中文)
		String sql="select resourceAccount from curriculum_resource "
				+ "where resourceId=?";
		PreparedStatement prep;
		ResultSet result=null;
		int resourceAccount;
		String resourceAccountStr = null;
		ResourceAccount ra=new ResourceAccount();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, resourceId);
			result=prep.executeQuery();
			result.next();
			resourceAccount=result.getInt("resourceAccount");
			resourceAccountStr=ra.getStateStr(resourceAccount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resourceAccountStr;
	}
}
