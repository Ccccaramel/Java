package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.Menu;
import com.ding.office.info.MenuInfo;
import com.ding.office.vo.MenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<MenuInfo> getAllMenu();

    void addMenu(@Param("menuVo") MenuVo menuVo);

    void updateMenu(@Param("menuVo") MenuVo menuVo);

    List<MenuInfo> getRoleMenuTree(Integer roleId);

    List<String> getCurrentUserPower(String[] powerArray);

    void deleteMenu(@Param("menuVo") MenuVo menuVo);
}
