package com.seckill.search.feign;

import com.seckill.goods.pojo.Sku;
import com.seckill.search.pojo.SkuInfo;
import com.seckill.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/*****
 * @Author: http://www.itheima.com
 * @Project: seckillproject
 * @Description: com.seckill.search.feign.SkuInfoFeign
 ****/
@FeignClient(name = "seckill-search")
public interface SkuInfoFeign {

    /***
     * 增量操作  ->删除索引   type=3
     *           ->修改索引   type=2
     *           ->添加索引   type=1
     */
    @PostMapping(value = "/search/modify/{type}")
    Result modify(@PathVariable(value = "type")Integer type,@RequestBody SkuInfo skuInfo);

    /***
     * 增量操作  ->删除索引   type=3
     *           ->修改索引   type=2
     *           ->添加索引   type=1
     */
    @PostMapping(value = "/search/modify/sku/{type}")
    Result modifySku(@PathVariable(value = "type")Integer type,@RequestBody Sku sku);

    @GetMapping(value = "/search/del/all")
    void deleteAll();
}
