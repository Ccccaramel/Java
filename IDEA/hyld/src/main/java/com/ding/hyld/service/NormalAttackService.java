package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.NormalAttack;
import com.ding.hyld.entity.StarPower;
import com.ding.hyld.info.NormalAttackInfo;
import com.ding.hyld.info.StarPowerInfo;
import com.ding.hyld.vo.NormalAttackVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.StarPowerVo;

import java.util.List;

public interface NormalAttackService extends IService<NormalAttack> {
    List<NormalAttackInfo> searchNormalAttack(Page page, NormalAttackVo normalAttackVo);

    void update(NormalAttackVo normalAttackVo);

    void add(NormalAttackVo normalAttackVo);

    void delete(NormalAttackVo normalAttackVo);

    Integer searchNormalAttackOfPage(NormalAttackVo normalAttackVo);
}
