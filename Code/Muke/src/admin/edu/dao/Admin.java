package admin.edu.dao;

import java.io.Serializable;

public class Admin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int gmId;
	private String gmEmail;
	private String gmTel;
	private String gmPassword;
	private int gmPower;
	private int gmAccountStatus;
	
	private String powerClass;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String accountStatusStr;
	
	public String getAccountStatusStr() {
		return accountStatusStr;
	}
	public void setAccountStatusStr(String accountStatusStr) {
		this.accountStatusStr = accountStatusStr;
	}	
	public String getPowerClass() {
		return powerClass;
	}
	public void setPowerClass(String powerClass) {
		this.powerClass = powerClass;
	}
	public int getGmId() {
		return gmId;
	}
	public void setGmId(int gmId) {
		this.gmId = gmId;
	}
	public String getGmEmail() {
		return gmEmail;
	}
	public void setGmEmail(String gmEmail) {
		this.gmEmail = gmEmail;
	}
	public String getGmTel() {
		return gmTel;
	}
	public void setGmTel(String gmTel) {
		this.gmTel = gmTel;
	}
	public String getGmPassword() {
		return gmPassword;
	}
	public void setGmPassword(String gmPassword) {
		this.gmPassword = gmPassword;
	}
	public int getGmPower() {
		return gmPower;
	}
	public void setGmPower(int gmPower) {
		this.gmPower = gmPower;
	}
	public int getGmAccountStatus() {
		return gmAccountStatus;
	}
	public void setGmAccountStatus(int gmAccountStatus) {
		this.gmAccountStatus = gmAccountStatus;
	}

	public Admin(int gmId, String gmEmail, String gmTel, int gmPower,String powerClass, int gmAccountStatus,
			 String accountStatusStr) {
		super();
		this.gmId = gmId;
		this.gmEmail = gmEmail;
		this.gmTel = gmTel;
		this.gmPower = gmPower;
		this.gmAccountStatus = gmAccountStatus;
		this.powerClass = powerClass;
		this.accountStatusStr = accountStatusStr;
	}
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	public int CheckAccountStatus() {
		if(this.gmAccountStatus==0) {
			return 911;  // �˺�״̬�����
		}else if(this.gmAccountStatus==1) {
			return 915;  // �˺�״̬����
		}else if(this.gmAccountStatus==2) {
			return 913;  // �˺�״̬�Ѷ���
		}else if(this.gmAccountStatus==3) {
			return 914;  // �˺�״̬��ע��
		}else if(this.gmAccountStatus==4) {
			return 912;  // �˺�״̬������
		}else{
			return 916;  // �˺�״̬δ֪����ֹ��¼
		}
	}
}
