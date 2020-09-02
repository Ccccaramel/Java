package homepage.edu.dao;

public class Achievement {
	private int userId;
	private String answerSheetId;
	private int courseId;
	private String courseName;
	private int testId;
	private String testName;
	private int userAchievement;
	private String submitTestDate;
	private int effectiveness;
	private int totalScore;
	private String pass;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAnswerSheetId() {
		return answerSheetId;
	}
	public void setAnswerSheetId(String answerSheetId) {
		this.answerSheetId = answerSheetId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getUserAchievement() {
		return userAchievement;
	}
	public void setUserAchievement(int userAchievement) {
		this.userAchievement = userAchievement;
	}
	public String getSubmitTestDate() {
		return submitTestDate;
	}
	public void setSubmitTestDate(String submitTestDate) {
		this.submitTestDate = submitTestDate;
	}
	public int getEffectiveness() {
		return effectiveness;
	}
	public void setEffectiveness(int effectiveness) {
		this.effectiveness = effectiveness;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Achievement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
