package com.muke.onlineedu.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.CourseStructure;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseStructureDao extends BaseMapper<CourseStructure> {
    void saveCourseChapterNoMV(int courseId, int chapterId, String chapterName, int sectionId, String sectionName);

    void saveCourseChapter(int courseId, int chapterId, String chapterName, int sectionId, String sectionName, String mvAdd);

    void delCourseSection(int courseId, int chapterId, int sectionId);

    String getSectionMVName(int courseId, int chapterId, int sectionId);

    String[] getAllChapterMVName(int courseId, int chapterId);

    void delCourseChapter(int courseId, int chapterId);

    List<CourseStructure> findBy(int courseId);

    CourseStructure getSectionInfo(int courseId, int chapterId, int sectionId);

    List<CourseStructure> getChapterInfo(int courseId, int chapterId);
}
