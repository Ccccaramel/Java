package homepage.edu.dao;

public class ItemBack {
	private int testId;
	private int courseId;
	private String testName;
	private int testStatus;
	private String testStatusStr;
	private int totalScore;
	private int passLine;
	private int teacherId;
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public int getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(int testStatus) {
		this.testStatus = testStatus;
	}
	public String getTestStatusStr() {
		return testStatusStr;
	}
	public void setTestStatusStr(String testStatusStr) {
		this.testStatusStr = testStatusStr;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public int getPassLine() {
		return passLine;
	}
	public void setPassLine(int passLine) {
		this.passLine = passLine;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public ItemBack() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
