package com.seckill.canal;

import com.alibaba.fastjson.JSON;
import com.seckill.goods.pojo.Sku;
import com.seckill.page.feign.SkuPageFeign;
import com.seckill.search.feign.SkuInfoFeign;
import com.seckill.search.pojo.SkuInfo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/*****
 * @Author: http://www.itheima.com
 * @Description: com.seckill.canal.SkuHandler
 ****/
@CanalTable(value = "tb_sku")
@Component
public class SkuHandler implements EntryHandler<Sku>{

    @Autowired
    private SkuInfoFeign skuInfoFeign;

    @Autowired
    private SkuPageFeign skuPageFeign;

    /***
     * 增加数据监听
     * @param sku
     */
    @SneakyThrows
    @Override
    public void insert(Sku sku) {
        //status=2
        if(sku.getStatus().equals("2")){
            //同步索引
            skuInfoFeign.modify(1, JSON.parseObject(JSON.toJSONString(sku), SkuInfo.class));
        }

        //同步静态页
        skuPageFeign.html(sku.getId());
    }

    /***
     * 修改数据监听
     * @param before
     * @param after
     */
    @SneakyThrows
    @Override
    public void update(Sku before, Sku after) {
        System.out.println("update---"+after.getId());
        int type=1;
        if(after.getStatus().equals("2")){
            type=2;
            //同步静态页
            //skuPageFeign.html(after);
        }else if(before.getStatus().equals("2") && after.getStatus().equals("1")){
            type=3;
            //同步静态页
            //skuPageFeign.delHtml(after.getId());
        }
        //同步静态页
        skuPageFeign.html(after.getId());

        skuInfoFeign.modify(type,JSON.parseObject(JSON.toJSONString(after), SkuInfo.class));
    }

    /****
     * 删除数据监听
     * @param sku
     */
    @Override
    public void delete(Sku sku) {
    }
}
