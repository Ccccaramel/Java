package homepage.edu.dao;

public class Course {
	private String courseName;
	private int courseId;
	private String courseImgName;
	private int courseClass;
	private String courseClassStr;
	private int teacherId;
	private String teacherName;
	private int courseStatus;
	private String IntroductionToTheFirstParagraph;
	private int heat;
	private String courseStatusStr;
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseImgName() {
		return courseImgName;
	}
	public void setCourseImgName(String courseImgName) {
		this.courseImgName = courseImgName;
	}
	public int getCourseClass() {
		return courseClass;
	}
	public void setCourseClass(int courseClass) {
		this.courseClass = courseClass;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(int courseStatus) {
		this.courseStatus = courseStatus;
	}
	public int getHeat() {
		return heat;
	}
	public void setHeat(int heat) {
		this.heat = heat;
	}
	public String getCourseClassStr() {
		return courseClassStr;
	}
	public void setCourseClassStr(String courseClassStr) {
		this.courseClassStr = courseClassStr;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getCourseStatusStr() {
		return courseStatusStr;
	}
	public void setCourseStatusStr(String courseStatusStr) {
		this.courseStatusStr = courseStatusStr;
	}
	public String getIntroductionToTheFirstParagraph() {
		return IntroductionToTheFirstParagraph;
	}
	public void setIntroductionToTheFirstParagraph(String introductionToTheFirstParagraph) {
		IntroductionToTheFirstParagraph = introductionToTheFirstParagraph;
	}
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

}
