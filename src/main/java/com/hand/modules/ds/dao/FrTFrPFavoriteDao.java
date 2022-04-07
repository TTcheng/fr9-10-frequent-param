package com.hand.modules.ds.dao;

import com.hand.modules.ds.entity.FineParamTemplateEntity;
import com.hand.modules.ds.entity.FrTFrPFavoriteEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * The interface Fr t fr p favorite dao.
 */
@Mapper
public interface FrTFrPFavoriteDao extends BaseMapper<FrTFrPFavoriteEntity> {

    /**
     * 批量插入或更新.
     *
     * @param fineParamTemplateEntities the fine param template entities
     * @return the int
     */
    int batchInsertOrUpdate(@Param("fineParamTemplateEntities") List<FineParamTemplateEntity> fineParamTemplateEntities);
}
