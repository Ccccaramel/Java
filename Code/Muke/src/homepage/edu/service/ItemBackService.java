package homepage.edu.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.edu.controller.tool.ResourceAccount;
import homepage.edu.dao.ItemBack;
import homepage.edu.dao.TestOptions;
import homepage.edu.dao.TestQuestion;

public class ItemBackService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	private ItemBack itemBack=new ItemBack();
	private List<TestQuestion> listTestQuestion=new ArrayList<>();
	private List<TestOptions> listTestOption=new ArrayList<>();
	
	public void connect() {  // 连接数据库
		try {
			CONN=DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ItemBackService() {
		super();
		// TODO Auto-generated constructor stub
		this.connect();
	}

	public void addItemBack(int courseId,String testName,int totalScore,int passLine,int teacherId) {  //添加新测试卷
		String sql="insert into item_back(courseId,testName,totalScore,passLine,teacherId) VALUES(?,?,?,?,?)";
		PreparedStatement prep;
		int result,testId;
		try {
			prep=CONN.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			prep.setInt(1, courseId);
			prep.setString(2, testName);
			prep.setInt(3, totalScore);
			prep.setInt(4, passLine);
			prep.setInt(5, teacherId);
			result=prep.executeUpdate();
			testId=this.generateKeys(prep);
			this.itemBack.setTestId(testId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateItemBack(int courseId,String testName,int totalScore,int passLine,int testId) {  //添加新测试卷
		String sql="update item_back set courseId=?,testName=?,totalScore=?,passLine=? where testId=?";
		PreparedStatement prep;
		int result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			prep.setString(2, testName);
			prep.setInt(3, totalScore);
			prep.setInt(4, passLine);
			prep.setInt(5, testId);
			result=prep.executeUpdate();
			this.itemBack.setTestId(testId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int addTestQuestion(int questionNumber,String questionContent,int rightKey,String analysis,int score) {  //保存测试卷题目分值等
		String sql="insert into test_question(testId,questionNumber,questionContent,rightKey,analysis,score) "
				+ "VALUES(?,?,?,?,?,?) "
				+ "ON DUPLICATE KEY UPDATE questionContent=?,rightKey=?,analysis=?,score=?";
		PreparedStatement prep;
		int testId=this.itemBack.getTestId();
		int result,questionId = 0;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, testId);
			prep.setInt(2, questionNumber);
			prep.setString(3, questionContent);
			prep.setInt(4, rightKey);
			prep.setString(5, analysis);
			prep.setInt(6, score);
			prep.setString(7, questionContent);
			prep.setInt(8, rightKey);
			prep.setString(9, analysis);
			prep.setInt(10, score);
			result=prep.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questionId;
	}
	public void delTestQuestion(int testId,int questionNumber) {  //删除指定测试卷的指定编号之后的题目
		this.delTestOption(testId, questionNumber);  //删除指定测试卷的指定题目的选项
		String sql="delete from test_question where testId=? and questionNumber>=?";
		PreparedStatement prep;
		int result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, testId);
			prep.setInt(2, questionNumber);
			result=prep.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void saveTestOption(int questionNumber,int optionNumber,String optionContent) {  //保存测试卷选项文本
		String sql="insert into test_options(questionId,questionOption,optionContent) VALUES(?,?,?) ON DUPLICATE KEY UPDATE questionId=?,questionOption=?,optionContent=?";
		PreparedStatement prep;
		int result,questionId=this.getQuestionId(this.itemBack.getTestId(), questionNumber);
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, questionId);
			prep.setInt(2, optionNumber);
			prep.setString(3, optionContent);
			prep.setInt(4, questionId);
			prep.setInt(5, optionNumber);
			prep.setString(6, optionContent);
			result=prep.executeUpdate(); 
			System.out.println("result:"+result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delTestOption(int testId,int questionNumber) {  //删除指定测试卷的指定题目的选项
		String sql1="select questionId from test_question where testId=? and questionNumber=?";
		PreparedStatement prep1;
		ResultSet result;
		try {
			prep1=CONN.prepareStatement(sql1);
			prep1.setInt(1, testId);
			prep1.setInt(2, questionNumber);
			result=prep1.executeQuery();
			while(result.next()) {
				int questionId=result.getInt("questionId");
				String sql2="delete from test_options where questionId=?";
				PreparedStatement prep;
				prep=CONN.prepareStatement(sql2);
				prep.setInt(1, questionId);
				prep.executeUpdate(); 			
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public List<ItemBack> getTeacherTestMessage(int startPage,int pageSize,int teacherId){  //获取教师自己的所有测试卷基本信息
		String sql="select testId,courseId,testName,testStatus from item_back where teacherId=? and testStatus!=3 limit ?,?";
		PreparedStatement prep;
		ResultSet result;
		ResourceAccount resourceAccount=new ResourceAccount();
		List<ItemBack> listItemBacks=new ArrayList<ItemBack>();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, teacherId);
			prep.setInt(2, startPage);
			prep.setInt(3, pageSize);
			result=prep.executeQuery();
			while(result.next()) {
				ItemBack itemBack=new ItemBack();
				itemBack.setTestId(result.getInt("testId"));
				itemBack.setCourseId(result.getInt("courseId"));
				itemBack.setTestName(result.getString("testName"));
				int testStatus=result.getInt("testStatus");
				itemBack.setTestStatus(testStatus);
				itemBack.setTestStatusStr(resourceAccount.getStateStr(testStatus));
				listItemBacks.add(itemBack);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listItemBacks;
	}
	public List<ItemBack> getAllTestMessage(int startPage,int pageSize,String key){  //获取教师自己的所有测试卷基本信息
		String sql="select testId,courseId,testName,testStatus from item_back "
				+ "where testId like ? or "
				+ "courseId like ? or "
				+ "testName like ? "
				+ "limit ?,?";
		PreparedStatement prep;
		ResultSet result;
		ResourceAccount resourceAccount=new ResourceAccount();
		List<ItemBack> listItemBacks=new ArrayList<ItemBack>();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setString(1, '%'+key+'%');
			prep.setString(2, '%'+key+'%');
			prep.setString(3, '%'+key+'%');
			prep.setInt(4, startPage);
			prep.setInt(5, pageSize);
			result=prep.executeQuery();
			while(result.next()) {
				ItemBack itemBack=new ItemBack();
				itemBack.setTestId(result.getInt("testId"));
				itemBack.setCourseId(result.getInt("courseId"));
				itemBack.setTestName(result.getString("testName"));
				int testStatus=result.getInt("testStatus");
				itemBack.setTestStatus(testStatus);
				itemBack.setTestStatusStr(resourceAccount.getStateStr(testStatus));
				listItemBacks.add(itemBack);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listItemBacks;
	}
	public List<ItemBack> getTeacherTestMessage(int testId){  //获取教师自己指定测试卷基本信息
		String sql="select courseId,testName,totalScore,passLine,testStatus from item_back where testId=?";
		PreparedStatement prep;
		ResultSet result;
		ResourceAccount resourceAccount=new ResourceAccount();
		List<ItemBack> listItemBacks=new ArrayList<ItemBack>();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, testId);
			result=prep.executeQuery();
			while(result.next()) {
				ItemBack itemBack=new ItemBack();
				itemBack.setCourseId(result.getInt("courseId"));
				itemBack.setTestName(result.getString("testName"));
				itemBack.setTotalScore(result.getInt("totalScore"));
				itemBack.setPassLine(result.getInt("passLine"));
				itemBack.setTestStatus(result.getInt("testStatus"));
				listItemBacks.add(itemBack);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listItemBacks;
	}
	public List<ItemBack> getAppointTestInformation(int courseId){  //获取指定课程的测试卷基本信息
		String sql="select testId,testName from item_back where courseId=? and testStatus=1";
		PreparedStatement prep;
		ResultSet result;
		ResourceAccount resourceAccount=new ResourceAccount();
		List<ItemBack> listItemBacks=new ArrayList<ItemBack>();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, courseId);
			result=prep.executeQuery();
			while(result.next()) {
				ItemBack itemBack=new ItemBack();
				itemBack.setTestId(result.getInt("testId"));
				itemBack.setTestName(result.getString("testName"));
				listItemBacks.add(itemBack);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listItemBacks;
	}
	public int getAppointTestInformationRow(int courseId){  //获取指定课程的测试卷基本信息
		String sql="select count(*) count from item_back where courseId=? and testStatus=1";
		PreparedStatement prep;
		ResultSet result;
		int row = 0;
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
	public int getTeacherTestRow(int teacherId){  //获取教师自己的测试卷总数
		String sql="select count(*) count from item_back where teacherId=? and testStatus!=3";
		PreparedStatement prep;
		ResultSet result;
		int row=0;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, teacherId);
			result=prep.executeQuery();
			result.next();
			row=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public int getAllTestRow(String key){  //获取所有测试卷总数
		String sql="select count(*) count from item_back "
				+ "where testId like ? or "
				+ "courseId like ? or "
				+ "testName like ?";
		PreparedStatement prep;
		ResultSet result;
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
	public void updateTestState(int testId,int sign) {  //更新测试卷状态
		String sql="update item_back set testStatus=? where testId=?";
		PreparedStatement prep;
		int row;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, sign);
			prep.setInt(2, testId);
			row=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getTestStateStr(int testId) {  //获取指定测试卷的状态(中文含义)
		String sql="select accountClass from item_back i join account_status a on i.testStatus=a.accountNumber where testId=?";
		PreparedStatement prep;
		ResultSet result;
		String state=null;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, testId);
			result=prep.executeQuery();
			result.next();
			state=result.getString("accountClass");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	public String getTestName(int testId) {  //获取指定测试卷名字
		String sql="select testName from item_back where testId=?";
		PreparedStatement prep;
		ResultSet result;
		String testName=null;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, testId);
			result=prep.executeQuery();
			result.next();
			testName=result.getString("testName");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testName;
	}
	public boolean checkTeachersTest(int testId,int teacherId) {  //检查指定测试卷是否为指定教师的测试卷
		String sql="select count(*) count from item_back where testId=? and teacherId=?";
		PreparedStatement prep;
		ResultSet result;
		boolean state=false;
		int row=0;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, testId);
			prep.setInt(2, teacherId);
			result=prep.executeQuery();
			result.next();
			row=result.getInt("count");
			if(row==1) {
				state=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state; 
	}
	
	public List<TestQuestion> getTestQuestion(int testId){  //获取指定测试卷的题目的相关信息
		String sql="select testId,questionId,questionNumber,questionContent,rightKey,analysis,score from test_question where testId=?";
		PreparedStatement prep;
		ResultSet result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, testId);
			result=prep.executeQuery();
			while(result.next()) {
				TestQuestion testQuestion=new TestQuestion();
				int questionId=result.getInt("questionId");
				testQuestion.setTestId(result.getInt("testId"));
				testQuestion.setQuestionId(result.getInt("questionId"));
				int questionNumber=result.getInt("questionNumber");
				testQuestion.setQuestionNumber(questionNumber);
				testQuestion.setQuestionContent(result.getString("questionContent"));
				testQuestion.setRightKey(result.getString("rightKey"));
				testQuestion.setAnalysis(result.getString("analysis"));
				testQuestion.setScore(result.getInt("score"));
				this.getTestOption(questionId,questionNumber);
				this.listTestQuestion.add(testQuestion);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.listTestQuestion;
	}
	public List<TestQuestion> getTestQuestionAndAnswer(int testId,int userId,String answerSheetId){  //获取指定测试卷的题目的相关信息
		String sql="select questionId,questionNumber,rightKey,analysis from test_question where testId=?";
		PreparedStatement prep;
		ResultSet result;
		UserResultsService userResultsService=new UserResultsService();
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, testId);
			result=prep.executeQuery();
			while(result.next()) {
				TestQuestion testQuestion=new TestQuestion();
				int questionId=result.getInt("questionId");
				testQuestion.setQuestionId(result.getInt("questionId"));
				int questionNumber=result.getInt("questionNumber");
				testQuestion.setQuestionNumber(questionNumber);
				String rightKey=result.getString("rightKey");
				testQuestion.setRightKey(rightKey);
				testQuestion.setAnalysis(result.getString("analysis"));
				String userKey=userResultsService.getUserKey(answerSheetId,questionId);
				testQuestion.setUserKey(userKey);
				if(rightKey.compareTo(userKey)==0) {
					System.out.println("1");
					testQuestion.setUserAnswerResult(1);  //正确
				}else {
					System.out.println("0");
					testQuestion.setUserAnswerResult(0);  //错误
				}
				
				this.listTestQuestion.add(testQuestion);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.listTestQuestion;
	}
	
	private void getTestOption(int questionId,int questionNumber) {  //获取指定题目的各个选项信息
		String sql="select questionOption,optionContent from test_options where questionId=?";
		PreparedStatement prep;
		ResultSet result;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, questionId);
			result=prep.executeQuery();
			while(result.next()) {
				TestOptions testOptions=new TestOptions();
				testOptions.setQuestionId(questionId);
				testOptions.setQuestionNumber(questionNumber);
				testOptions.setQuestionOption(result.getInt("questionOption"));
				testOptions.setOptionContent(result.getString("optionContent"));
				this.listTestOption.add(testOptions);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<TestOptions> getTestOption(){
		return this.listTestOption;
	}
	public int getQuestionId(int testId,int questionNumber) {  //根据测试卷id和题目序号获取题目id
		String sql="select questionId from test_question where testId=? and questionNumber=?";
		PreparedStatement prep;
		ResultSet result;
		int questionId = 0;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, testId);
			prep.setInt(2, questionNumber);
			result=prep.executeQuery();
			result.next();
			questionId=result.getInt("questionId");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return questionId;
	}
}
