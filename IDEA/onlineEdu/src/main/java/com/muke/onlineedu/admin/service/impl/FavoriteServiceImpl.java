package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.admin.dao.FavoriteDao;
import com.muke.onlineedu.admin.entity.Course;
import com.muke.onlineedu.admin.entity.Favorite;
import com.muke.onlineedu.admin.service.FavoriteService;
import com.muke.onlineedu.common.tool.ResourcePathUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("favoriteService")
public class FavoriteServiceImpl extends ServiceImpl<FavoriteDao,Favorite> implements FavoriteService {
    @Override
    public boolean checkFavorite(int userId, int courseId) {
        return baseMapper.findBy(userId,courseId)!=null;
    }

    @Override
    public void addFavorite(int userId, int courseId) {
        baseMapper.addFavorite(userId,courseId);
    }

    @Override
    public void delFavorite(int userId, int courseId) {
        baseMapper.delFavorite(userId,courseId);
    }

    @Override
    public int getFavoriteCourseRow(int userId) {
        return baseMapper.getFavoriteCourse(userId).size();
    }

    @Override
    public List<Favorite> getFavoriteCourseMessage(String url,int startPage, int pageSize, int userId) {
        List<Favorite> favoriteList = baseMapper.getPartFavoriteCourse(startPage, pageSize, userId);
        for(Favorite favorite:favoriteList){
            Course course = favorite.getCourse();
            course.setImageURL(ResourcePathUtils.getPhotoPath(url)+course.getCourseImgName());
        }
        return favoriteList;
    }
}
