package homepage.edu.dao;

public class Student {
	private String userName;
	private int userId;
	private String userEmail;
	private String userTel;
	private String userOldPassword;
	private String userNewPassword;
	private String userSex;
	private String userBirth;
	private int userAccountStatus;
	private String accountClass;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserOldPassword() {
		return userOldPassword;
	}
	public void setUserOldPassword(String userOldPassword) {
		this.userOldPassword = userOldPassword;
	}
	public String getUserNewPassword() {
		return userNewPassword;
	}
	public void setUserNewPassword(String userNewPassword) {
		this.userNewPassword = userNewPassword;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public int getUserAccountStatus() {
		return userAccountStatus;
	}
	public void setUserAccountStatus(int userAccountStatus) {
		this.userAccountStatus = userAccountStatus;
	}
	public String getAccountClass() {
		return accountClass;
	}
	public void setAccountClass(String accountClass) {
		this.accountClass = accountClass;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
