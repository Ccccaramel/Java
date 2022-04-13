package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.Note;

import java.util.List;

public interface NoteService extends IService<Note> {
    int getNoteRow(int courseId);
    List<Note> getNote(int courseId, int startPage, int pageSize);
    void addNote(int courseId, int userId, int userType, String note);
}
