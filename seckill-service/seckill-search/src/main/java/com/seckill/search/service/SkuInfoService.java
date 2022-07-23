package com.seckill.search.service;

import com.seckill.search.pojo.SkuInfo;
import org.springframework.data.domain.Page;

import java.util.Map;
public interface SkuInfoService {

    /***
     * 秒杀商品搜索
     */
    Page<SkuInfo> search(Map<String,String> searchMap);
    /***
     * 增量操作  ->删除索引   type=3
     *           ->修改索引   type=2
     *           ->添加索引   type=1
     */
    void modify(Integer type, SkuInfo skuInfo);
    /***
     * 批量导入
     */
    void addAll();
    void deleteAll();
}
