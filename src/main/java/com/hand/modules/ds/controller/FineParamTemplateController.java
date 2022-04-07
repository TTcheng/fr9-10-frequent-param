package com.hand.modules.ds.controller;

import com.hand.common.utils.R;
import com.hand.modules.ds.entity.FineParamTemplateEntity;
import com.hand.modules.ds.service.FineParamTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/ds/fineparamtemplate")
public class FineParamTemplateController {
    @Autowired
    private FineParamTemplateService fineParamTemplateService;


    /**
     * 帆软9数据同步到帆软10
     */
    @RequestMapping("/dataExtraction")
    @ResponseBody
    public R dataExtraction() {
        fineParamTemplateService.dataExtraction();
        return R.ok("帆软9数据同步到帆软10成功!");
    }

    /**
     * 从帆软10中获取数据并解密
     */
    @RequestMapping("/dataObtain")
    @ResponseBody
    public R dataObtain() {
        List<FineParamTemplateEntity> entityList = fineParamTemplateService.dataObtain();
        return R.ok().put("data", entityList);
    }

}
