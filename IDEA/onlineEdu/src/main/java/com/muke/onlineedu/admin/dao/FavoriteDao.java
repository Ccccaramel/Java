package com.muke.onlineedu.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.Favorite;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavoriteDao extends BaseMapper<Favorite> {
    Favorite findBy(int userId, int courseId);
    void addFavorite(int userId, int courseId);
    void delFavorite(int userId, int courseId);
    List<Favorite> getFavoriteCourse(int userId);
    List<Favorite> getPartFavoriteCourse(int startPage, int pageSize, int userId);
}
