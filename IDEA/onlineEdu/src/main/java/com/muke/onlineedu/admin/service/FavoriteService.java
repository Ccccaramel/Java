package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.Favorite;

import java.util.List;

public interface FavoriteService extends IService<Favorite> {
    boolean checkFavorite(int userId, int courseId);
    void addFavorite(int userId, int courseId);
    void delFavorite(int userId, int courseId);
    int getFavoriteCourseRow(int userId);
    List<Favorite> getFavoriteCourseMessage(String url,int startPage, int pageSize, int userId);
}
