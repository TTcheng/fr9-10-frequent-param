package com.hand.modules.ds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hand.common.utils.PageUtils;
import com.hand.modules.ds.entity.FrTFrPFavoriteEntity;

import java.util.Map;


public interface FrTFrPFavoriteService extends IService<FrTFrPFavoriteEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

