package admin.edu.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import admin.edu.dao.Admin;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AdminService {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/muke?userSSL=false&serverTimezone=UTC";
	static final String USER="root";
	static final String PASS="root";
	static ResultSet resultest=null;
	static Connection CONN=null;
	private Admin user;
	public AdminService() {
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
	
	public static String md5(String str) {  // md5 ����
		byte[] digest = null;
		MessageDigest md5;
		String md5Str=null;
		try {
			md5 = MessageDigest.getInstance("md5");
			digest=md5.digest(str.getBytes("utf-8"));			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md5Str=new BigInteger(1,digest).toString(16);
		return md5Str;
	}
	
	public static int getRandom() {
		double max=99999999,min=100000;
		int password=(int)(Math.random()*(max-min)+min);
		return password;
	}
	
	public boolean loginId(int id,String gmPassword){  // id ��¼
		String sql="SELECT gmId,gmEmail,gmTel,gmPower,gmAccountStatus from administrator WHERE gmId="+id+" AND gmPassword=\""+md5(gmPassword)+"\"";
		Statement stmt;
		ResultSet result = null;
		boolean state=false;
		System.out.println(":id"+id+",gmPassword:"+md5(gmPassword));
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			state=setUserData(result);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	public boolean loginTel(int gmTel,String gmPassword){  // �绰�����¼
		String sql="SELECT gmId,gmEmail,gmTel,gmPower,gmAccountStatus from administrator WHERE gmTel="+gmTel+" AND gmPassword=\""+md5(gmPassword)+"\"";
		Statement stmt;
		ResultSet result = null;
		boolean state=false;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			state=setUserData(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}	
	public boolean loginEmail(String gmEmail,String gmPassword){  // �����¼
		String sql="SELECT gmId,gmEmail,gmTel,gmPower,gmAccountStatus from administrator WHERE gmEmail=\""+gmEmail+"\" AND gmPassword=\""+md5(gmPassword)+"\"";
		Statement stmt;
		ResultSet result = null;
		boolean state=false;
		
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			state=setUserData(result);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	private void activation(int gmId) {
		String sql="update administrator set gmAccountStatus=1 where gmId=?";
		PreparedStatement prep;
		try {
			prep=CONN.prepareStatement(sql);
			prep.setInt(1, gmId);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int stateCheck(int gmId){  // �˺�״̬���,����ֵΪ����,������ֵ���һ���ж�
		String sql="SELECT gmAccountStatus from administrator WHERE gmId="+gmId;
		Statement stmt;
		ResultSet result=null;
		int gmAccountStatus=3;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			result.next();
			gmAccountStatus=result.getInt("gmAccountStatus"); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gmAccountStatus;
		
	}

	private boolean setUserData(ResultSet result) {  // ������д�� user ��
		boolean sign=false;
		this.user=new Admin();
		this.user.setGmAccountStatus(916);  // Ĭ�ϵ�¼ʧ��,�˺Ż��������
		try {
			while(result.next()) {
				sign=true;
				this.user.setGmId(result.getInt("gmId"));
				this.user.setGmEmail(result.getString("gmEmail"));
				this.user.setGmTel(result.getString("gmTel"));
				int gmPower=result.getInt("gmPower");
				this.user.setGmPower(gmPower);
				if(result.getInt("gmAccountStatus")==4) {  //���˺�֮ǰΪδ�������¼�����Ϊ����
					this.activation(result.getInt("gmId"));
				}
				this.user.setGmAccountStatus(result.getInt("gmAccountStatus"));
				String gmPowerStr=this.getPowerStr(gmPower);
				this.user.setPowerClass(gmPowerStr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sign;
	}
	
	public void setUser(Admin user) {  // set user
		this.user=user;
	}
	public Admin getUser() {  // get user
		return this.user;
	}
	public String getPowerStr(int gmPower) {  // ��ȡȨ�޵����ĺ���(���ݿ����������ֱ�ʶȨ��,��Ҫͨ���ֵ������ȡ�����ĺ���)
		String sql="SELECT powerClass from gm_power WHERE powerNumber="+gmPower;
		Statement stmt;
		ResultSet result = null;
		String powerClass=null;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			result.next();
			powerClass=result.getString("powerClass"); 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return powerClass;
	}
	public String getAccountStatusStr(int gmId) {  // ��ȡ�˺�״̬�����ĺ���(���ݿ����������ֱ�ʶȨ��,��Ҫͨ���ֵ������ȡ�����ĺ���)
		String sql="SELECT accountClass from administrator JOIN account_status ON gmAccountStatus=accountNumber WHERE gmId="+gmId;
		Statement stmt;
		ResultSet result = null;
		String accountClass=null;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			result.next();
			accountClass=result.getString("accountClass"); 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountClass;
	}
	private void updateUser() {  // ���� user ������
		String sql="SELECT gmId,gmEmail,gmTel,gmPower,gmAccountStatus from administrator WHERE gmId="+this.user.getGmId();
		Statement stmt;
		ResultSet result = null;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeQuery(sql);
			setUserData(result);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean saveGMchanges(String Email,int Tel) {  // ������Ϣ
		int gmId=this.user.getGmId();
		String sql="update administrator set gmEmail=\""+Email+"\",gmTel=\""+Tel+"\" WHERE gmId="+gmId;
		Statement stmt;
		int result;
		boolean sign=false;
		try {
			stmt=CONN.createStatement();
			result=stmt.executeUpdate(sql);
			if(result==1) {  // 1����Ӱ��  
				sign=true;
				System.out.println("1����Ӱ��.");
			}else {  // �� 1 ����Ӱ��
				System.out.println(result+"����Ӱ��.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.updateUser();
		return sign;
	}
	public boolean changePassword(String newPassword,String oldPassword) {  // ��������
		int gmId=this.user.getGmId();
		boolean state = this.loginId(gmId, oldPassword);  // ��֤�����Ƿ���ȷ(ͨ��"id��¼"��֤)
		boolean sign=false;
		if(state) {
			String sql="update administrator set gmPassword=\""+md5(newPassword)+"\" WHERE gmId="+gmId;
			Statement stmt;
			int result;
			try {
				stmt=CONN.createStatement();
				result=stmt.executeUpdate(sql);
				if(result==1) {  // 1����Ӱ��  
					sign=true;
					System.out.println("������³ɹ�.");
				}else {  // �� 1 ����Ӱ��
					System.out.println(result+"����Ӱ��,�������ʧ��.");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("�������.");
		}
		return sign;
	}
	public int getAdminRow(){  // ��ȡ�����ݵ�������
		String sql="select * from administrator";
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
	public List<Admin> getAllAccounts(int gmPower,String key,int startPage,int pageSize){  // ��ȡ����Ȩ�޵��ڴ˹���Ա�Ĺ���Ա��Ϣ
		List<Admin> adminList=new ArrayList<>();
		String sql="SELECT gmId,gmEmail,gmTel,gmPower,gmAccountStatus "
				+ "from administrator WHERE gmPower<"+gmPower +
				" AND (gmId LIKE '%"+key+"%' "
						+ "OR gmEmail LIKE '%"+key+"%' "
								+ "OR gmTel LIKE '%"+key+"%' "
										+ "OR gmPower LIKE '%"+key+"%') "
												+ "limit "+startPage+","+pageSize,
				gmEmail,gmPowerStr,accountStatusStr,gmTel;
		int gmId,gmAccountStatus;
		Statement stmt;
		try {
			stmt=CONN.createStatement();
			ResultSet result=stmt.executeQuery(sql);
			while(result.next()) {
				gmId=result.getInt("gmId");
				gmEmail=result.getString("gmEmail");
				gmTel=result.getString("gmTel");
				gmPower=result.getInt("gmPower");  // Ȩ��-���� �Ѿ��ڲ����ж�����
				gmPowerStr=this.getPowerStr(gmPower);  // Ȩ��-����
				gmAccountStatus=result.getInt("gmAccountStatus");  // �˺�״̬-����
				accountStatusStr=this.getAccountStatusStr(gmId);  // �˺�״̬-����
				Admin admin=new Admin(gmId,gmEmail,gmTel,gmPower,gmPowerStr,gmAccountStatus,accountStatusStr);
				adminList.add(admin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adminList;
	}
	
	public void updatePower(int sign,int id) {  // ���ĵͼ�Ȩ�޵Ĺ���Ա�˺�״̬
		String sql="update administrator set gmAccountStatus="+sign+" where gmId="+id;
		Statement stmt;
		try {
			stmt=CONN.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Admin> batchAddAdminAccount(int quantity,int gmPower) { //����ע��ָ���ȼ�����Ա�˺�
		String sql="insert into administrator(gmPassword,gmPower,gmAccountStatus) values(?,?,?)";
		PreparedStatement prep;
		ResultSet result;
		List<Admin> adminList=new ArrayList<Admin>();
		for(int i=0;i<quantity;i++) {
			int gmId=0;
			String gmPassword=String.valueOf(getRandom());
			String password=md5(gmPassword);
			try {
				prep=CONN.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				prep.setString(1, password);
				prep.setInt(2, gmPower);
				prep.setInt(3, 4);
				prep.executeUpdate();
				gmId=this.generateKeys(prep);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Admin admin=new Admin();
			admin.setGmId(gmId);
			admin.setGmPassword(gmPassword);
			adminList.add(admin);
		}
		return adminList; 
	}
	public int generateKeys(PreparedStatement prep) {  //��ȡ�ղ������ݵ����� id
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
}
