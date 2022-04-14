package com.hand.modules.ds.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hand.common.utils.Decrypt;
import com.hand.common.utils.Encrypt;
import com.hand.common.utils.PageUtils;
import com.hand.common.utils.Query;
import com.hand.modules.ds.dao.FineParamTemplateDao;
import com.hand.modules.ds.dao.FrTFrPFavoriteDao;
import com.hand.modules.ds.entity.FineParamTemplateEntity;
import com.hand.modules.ds.entity.FrTFrPFavoriteEntity;
import com.hand.modules.ds.service.FineParamTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service("fineParamTemplateService")
@Transactional(rollbackFor = Exception.class)
public class FineParamTemplateServiceImpl extends ServiceImpl<FineParamTemplateDao, FineParamTemplateEntity> implements FineParamTemplateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FineParamTemplateServiceImpl.class);

    @Autowired
    private FrTFrPFavoriteDao frTFrPFavoriteDao;

    @Autowired
    private FineParamTemplateDao fineParamTemplateDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FineParamTemplateEntity> page = this.page(
                new Query<FineParamTemplateEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public void dataExtraction() {
        //查出帆软9数据,数据量不大，一次性查出
        QueryWrapper<FrTFrPFavoriteEntity> queryWrapper = new QueryWrapper<>();
        int pageNum = 1;
        final int pageSize = 1000;
        Page<FrTFrPFavoriteEntity> page;
        do {
            page = PageHelper.startPage(pageNum, pageSize);
            pageNum ++;
            List<FrTFrPFavoriteEntity> fr9Params = frTFrPFavoriteDao.selectList(queryWrapper);
            if (CollectionUtils.isEmpty(fr9Params)) {
                break;
            }
            List<FineParamTemplateEntity> fr10Params = new ArrayList<>();
            //使用加密算法将字段tpgroup加密后保存到帆软10表中
            for (FrTFrPFavoriteEntity fr : fr9Params) {
                FineParamTemplateEntity fineParamTemplateEntity = new FineParamTemplateEntity();
                String encrypt = Encrypt.encrypt(fr.getTpgroup());
                fineParamTemplateEntity.setId(UUID.randomUUID().toString());
                fineParamTemplateEntity.setTemplateid(fr.getTemplateid());
                fineParamTemplateEntity.setUsername(fr.getUsername());
                fineParamTemplateEntity.setTpgroup(encrypt);
                fr10Params.add(fineParamTemplateEntity);
            }
            //批量插入
            frTFrPFavoriteDao.batchInsertOrUpdate(fr10Params);
        } while (page.toPageInfo().isHasNextPage());
        LOGGER.info("===============常用参数迁移完成，共{}条=======================", page.getTotal());
    }

    @Override
    public List<FineParamTemplateEntity> dataObtain() {
        List<FineParamTemplateEntity> entityList = fineParamTemplateDao.selectList(new QueryWrapper<>());
        for (FineParamTemplateEntity entity : entityList) {
            entity.setTpgroup(Decrypt.decrypt(entity.getTpgroup()));
        }
        return entityList;
    }

}