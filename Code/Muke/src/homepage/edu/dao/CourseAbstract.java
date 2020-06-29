package homepage.edu.dao;

public class CourseAbstract {
	private int courseSection;
	private String courseIntroduce;
	public int getCourseSection() {
		return courseSection;
	}
	public void setCourseSection(int courseSection) {
		this.courseSection = courseSection;
	}
	public String getCourseIntroduce() {
		return courseIntroduce;
	}
	public void setCourseIntroduce(String courseIntroduce) {
		this.courseIntroduce = courseIntroduce;
	}
	public CourseAbstract() {
		super();
		// TODO Auto-generated constructor stub
	}
}
