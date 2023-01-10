package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.NormalAttack;
import com.ding.hyld.entity.NormalAttackAttribute;
import com.ding.hyld.info.NormalAttackAttributeInfo;
import com.ding.hyld.info.NormalAttackInfo;
import com.ding.hyld.vo.NormalAttackAttributeVo;
import com.ding.hyld.vo.NormalAttackVo;
import com.ding.hyld.vo.Page;

import java.util.List;

public interface NormalAttackAttributeService extends IService<NormalAttackAttribute> {
    List<NormalAttackAttributeInfo> searchNormalAttackAttribute(Page page, NormalAttackAttributeVo normalAttackAttributeVo);

    void update(NormalAttackAttributeVo normalAttackAttributeVo);

    void add(NormalAttackAttributeVo normalAttackAttributeVo);

    void delete(NormalAttackAttributeVo normalAttackAttributeVo);

    Integer searchNormalAttackAttributeOfPage(NormalAttackAttributeVo normalAttackAttributeVo);
}
