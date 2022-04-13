package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.admin.dao.NoteDao;
import com.muke.onlineedu.admin.entity.Note;
import com.muke.onlineedu.admin.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("noteService")
public class NoteServiceImpl extends ServiceImpl<NoteDao, Note> implements NoteService {
    @Override
    public int getNoteRow(int courseId) {
        return baseMapper.findBy(courseId).size();
    }

    @Override
    public List<Note> getNote(int courseId, int startPage, int pageSize) {
        return baseMapper.getNote(courseId,startPage,pageSize);
    }

    @Override
    public void addNote(int courseId, int userId, int userType, String note) {
        baseMapper.addNote(courseId,userId,userType,note);
    }
}
