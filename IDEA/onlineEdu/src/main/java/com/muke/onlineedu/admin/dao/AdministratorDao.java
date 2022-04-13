package com.muke.onlineedu.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.Admin;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface AdministratorDao extends BaseMapper<Admin> {
    @Insert("insert into administrator(gmId,gmEmail,gmTel,gmPassword,gmPower) " +
            "values(#{administratorEntity.gmId}, " +
            "#{administratorEntity.gmEmail}, " +
            "#{administratorEntity.gmTel}, " +
            "#{administratorEntity.gmPassword}, " +
            "#{administratorEntity.gmPower})")
    int insert(@Param("administratorEntity") Admin administratorEntity);

    @Insert("insert into administrator(gmPassword,gmPower,gmAccountStatus) " +
            "values(#{administratorEntity.gmPassword}, " +
            "#{administratorEntity.gmPower}, " +
            "#{administratorEntity.gmAccountStatus})")
    @Options(useGeneratedKeys = true,keyProperty = "gmId", keyColumn = "gmId")
    void addGM(@Param("administratorEntity") Admin administratorEntity);

    @Delete("delete from administrator where gmId=#{administratorEntity.gmId}")
    int delete(@Param("administratorEntity") Admin administratorEntity);

    @Update("update administrator " +
            "set gmAccountStatus=#{gmAccountStatus} " +
            "where gmId=#{gmId}")
    int updateGMAccountStatus(@Param("gmAccountStatus") int gmAccountStatus,@Param("gmId") int gmId);

    @Update("update administrator set gmEmail=#{email},gmTel=#{tel} where gmId=#{gmId}")
    void updateGMInfo(@Param("email") String email,@Param("tel") String tel,@Param("gmId") int gmId);

    @Update("update administrator set gmAccountStatus=#{sign} where gmId=#{gmId}")
    void updatePower(@Param("sign") int sign,@Param("gmId") int gmId);

    @Update("update administrator set gmPassword=#{password} where gmId=#{gmId}")
    void changePassword(@Param("password") String password,@Param("gmId") int gmId);

    @Select("select * from administrator where gmId=#{gmId} and gmPassword=#{gmPassword}")
    List<Admin> selectById(@Param("gmId") Integer gmId, @Param("gmPassword") String gmPassword);

    @Select("select count(*) from administrator where gmTel=#{gmTel} and gmPassword=#{gmPassword}")
    int selectByTel(@Param("gmTel") String gmTel,@Param("gmPassword") String gmPassword);

    @Select("select count(*) from administrator where gmEmail=#{gmEmail} and gmPassword=#{gmPassword}")
    int selectByEmail(@Param("gmEmail") String gmEmail,@Param("gmPassword") String gmPassword);

    @Select("select gmAccountStatus from administrator where gmId=#{gmId}")
    int getGMAccountStatus(@Param("gmId") Integer gmId);

    @Select("select * from administrator where gmId=#{gmId}")
    List<Admin> selectBy(@Param("gmId") Integer gmId);

    @Select("select * from administrator " +
            "where gmPower<#{gmPower} " +
            "and( gmId LIKE '%${key}%' " +
            "or gmEmail LIKE '%${key}%'  " +
            "or gmTel LIKE '%${key}%' " +
            "or gmPower LIKE '%${key}%') " +
            "limit #{startPage},#{pageSize}")
    List<Admin> getPartAccounts(@Param("gmPower")int gmPower,@Param("key") String key,@Param("startPage") int startPage,@Param("pageSize") int pageSize);

    @Select("select * from administrator " +
            "where gmPower<#{gmPower} " +
            "and( gmId LIKE '%${key}%' " +
            "or gmEmail LIKE '%${key}%'  " +
            "or gmTel LIKE '%${key}%' " +
            "or gmPower LIKE '%${key}%')")
    List<Admin> getAllAccounts(@Param("gmPower")int gmPower,@Param("key") String key);

    @Select("SELECT accountClass from administrator JOIN account_status ON gmAccountStatus=accountNumber WHERE gmId=#{gmId}")
    String getAccountStatusStr(@Param("gmId") int gmId);
}
