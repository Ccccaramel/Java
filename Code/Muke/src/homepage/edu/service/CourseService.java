package homepage.edu.service;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.protocol.Resultset;

import admin.edu.service.CourseTypeService;
import homepage.edu.dao.Course;
import homepage.edu.dao.CourseStructure;

public class CourseService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	private Course course=new Course();
	
	public void connect() {  // 连接数据库
		try {
			CONN=DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public CourseService() {
		super();
		// TODO Auto-generated constructor stub
		this.connect();
	}

	public boolean addCourse(String courseName,String courseImgName,int courseClass,int teacherId) {  //添加新课程(课程名,封面,作者...)
		String sql="insert into course(courseName,courseImgName,courseClass,teacherId) VALUES(?,?,?,?)";
		boolean result=false;
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			prep.setString(1, courseName);
			prep.setString(2, courseImgName);
			prep.setInt(3, courseClass);
			prep.setInt(4, teacherId);
			int num=prep.executeUpdate();
			if(num>0) {
				System.out.println("course success!");
			}
			int courseId=this.generateKeys(prep);
			System.out.println("courseId:"+courseId);
			this.course.setCourseId(courseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean changeCourse(String courseName,String courseImgName,int courseClass,int courseId) {  //更改课程(课程名,封面,作者...)
		String sql=null;
		if(courseImgName==null) {
			sql="update course set courseName=?,courseClass=? where courseId=?";
		}else {
			sql="update course set courseName=?,courseImgName=?,courseClass=? where courseId=?";
		}
		
		boolean result=false;
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			prep.setString(1, courseName);
			if(courseImgName==null) {
				prep.setInt(2, courseClass);
				prep.setInt(3, courseId);	
			}else {
				prep.setString(2, courseImgName);
				prep.setInt(3, courseClass);
				prep.setInt(4, courseId);				
			}

			int num=prep.executeUpdate();
			System.out.println("num:"+num);
			if(num>0) {
				System.out.println("course success!");
			}
			this.course.setCourseId(courseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean saveCourseAbstract(int courseSection,String courseIntroduce) {  //存储/更新课程简介
		String sql="insert into course_abstract(courseId,courseSection,courseIntroduce) VALUES(?,?,?) ON DUPLICATE KEY UPDATE courseIntroduce=?";
		PreparedStatement prep=null;
		boolean result=false;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1,this.course.getCourseId());
			prep.setInt(2, courseSection);
			prep.setString(3, courseIntroduce);
			prep.setString(4, courseIntroduce);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; 
	}
	public boolean delCourseAbstract(int courseSection,int courseId) {  //存储/更新课程简介
		String sql="delete from course_abstract where courseId=? and courseSection=?";
		PreparedStatement prep=null;
		boolean result=false;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1,courseId);
			prep.setInt(2, courseSection);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; 
	}
	public void saveCourseChapter(int chapter,String chapterName,int section,String sectionName,String mvAdd){  //保存课程章节,小节,视频路径
		String sql=null;
		if(mvAdd==null) {
			sql="insert into course_structure(courseId,chapterId,chapterName,sectionId,sectionName) VALUES(?,?,?,?,?) ON DUPLICATE KEY UPDATE chapterName=?,sectionName=?";
		}else {
			sql="insert into course_structure(courseId,chapterId,chapterName,sectionId,sectionName,mvAdd) VALUES(?,?,?,?,?,?) ON DUPLICATE KEY UPDATE chapterName=?,sectionName=?,mvAdd=?";
		}
		
		PreparedStatement prep;
		int row;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, this.course.getCourseId());
			prep.setInt(2, chapter);
			prep.setString(3, chapterName);
			prep.setInt(4, section);
			prep.setString(5, sectionName);
			if(mvAdd==null) {
				prep.setString(6, chapterName);
				prep.setString(7, sectionName);	
			}else {
				prep.setString(6, mvAdd);
				prep.setString(7, chapterName);
				prep.setString(8, sectionName);
				prep.setString(9, mvAdd);				
			}

			row=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delCourseSection(int courseId,int chapterId,int sectionId) {  //删除指定课程的指定章节的指定小节数之后的小节
		//删除数据前先将本地资源删除
		String sql="delete from course_structure where courseId=? and chapterId=? and sectionId>=?";
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setInt(2, chapterId);
			prep.setInt(3, sectionId);	
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delCourseChapter(int courseId,int chapterId) {  //删除指定课程的指定章节数之后的章节
		//删除数据前先将本地资源删除
		String sql="delete from course_structure where courseId=? and chapterId>=?";
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setInt(2, chapterId);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delMV(int courseId,int chapterId,int sectionId,String saveVideoPath) {  //删除指定课程的指定章节的指定小节数之后的小节MV
		String sql="select mvAdd from course_structure where courseId=? and chapterId=? and sectionId>=?";
		
		PreparedStatement prep;
		ResultSet result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setInt(2, chapterId);
			prep.setInt(3, sectionId);
			result=prep.executeQuery();
			while(result.next()) {
				String mvAdd=result.getString("mvAdd");
				File fileTemp=new File(saveVideoPath+mvAdd);
				if(fileTemp.exists()) {
					fileTemp.delete();
					System.out.println("尾部小节删除成功!");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delMV(int courseId,int chapterId,String saveVideoPath) {  //删除指定课程的指定章节的指定小节数之后的小节MV
		String sql="select mvAdd from course_structure where courseId=? and chapterId>=?";
		
		PreparedStatement prep;
		ResultSet result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setInt(2, chapterId);
			result=prep.executeQuery();
			while(result.next()) {
				String mvAdd=result.getString("mvAdd");
				File fileTemp=new File(saveVideoPath+mvAdd);
				if(fileTemp.exists()) {
					fileTemp.delete();
					System.out.println("整章删除成功!");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public CourseStructure getAppointSectionMessage(int courseId,int chapterId,int sectionId) {  //获取指定课程的指定章节的指定小节数据
		String sql="select chapterId,chapterName,sectionId,sectionName,mvAdd from "
				+ "course_structure where courseId=? and chapterId=? and sectionId=?";
		CourseStructure courseStructure=new CourseStructure();
		PreparedStatement prep;
		ResultSet result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setInt(2, chapterId);
			prep.setInt(3, sectionId);
			result=prep.executeQuery();
			while(result.next()) {
				courseStructure.setChapterId(result.getInt("chapterId"));
				courseStructure.setChapterName(result.getString("chapterName"));
				courseStructure.setSectionId(result.getInt("sectionId"));
				courseStructure.setSectionName(result.getString("sectionName"));
				courseStructure.setMvAdd(result.getString("mvAdd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseStructure;
	}
	public int generateKeys(PreparedStatement prep) {  //获取刚插入数据的自增 id
		ResultSet result;
		try {
			result = prep.getGeneratedKeys();
			if(result.next()) {
				return result.getInt(1);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
	}
	
	public List<Course> getCourseMessage(String key,int startPage,int pageSize){  //获取所有课程的部分基本信息
		List<Course> courseList=new ArrayList<Course>();
		String sql="select "
				+ "c.courseId as courseId,"
				+ "c.courseName as courseName,"
				+ "c.teacherId as teacherId,"
				+ "t.teacherName as teacherName,"
				+ "c.courseClass as courseClass,"
				+ "c.courseStatus as courseStatus "
				+ "from course c "
				+ "left join teacher_basic_information t on c.teacherId=t.teacherId "
				+ "where c.courseId like \"%"+key+"%\" or "
				+ "c.courseName like \"%"+key+"%\" or "
				+ "c.teacherId like \"%"+key+"%\" or "
				+ "t.teacherName like \"%"+key+"%\" or "
				+ "c.courseClass like \"%"+key+"%\" or "
				+ "c.courseStatus like \"%"+key+"%\" "
				+ "limit "+startPage+","+pageSize;
		PreparedStatement prep=null;
		CourseTypeService courseTypeService=new CourseTypeService();
		ResultSet result=null;
		try {
			prep=CONN.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()) {
				Course course=new Course();
				int courseId=result.getInt("courseId");
				course.setCourseId(courseId);
				course.setCourseName(result.getString("courseName"));
				course.setTeacherId(result.getInt("teacherId"));
				course.setTeacherName(result.getString("teacherName"));
				course.setCourseClass(result.getInt("courseClass"));
				course.setCourseClassStr(courseTypeService.getcourseType(result.getInt("courseClass")));
				course.setCourseStatus(result.getInt("courseStatus"));
				course.setCourseStatusStr(this.getCourseStatusStr(courseId));
				courseList.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseList;
	}
	public int getSourchCourseRow(String key,int startPage,int pageSize){  //模糊搜索-所有课程的部分基本信息
		List<Course> courseList=new ArrayList<Course>();
		String sql="select count(*) count from course "
				+ "where courseName like \"%"+key+"%\" "
				+ "and courseStatus=1";
		PreparedStatement prep=null;
		CourseTypeService courseTypeService=new CourseTypeService();
		ResultSet result=null;
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
	public List<Course> sourchCourse(String key,int startPage,int pageSize){  //模糊搜索-所有课程的部分基本信息
		List<Course> courseList=new ArrayList<Course>();
		String sql="select "
				+ "courseId,"
				+ "courseName,"
				+ "courseImgName "
				+ "from course "
				+ "where courseName like \"%"+key+"%\" "
				+ "and courseStatus=1 "
				+ "limit "+startPage+","+pageSize;
		PreparedStatement prep=null;
		CourseTypeService courseTypeService=new CourseTypeService();
		ResultSet result=null;
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
	public List<Course> getCourseMessage(int startPage,int pageSize,int id){  //获取所有课程的部分基本信息
		List<Course> courseList=new ArrayList<Course>();
		String sql="select "
				+ "courseId,"
				+ "courseName,"
				+ "courseClass,"
				+ "courseStatus "
				+ "from course "
				+ "where teacherId="+id +" and courseStatus!=3 "
				+ "limit "+startPage+","+pageSize;
		PreparedStatement prep=null;
		CourseTypeService courseTypeService=new CourseTypeService();
		ResultSet result=null;
		try {
			prep=CONN.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()) {
				Course course=new Course();
				int courseId=result.getInt("courseId");
				course.setCourseId(courseId);
				course.setCourseName(result.getString("courseName"));
				course.setCourseClass(result.getInt("courseClass"));
				course.setCourseClassStr(courseTypeService.getcourseType(result.getInt("courseClass")));
				course.setCourseStatus(result.getInt("courseStatus"));
				course.setCourseStatusStr(this.getCourseStatusStr(courseId));
				courseList.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseList;
	}
	public Course getCourseMessage(int courseId){  //获取指定课程的基本信息
		Course course=new Course();
		String sql="select "
				+ "courseName,"
				+ "courseClass,"
				+ "courseImgName "
				+ "from course "
				+ "where courseStatus=1 and courseId=?";
		PreparedStatement prep=null;
		ResultSet result=null;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			result=prep.executeQuery();
			result.next();
			course.setCourseId(courseId);
			course.setCourseName(result.getString("courseName"));
			course.setCourseImgName(result.getString("courseImgName"));
			course.setCourseClass(result.getInt("courseClass"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;
	}
	public List<Course> getFavoriteCourseMessage(int startPage,int totalPage,int userId){  //获取指定用户收藏的课程的基本信息
		String sql="select "
				+ "c.courseName as courseName,"
				+ "c.courseId as courseId,"
				+ "c.courseImgName as courseImgName "
				+ "from course c "
				+ "join favorite f on c.courseId=f.courseId "
				+ "where courseStatus=1 and f.userId=? "
				+ "limit ?,?";
		List<Course> courseList=new ArrayList<Course>();
		PreparedStatement prep=null;
		ResultSet result=null;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setInt(2, startPage);
			prep.setInt(3, totalPage);
			result=prep.executeQuery();
			while(result.next()){
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
	public int getFavoriteCourseRow(int userId){  //获取指定用户收藏的课程的基本信息
		String sql="select count(*) count "
				+ "from favorite "
				+ "where userId=?";
		int row=0;
		PreparedStatement prep=null;
		ResultSet result=null;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, userId);
			result=prep.executeQuery();
			result.next();
			row=result.getInt("count");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public Course getAppointCourseMessage(int courseId){  //获取指定课程的基本信息
		Course course=new Course();
		String sql="select "
				+ "c.courseName as courseName,"
				+ "c.courseImgName as courseImgName,"
				+ "t.teacherName as teacherName "
				+ "from course c "
				+ "join teacher_basic_information t on c.teacherId=t.teacherId "
				+ "where courseStatus=1 and courseId=?";
		PreparedStatement prep=null;
		ResultSet result=null;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			result=prep.executeQuery();
			while(result.next()) {
				course.setCourseId(courseId);
				course.setCourseName(result.getString("courseName"));
				course.setCourseImgName(result.getString("courseImgName"));
				course.setTeacherName(result.getString("teacherName"));		
				course.setCourseStatus(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;
	}
	public String getCourseStatusStr(int courseId) {  //获取资源状态字段(数字)的中文含义
		String sql="select r.accountClass from resource_account r join course c on r.accountNumber=c.courseStatus where c.courseId="+courseId;
		PreparedStatement prep;
		ResultSet result;
		String courseStatusStr=null;
		try {
			prep=CONN.prepareStatement(sql);
			result=prep.executeQuery();
			result.next();
			courseStatusStr=result.getString("accountClass");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("courseStatusStr:"+courseStatusStr);
		return courseStatusStr;
	}
	
	public int getCourseTableRow() {  //获取课程总数
		String sql="select count(*) count from course";
		int row=0;
		ResultSet result;
		PreparedStatement prep;
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
	public int getAllCourseRow() {  //获取课程总数
		String sql="select count(*) count from course where courseStatus!=3";
		int row=0;
		ResultSet result;
		PreparedStatement prep;
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
	public int getCourseTableRow(int teacherId) {  //教师获取自己的课程总数
		String sql="select count(*) count from course where teacherId="+teacherId+" and courseStatus!=3";
		int row=0;
		ResultSet result;
		PreparedStatement prep;
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
	public int getAdoptCourseTypeRow(int courseClass) {  //教师获取自己的课程总数
		String sql="select count(*) count from course where courseClass=? and courseStatus!=3";
		int row=0;
		ResultSet result;
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseClass);
			result=prep.executeQuery();
			result.next();
			row=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public List<Course> getAllTypeCourse(int startPage,int pageSize) {  //获取所有类型的指定页数的课程信息
		String sql="select courseId,courseName,courseImgName from course "
				+ "where courseStatus!=3 "
				+ "limit ?,?";
		int row=0;
		ResultSet result;
		PreparedStatement prep;
		List<Course> courseList=new ArrayList<>();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, startPage);
			prep.setInt(2, pageSize);
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
	public List<Course> getAdoptTypeCourse(int courseClass,int startPage,int pageSize) {  //获指定类型课程的指定页数的信息
		String sql="select courseId,courseName,courseImgName from course "
				+ "where courseClass=? and courseStatus!=3 "
				+ "limit ?,?";
		int row=0;
		ResultSet result;
		PreparedStatement prep;
		List<Course> courseList=new ArrayList<>();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseClass);
			prep.setInt(2, startPage);
			prep.setInt(3, pageSize);
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
	public void updateCourseStatus(int sign,int id) {  //更新指定课程的课程状态
		String sql="update course set courseStatus=? where courseId=?";
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
	public Map<Object,Object> getCourseAbstract(int courseId){  //获取指定课程的简介信息
		String sql="SELECT courseSection,courseIntroduce from course_abstract WHERE courseId="+courseId;
		PreparedStatement prep;
		ResultSet result;
		Map<Object,Object> data=new HashMap<>();
		try {
			prep=CONN.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()) {
				data.put(result.getInt("courseSection"), result.getString("courseIntroduce"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	public List<CourseStructure> getCourseStructure(int courseId){  //获取指定课程的目录结构信息
		String sql="SELECT chapterId,chapterName,sectionId,sectionName,mvAdd from course_structure WHERE courseId=?";
		PreparedStatement prep;
		ResultSet result;
		List<CourseStructure> courseStructureList=new ArrayList<CourseStructure>();
		
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			result=prep.executeQuery();
			while(result.next()) {
				CourseStructure courseStructure=new CourseStructure();
				courseStructure.setChapterId(result.getInt("chapterId"));
				courseStructure.setChapterName(result.getString("chapterName"));
				courseStructure.setSectionId(result.getInt("sectionId"));
				courseStructure.setSectionName(result.getString("sectionName"));
				courseStructure.setMvAdd(result.getString("mvAdd"));
				courseStructureList.add(courseStructure);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<Object,Object> data=new HashMap<>();
		return courseStructureList;
	}
	public boolean checkTeachersCourse(int teacherId,int courseId) {  //检查指定教师是否包含指定课程
		String sql="select count(*) count from course where courseId=? and teacherId=?";
		boolean state=false;
		PreparedStatement prep;
		ResultSet result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setInt(2, teacherId);
			result=prep.executeQuery();
			result.next();
			if(result.getInt("count")==1) {
				state=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("state:"+state);
		return state;
	}
	
	public boolean checkCourseExist(int courseId) {  //检查指定正常课程是否存在
		String sql="select count(*) count from course where courseId=? and courseStatus=1";
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
	
	public List<Course> getTeacherAllCourse(int teacherId){
		String sql="select courseId,courseName from course where teacherId=?";
		List<Course> courseList=new ArrayList<>();
		PreparedStatement prep;
		ResultSet result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, teacherId);
			result=prep.executeQuery();
			while(result.next()) {
				Course course=new Course();
				course.setCourseId(result.getInt("courseId"));
				course.setCourseName(result.getString("courseName"));
				courseList.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseList;
	}
	
	public String getCourseName(int courseId) {
		String sql="select courseName from course where courseId=?";
		PreparedStatement prep;
		ResultSet result;
		String courseName = null;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			result=prep.executeQuery();
			result.next();
			courseName=result.getString("courseName");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseName;
	}
	public String getChapterName(int courseId,int chapterId) {
		String sql="select chapterName from course_structure where courseId=? and chapterId=?";
		PreparedStatement prep;
		ResultSet result;
		String chapterName = null;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setInt(2, chapterId);
			result=prep.executeQuery();
			result.next();
			chapterName=result.getString("chapterName");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chapterName;
	}
	/**
	 * @param courseId
	 * @param chapterId
	 * @return
	 */
	public String getSectionName(int courseId,int chapterId,int sectionId) {
		String sql="select sectionName from course_structure "
				+ "where courseId=? and chapterId=? and sectionId=?";
		PreparedStatement prep;
		ResultSet result;
		String sectionName = null;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setInt(2, chapterId);
			prep.setInt(3, sectionId);
			result=prep.executeQuery();
			result.next();
			sectionName=result.getString("sectionName");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sectionName;
	}
	public List<Course> getPopularCourses(int amount){  //获取指定数量的热门课程
		String sql="SELECT c1.courseId as courseId,"
				+ "c1.courseName as courseName,"
				+ "c1.courseImgName as courseImgName,"
				+ "c2.courseIntroduce as courseIntroduce from course c1 "
				+ "join course_abstract c2 on c1.courseId=c2.courseId "
		+ "WHERE c1.courseStatus=1 and c2.courseSection=1 ORDER BY heat desc limit ?";
		PreparedStatement prep;
		List<Course> courseList=new ArrayList<Course>();
		ResultSet result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, amount);
			result=prep.executeQuery();
			while(result.next()) {
				Course course=new Course();
				course.setCourseId(result.getInt("courseId"));
				course.setCourseName(result.getString("courseName"));
				course.setCourseImgName(result.getString("courseImgName"));
				course.setIntroductionToTheFirstParagraph(result.getString("courseIntroduce"));
				courseList.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseList;
	}
	public List<Course> getLatestCourses(int amount){  //获取指定数量的最新课程
		String sql="SELECT c1.courseId as courseId,"
				+ "c1.courseName as courseName,"
				+ "c1.courseImgName as courseImgName,"
				+ "c2.courseIntroduce as courseIntroduce from course c1 "
				+ "join course_abstract c2 on c1.courseId=c2.courseId "
		+ "WHERE c1.courseStatus=1 and c2.courseSection=1 ORDER BY courseId desc limit ?";
		PreparedStatement prep;
		List<Course> courseList=new ArrayList<Course>();
		ResultSet result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, amount);
			result=prep.executeQuery();
			while(result.next()) {
				Course course=new Course();
				course.setCourseId(result.getInt("courseId"));
				course.setCourseName(result.getString("courseName"));
				course.setCourseImgName(result.getString("courseImgName"));
				course.setIntroductionToTheFirstParagraph(result.getString("courseIntroduce"));
				courseList.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseList;
	}
	public List<Course> getITCourses(int amount){  //获取指定数量的IT课程
		String sql="SELECT c1.courseId as courseId,"
				+ "c1.courseName as courseName,"
				+ "c1.courseImgName as courseImgName,"
				+ "c2.courseIntroduce as courseIntroduce from course c1 "
				+ "join course_abstract c2 on c1.courseId=c2.courseId "
		+ "WHERE c1.courseStatus=1 and c2.courseSection=1 and c1.courseClass=1015 ORDER BY heat desc limit ?";
		PreparedStatement prep;
		List<Course> courseList=new ArrayList<Course>();
		ResultSet result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, amount);
			result=prep.executeQuery();
			while(result.next()) {
				Course course=new Course();
				course.setCourseId(result.getInt("courseId"));
				course.setCourseName(result.getString("courseName"));
				course.setCourseImgName(result.getString("courseImgName"));
				course.setIntroductionToTheFirstParagraph(result.getString("courseIntroduce"));
				courseList.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseList;
	}
	public List<Course> getLiteraryHistoryCourses(int amount){  //获取指定数量的历史课程
		String sql="SELECT c1.courseId as courseId,"
				+ "c1.courseName as courseName,"
				+ "c1.courseImgName as courseImgName,"
				+ "c2.courseIntroduce as courseIntroduce from course c1 "
				+ "join course_abstract c2 on c1.courseId=c2.courseId "
		+ "WHERE c1.courseStatus=1 and c2.courseSection=1 and c1.courseClass=1014 ORDER BY heat desc limit ?";
		PreparedStatement prep;
		List<Course> courseList=new ArrayList<Course>();
		ResultSet result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, amount);
			result=prep.executeQuery();
			while(result.next()) {
				Course course=new Course();
				course.setCourseId(result.getInt("courseId"));
				course.setCourseName(result.getString("courseName"));
				course.setCourseImgName(result.getString("courseImgName"));
				course.setIntroductionToTheFirstParagraph(result.getString("courseIntroduce"));
				courseList.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseList;
	}
	public String getMvAdd(int courseId,int chapterId,int sectionId){  //获取指定课程指定章节指定小节的视频地址
		String sql="SELECT mvAdd "
				+ "from course_structure "
				+ "WHERE courseId=? and chapterId=? and sectionId=?";
		PreparedStatement prep;
		String mvAdd=null;
		ResultSet result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setInt(2, chapterId);
			prep.setInt(3, sectionId);
			result=prep.executeQuery();
			while(result.next()) {
				mvAdd=result.getString("mvAdd");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mvAdd;
	}
	public void heatsUp(int courseId){  //获取指定数量的历史课程
		String sql="update course set heat=heat+1 where courseId=?";
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getChaptersSectionRow(int courseId,int chapterId) {  //获取指定课程的指定章节的最后一小节
		String sql="select count(*) count from course_structure where "
				+ "courseId=? and chapterId=?";
		PreparedStatement prep;
		ResultSet result;
		int previousSectionId=0;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setInt(2, chapterId);
			result=prep.executeQuery();
			result.next();
			previousSectionId=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return previousSectionId;
	}
	public boolean checkChapterExist(int courseId,int chapterId) {  //检查指定课程的指定章节是否存在
		String sql="select count(*) count from course_structure where "
				+ "courseId=? and chapterId=?";
		PreparedStatement prep;
		ResultSet result;
		boolean exist=false;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setInt(2, chapterId);
			result=prep.executeQuery();
			result.next();
			if(result.getInt("count")>0) {
				exist=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exist;
	}
}
