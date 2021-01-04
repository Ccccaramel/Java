package homepage.edu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import homepage.edu.dao.CourseStructure;
import homepage.edu.service.CourseService;

/**
 * Servlet implementation class LearnDataImport
 */
@WebServlet("/LearnDataImport")
public class LearnDataImport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LearnDataImport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		Map<Object,Object> data=new HashMap<>();
		Gson gson=new Gson();
		HttpSession session=request.getSession();
		CourseService courseService=new CourseService();
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int chapterId = Integer.parseInt(request.getParameter("chapterId"));
        int sectionId = Integer.parseInt(request.getParameter("sectionId"));
        String courseName=courseService.getCourseName(courseId);
        String chapterName=courseService.getChapterName(courseId, chapterId);
        String sectionName=courseService.getSectionName(courseId, chapterId, sectionId);
        String mvAdd=courseService.getMvAdd(courseId, chapterId, sectionId);
        
        int previousChapterId=0;
        int previousSectionId=0;
        if(chapterId==1) {
        	if(sectionId==1) {
        		//无 <上一节>
        		previousChapterId=0;
                previousSectionId=0;
        	}else {	
	            previousChapterId=chapterId;
	            previousSectionId=sectionId-1;
        	}
        }else {
    		previousChapterId=chapterId-1;
    		previousSectionId=courseService.getChaptersSectionRow(courseId, previousChapterId);    	
        }
        
        int nextChapterId=0;
        int nextSectionId=0;
        if(courseService.getChaptersSectionRow(courseId, sectionId)==sectionId) {  //若本小节为本章的最后一小节
        	if(courseService.checkChapterExist(courseId, chapterId+1)) {
        		nextChapterId=chapterId+1;
                nextSectionId=1;
        	}else {
        		// 无<下一节>
                nextChapterId=0;
                nextSectionId=0;        		

        	}
        }else {
    		nextChapterId=chapterId;
            nextSectionId=sectionId+1;
        }

        data.put("courseId",courseId);
        data.put("courseName",courseName);
        data.put("chapterId", chapterId);
        data.put("chapterName", chapterName);
        data.put("sectionId", sectionId);
        data.put("sectionName", sectionName);
        data.put("mvAdd", mvAdd);
        
        data.put("previousChapterId",previousChapterId);
        data.put("previousSectionId",previousSectionId);
        data.put("nextChapterId",nextChapterId);
        data.put("nextSectionId",nextSectionId);
        
        String dataJson=gson.toJson(data);
        System.out.println("LearnDataImport:"+dataJson);
		response.getWriter().print(dataJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
