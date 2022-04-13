package com.muke.onlineedu.dao;

import com.muke.onlineedu.OnlineEduApplicationTests;
import com.muke.onlineedu.admin.dao.TeacherDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class TeacherMapperTest extends OnlineEduApplicationTests {

    @Autowired
    private TeacherDao teacherMapper;

    @Test
    void testInsert() {
//        TeacherEntity teacher = new TeacherEntity(1002,"A");
//        teacherMapper.insert(teacher);
//        log.info("Teacher {} inserted.",teacher.getId());
    }

    @Test
    void delete() {
//        TeacherEntity teacher=new TeacherEntity(1002,"A");
//        teacherMapper.delete(teacher);
//        log.info("Teacher {} deleted.",teacher.getId());
    }

    @Test
    void update() {
//        TeacherEntity teacher=new TeacherEntity(1001,"A");
//        teacherMapper.update(teacher);
//        log.info("Teacher {} updated.",teacher.getId());
    }
}