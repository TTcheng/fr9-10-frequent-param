package com.hand.modules.ds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hand.common.utils.PageUtils;
import com.hand.modules.ds.entity.FineParamTemplateEntity;

import java.util.List;
import java.util.Map;


public interface FineParamTemplateService extends IService<FineParamTemplateEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void dataExtraction();

    List<FineParamTemplateEntity> dataObtain();
}

