package com.seckill.page.service.impl;

import com.seckill.page.process.BaseProcess;
import com.seckill.page.service.SkuPageService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

@Service
public class SkuPageServiceImpl extends BaseProcess implements SkuPageService {


    /***
     * 删除商品静态页
     * @param name 静态页名称
     * @param htmlPath 静态页存储路径
     */
    @Override
    public void delHtml(String name, String htmlPath) {
        File file = new File(htmlPath, name);
        if(file.exists()){
            file.delete();
        }
    }

    /***
     * 生成静态页
     * @param dataMap
     */
    @Override
    public void itemPage(Map<String, Object> dataMap) throws Exception {
        super.writerPage(dataMap);
    }
}
