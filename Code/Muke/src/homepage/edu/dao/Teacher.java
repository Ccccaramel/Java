package homepage.edu.dao;

public class Teacher {
	private String teacherName;
	private int teacherId;
	private String teachersSchool;
	private String teacherIDcard;
	private String teacherTel;
	private String teacherEmail;
	private String teacherQualification;
	private String password;
	private String teachersSchoolEmail;
	private int teacherAccountStatus;
	private String teacherAccountStatusStr;

	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeachersSchool() {
		return teachersSchool;
	}
	public void setTeachersSchool(String teachersSchool) {
		this.teachersSchool = teachersSchool;
	}
	public String getTeacherIDcard() {
		return teacherIDcard;
	}
	public void setTeacherIDcard(String teacherIDcard) {
		this.teacherIDcard = teacherIDcard;
	}
	public String getTeacherTel() {
		return teacherTel;
	}
	public void setTeacherTel(String teacherTel) {
		this.teacherTel = teacherTel;
	}
	public String getTeacherEmail() {
		return teacherEmail;
	}
	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}
	public String getTeacherQualification() {
		return teacherQualification;
	}
	public void setTeacherQualification(String teacherQualification) {
		this.teacherQualification = teacherQualification;
	}
	public int getTeacherAccountStatus() {
		return teacherAccountStatus;
	}
	public void setTeacherAccountStatus(int teacherAccountStatus) {
		this.teacherAccountStatus = teacherAccountStatus;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTeachersSchoolEmail() {
		return teachersSchoolEmail;
	}
	public void setTeachersSchoolEmail(String teachersSchoolEmail) {
		this.teachersSchoolEmail = teachersSchoolEmail;
	}
	public String getTeacherAccountStatusStr() {
		return teacherAccountStatusStr;
	}
	public void setTeacherAccountStatusStr(String teacherAccountStatusStr) {
		this.teacherAccountStatusStr = teacherAccountStatusStr;
	}
	public Teacher(String teacherName, int teacherId, String teachersSchool, String teacherIDcard, String teacherTel,
			String teacherEmail, String teacherQualification, String teachersSchoolEmail, int teacherAccountStatus,
			String teacherAccountStatusStr) {
		super();
		this.teacherName = teacherName;
		this.teacherId = teacherId;
		this.teachersSchool = teachersSchool;
		this.teacherIDcard = teacherIDcard;
		this.teacherTel = teacherTel;
		this.teacherEmail = teacherEmail;
		this.teacherQualification = teacherQualification;
		this.teachersSchoolEmail = teachersSchoolEmail;
		this.teacherAccountStatus = teacherAccountStatus;
		this.teacherAccountStatusStr = teacherAccountStatusStr;
	}
	
}
