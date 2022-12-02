package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.User;
import com.ding.hyld.info.UserInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UserVo;
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

    UserInfo findByGameRoleCommentId(Integer gameRoleCommentId);

    void addEx(Integer userId, Integer i);

    User findByQqOpenId(String qqOpenId);
}
