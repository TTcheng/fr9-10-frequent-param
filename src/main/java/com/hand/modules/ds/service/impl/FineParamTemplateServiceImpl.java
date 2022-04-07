package com.hand.modules.ds.service.impl;

import com.hand.common.utils.Decrypt;
import com.hand.common.utils.Encrypt;
import com.hand.common.utils.PageUtils;
import com.hand.common.utils.Query;
import com.hand.modules.ds.dao.FineParamTemplateDao;
import com.hand.modules.ds.dao.FrTFrPFavoriteDao;
import com.hand.modules.ds.entity.FineParamTemplateEntity;
import com.hand.modules.ds.entity.FrTFrPFavoriteEntity;
import com.hand.modules.ds.service.FineParamTemplateService;
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


@Service("fineParamTemplateService")
@Transactional(rollbackFor = Exception.class)
public class FineParamTemplateServiceImpl extends ServiceImpl<FineParamTemplateDao, FineParamTemplateEntity> implements FineParamTemplateService {

    @Autowired
    private FrTFrPFavoriteDao frTFrPFavoriteDao;

    @Autowired
    private FineParamTemplateDao fineParamTemplateDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FineParamTemplateEntity> page = this.page(
                new Query<FineParamTemplateEntity>().getPage(params),
                new QueryWrapper<FineParamTemplateEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void dataExtraction() {
        //查出帆软9数据,数据量不大，一次性查出
        List<FrTFrPFavoriteEntity> frTFrPFavoriteEntities = frTFrPFavoriteDao.selectList(new QueryWrapper<>());
        List<FineParamTemplateEntity> entityList = new ArrayList<>();
        //使用加密算法将字段tpgroup加密后保存到帆软10表中
        for (FrTFrPFavoriteEntity fr:frTFrPFavoriteEntities) {
            FineParamTemplateEntity fineParamTemplateEntity = new FineParamTemplateEntity();
            String encrypt = Encrypt.encrypt(fr.getTpgroup());
            fineParamTemplateEntity.setId(UUID.randomUUID().toString());
            fineParamTemplateEntity.setTemplateid(fr.getTemplateid());
            fineParamTemplateEntity.setUsername(fr.getUsername());
            fineParamTemplateEntity.setTpgroup(encrypt);
            entityList.add(fineParamTemplateEntity);
        }
        //批量插入
        frTFrPFavoriteDao.batchInsertOrUpdate(entityList);
        entityList.clear();
    }

    @Override
    public List<FineParamTemplateEntity> dataObtain() {
        List<FineParamTemplateEntity> entityList = fineParamTemplateDao.selectList(new QueryWrapper<>());
        for (int i = 0; i < entityList.size(); i++) {
            entityList.get(i).setTpgroup(Decrypt.decrypt(entityList.get(i).getTpgroup()));
        }
        return entityList;
    }

}