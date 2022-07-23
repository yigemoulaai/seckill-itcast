package com.seckill.page.service;

import java.util.Map;

/*****
 * @Author: lichuang
 * @Project: seckillproject
 * @Description: com.seckill.page.service.SkuPageService
 ****/
public interface SkuPageService {

    /***
     * 删除商品静态页
     * @param name 静态页名称
     * @param htmlPath 静态页存储路径
     */
    void delHtml(String name,String htmlPath);

    /****
     * 生成静态页
     * @param dataMap
     */
    void itemPage(Map<String,Object> dataMap) throws Exception;
}
