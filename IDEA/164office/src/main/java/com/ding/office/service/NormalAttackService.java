package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.NormalAttack;
import com.ding.office.info.NormalAttackInfo;
import com.ding.office.vo.NormalAttackVo;
import com.ding.office.vo.Page;

import java.util.List;

public interface NormalAttackService extends IService<NormalAttack> {
    List<NormalAttackInfo> searchNormalAttack(Page page, NormalAttackVo normalAttackVo);

    void update(NormalAttackVo normalAttackVo);

    void add(NormalAttackVo normalAttackVo);

    void delete(NormalAttackVo normalAttackVo);

    Integer searchNormalAttackOfPage(NormalAttackVo normalAttackVo);
}
