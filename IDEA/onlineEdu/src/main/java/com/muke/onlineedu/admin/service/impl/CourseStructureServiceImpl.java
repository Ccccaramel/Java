package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.admin.dao.CourseStructureDao;
import com.muke.onlineedu.admin.entity.CourseStructure;
import com.muke.onlineedu.admin.service.CourseStructureService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service("courseStructureService")
public class CourseStructureServiceImpl extends ServiceImpl<CourseStructureDao, CourseStructure> implements CourseStructureService {
    @Override
    public void saveCourseChapter(int courseId,int chapterId, String chapterName, int sectionId, String sectionName, String mvAdd) {
        if(mvAdd==null){
            baseMapper.saveCourseChapterNoMV(courseId,chapterId,chapterName,sectionId,sectionName);
        }else {
            baseMapper.saveCourseChapter(courseId,chapterId,chapterName,sectionId,sectionName,mvAdd);
        }
    }

    @Override
    public void delCourseSection(int courseId, int chapterId, int sectionId) {
        baseMapper.delCourseSection(courseId,chapterId,sectionId);
    }

    /**
     * 删除指定章节的小节
     *
     * @param courseId
     * @param chapterId
     * @param sectionId
     * @param saveVideoPath
     */
    @Override
    public void delMV(int courseId, int chapterId, int sectionId, String saveVideoPath) {
        String mvName = baseMapper.getSectionMVName(courseId, chapterId, sectionId);
        File fileTemp=new File(saveVideoPath+mvName);
        fileTemp.delete();
    }

    /**
     * 删除整章
     *
     * @param courseId
     * @param chapterId
     * @param saveVideoPath
     */
    @Override
    public void delMV(int courseId, int chapterId, String saveVideoPath) {
        String[] mvNameList = baseMapper.getAllChapterMVName(courseId, chapterId);
        for (String mvName:mvNameList){
            File fileTemp=new File(saveVideoPath+mvName);
            fileTemp.delete();
        }
    }

    @Override
    public void delCourseChapter(int courseId, int chapterId) {
        baseMapper.delCourseChapter(courseId,chapterId);
    }

    @Override
    public List<CourseStructure> findBy(int courseId) {
        return baseMapper.findBy(courseId);
    }

    @Override
    public CourseStructure getSectionInfo(int courseId, int chapterId, int sectionId) {
        return baseMapper.getSectionInfo(courseId,chapterId,sectionId);
    }

    @Override
    public int getChaptersSectionRow(int courseId, int previousChapterId) {
        return baseMapper.getChapterInfo(courseId,previousChapterId).size();
    }

    @Override
    public boolean checkChapterExist(int courseId, int chapterId) {
        return baseMapper.getChapterInfo(courseId,chapterId).size()>0;
    }
}
