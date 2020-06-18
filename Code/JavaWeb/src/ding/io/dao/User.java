package ding.io.dao;

import java.io.Serializable;

public class User implements Serializable {
	private Integer IDCard;
	private String Name;
	private Integer Age;
	private String SubmissionDate;
	public Integer getIDCard() {
		return IDCard;
	}
	public void setIDCard(Integer iDCard) {
		IDCard = iDCard;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

	public Integer getAge() {
		return Age;
	}
	public void setAge(Integer age) {
		Age = age;
	}
	public String getSubmissionDate() {
		return SubmissionDate;
	}
	public void setSubmissionDate(String submissionDate) {
		SubmissionDate = submissionDate;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer iDCard, String name, Integer age, String submissionDate) {
		super();
		IDCard = iDCard;
		Name = name;
		Age = age;
		SubmissionDate = submissionDate;
	}	
	
}
