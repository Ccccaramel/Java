package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.User;
import com.ding.office.info.UserInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User login(String account, String password);

    void register(@Param("userVo") UserVo userVo);

    User findByName(String name);

    UserInfo findById(Integer id);

    void updateUserInfo(@Param("userVo") UserVo userVo);

    List<UserInfo> searchUser(@Param("page") Page page,@Param("userVo") UserVo userVo);

    void saveUser(@Param("userVo") UserVo userVo);

    void saveUserPassword(@Param("userVo") UserVo userVo);

    void saveHeadPortrait(@Param("userVo") UserVo userVo);

    UserInfo findByTopicId(Integer topicId);

    UserInfo findByBlogRemarkId(Integer blogRemarkId);

    UserInfo findByGameRoleCommentId(Integer gameRoleCommentId);

    void addEx(Integer userId, Integer i);

    User findByQqOpenId(String qqOpenId);

    User findByEmail(String email);

    void bindEmail(@Param("userVo") UserVo userVo);

    void unbindEmail(Integer userId);

    void updatePassword(@Param("user") User user);

    Integer searchUserOfPage(@Param("userVo") UserVo userVo);

    void updateCoin(@Param("userVo") UserVo userVo);

    void giftCoin();

    UserInfo findBriefInfoById(Integer id);
}
