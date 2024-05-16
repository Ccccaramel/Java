package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.EmailCode;
import com.ding.office.info.EmailCodeInfo;
import com.ding.office.vo.EmailCodeVo;
import com.ding.office.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmailCodeMapper extends BaseMapper<EmailCode> {
    List<EmailCodeInfo> searchEmailCode(@Param("page") Page page, @Param("emailCodeVo") EmailCodeVo emailCodeVo);

    void add(@Param("emailCodeVo") EmailCodeVo emailCodeVo);

    List<EmailCodeInfo> findBy(@Param("emailCodeVo") EmailCodeVo emailCodeVo);
}
