package homepage.edu.dao;

public class Note {
	private int courseId;
	private String courseName;
	private int userId;
	private String userName;
	private int userType;
	private String userTypeStr;
	private String	note;
	private String sendingDate;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getUserTypeStr() {
		return userTypeStr;
	}
	public void setUserTypeStr(String userTypeStr) {
		this.userTypeStr = userTypeStr;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getSendingDate() {
		return sendingDate;
	}
	public void setSendingDate(String sendingDate) {
		this.sendingDate = sendingDate;
	}
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
