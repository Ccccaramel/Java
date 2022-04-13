package com.muke.onlineedu.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.Note;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteDao extends BaseMapper<Note> {
    List<Note> findBy(int courseId);
    List<Note> getNote(int courseId, int startPage, int pageSize);
    void addNote(int courseId, int userId, int userType, String note);
}
