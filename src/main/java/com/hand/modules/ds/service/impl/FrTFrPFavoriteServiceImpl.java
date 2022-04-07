package com.hand.modules.ds.service.impl;

import com.hand.common.utils.PageUtils;
import com.hand.common.utils.Query;
import com.hand.modules.ds.dao.FrTFrPFavoriteDao;
import com.hand.modules.ds.entity.FrTFrPFavoriteEntity;
import com.hand.modules.ds.service.FrTFrPFavoriteService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("frTFrPFavoriteService")
public class FrTFrPFavoriteServiceImpl extends ServiceImpl<FrTFrPFavoriteDao, FrTFrPFavoriteEntity> implements FrTFrPFavoriteService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FrTFrPFavoriteEntity> page = this.page(
                new Query<FrTFrPFavoriteEntity>().getPage(params),
                new QueryWrapper<FrTFrPFavoriteEntity>()
        );

        return new PageUtils(page);
    }

}