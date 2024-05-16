package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.NormalAttackAttribute;
import com.ding.office.info.NormalAttackAttributeInfo;
import com.ding.office.vo.NormalAttackAttributeVo;
import com.ding.office.vo.Page;

import java.util.List;

public interface NormalAttackAttributeService extends IService<NormalAttackAttribute> {
    List<NormalAttackAttributeInfo> searchNormalAttackAttribute(Page page, NormalAttackAttributeVo normalAttackAttributeVo);

    void update(NormalAttackAttributeVo normalAttackAttributeVo);

    void add(NormalAttackAttributeVo normalAttackAttributeVo);

    void delete(NormalAttackAttributeVo normalAttackAttributeVo);

    Integer searchNormalAttackAttributeOfPage(NormalAttackAttributeVo normalAttackAttributeVo);
}
