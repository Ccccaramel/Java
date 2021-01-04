package homepage.edu.dao;

public class CourseStructure {
	private int chapterId;
	private String chapterName;
	private int sectionId;
	private String sectionName;
	private String mvAdd;
	public int getChapterId() {
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getMvAdd() {
		return mvAdd;
	}
	public void setMvAdd(String mvAdd) {
		this.mvAdd = mvAdd;
	}
	public CourseStructure() {
		super();
		// TODO Auto-generated constructor stub
	}
}
