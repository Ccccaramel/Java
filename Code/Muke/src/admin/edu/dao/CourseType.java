package admin.edu.dao;

public class CourseType {
	private int typeValue;
	private String typeName;
	
	public int getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public CourseType(int typeValue, String typeName) {
		super();
		this.typeValue = typeValue;
		this.typeName = typeName;
	}
	public CourseType() {
		super();
		// TODO Auto-generated constructor stub
	}
}
