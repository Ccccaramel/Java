package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.CourseStructure;

import java.util.List;

public interface CourseStructureService extends IService<CourseStructure> {
    void saveCourseChapter(int courseId,int chapterId, String chapterName, int sectionId, String sectionName, String mvAdd);

    void delCourseSection(int courseId, int chapterId, int sectionId);

    /**
     * 删除指定章节的小节
     * @param courseId
     * @param chapterId
     * @param sectionId
     * @param saveVideoPath
     */
    void delMV(int courseId, int chapterId, int sectionId, String saveVideoPath);

    /**
     * 删除整章
     * @param courseId
     * @param chapterId
     * @param saveVideoPath
     */
    void delMV(int courseId, int chapterId, String saveVideoPath);

    void delCourseChapter(int courseId, int chapterId);


    List<CourseStructure> findBy(int courseId);


    CourseStructure getSectionInfo(int courseId, int chapterId, int sectionId);

    int getChaptersSectionRow(int courseId, int previousChapterId);

    boolean checkChapterExist(int courseId, int chapterId);
}
