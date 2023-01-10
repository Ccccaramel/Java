package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.UserWithTeam;
import com.ding.hyld.info.UserWithTeamInfo;
import com.ding.hyld.vo.UserWithTeamVo;
import com.ding.hyld.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserWithTeamMapper extends BaseMapper<UserWithTeam> {

    List<UserWithTeamInfo> searchTeam(@Param("page") Page page, @Param("userWithTeamVo") UserWithTeamVo userWithTeamVo);

    List<UserWithTeamInfo> findBy(@Param("userWithTeamVo") UserWithTeamVo userWithTeamVo);

    void add(@Param("userWithTeamVo") UserWithTeamVo userWithTeamVo);

    /**
     * 直接解除关联
     * @param userWithTeamVo
     */
    void relieveTeam(@Param("userWithTeamVo") UserWithTeamVo userWithTeamVo);

    void saveCheckInfo(Integer relationId, String controllerPreparePageNewName, String teamMainPageNewName, Integer checkStatus);

    void teamExamineCheck(@Param("userWithTeamVo") UserWithTeamVo userWithTeamVo);

    void addViceCaptain(@Param("userWithTeamVo") UserWithTeamVo userWithTeamVo);

    void allRelieveTeam(@Param("userWithTeamVo") UserWithTeamVo userWithTeamVo);

    /**
     * 更改关联状态
     * @param userWithTeamVo
     */
    void changeRelationStatus(@Param("userWithTeamVo") UserWithTeamVo userWithTeamVo);

    /**
     * 主页 > 站队搜索
     * @param page
     * @param userWithTeamVo
     * @return
     */
    List<UserWithTeamInfo> searchValidTeamInfo(@Param("page") Page page,@Param("userWithTeamVo") UserWithTeamVo userWithTeamVo);

    List<UserWithTeamInfo> playerFindTeamBy(@Param("userWithTeamVo") UserWithTeamVo userWithTeamVo);

    void teamTransfer(@Param("userWithTeamVo") UserWithTeamVo userWithTeamVo);

    UserWithTeamInfo findById(Integer id);

    void updateTeamCreditScore(@Param("userWithTeamVo") UserWithTeamVo userWithTeamVo);

    Integer searchTeamOfPage(@Param("userWithTeamVo") UserWithTeamVo userWithTeamVo);

    Integer searchValidTeamInfoOfPage(@Param("userWithTeamVo") UserWithTeamVo userWithTeamVo);
}

