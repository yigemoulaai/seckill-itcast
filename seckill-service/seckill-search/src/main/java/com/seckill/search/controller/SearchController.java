package com.seckill.search.controller;

import com.alibaba.fastjson.JSON;
import com.seckill.goods.pojo.Sku;
import com.seckill.search.pojo.SkuInfo;
import com.seckill.search.service.SkuInfoService;
import com.seckill.util.Result;
import com.seckill.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/*****
 * @Author: lichuang
 * @Project: seckillproject
 * @Description: com.seckill.search.controller.SearchController
 ****/
@RestController
@RequestMapping(value = "/search")
@CrossOrigin
public class SearchController {

    @Autowired
    private SkuInfoService skuInfoService;

    /****
     * 分页查询秒杀商品
     */
    @GetMapping
    public Page<SkuInfo> search(@RequestParam(required = false)Map<String,String> searchMap){
        return skuInfoService.search(searchMap);
    }

    /***
     * 增量操作  ->删除索引   type=3
     *           ->修改索引   type=2
     *           ->添加索引   type=1
     */
    @PostMapping(value = "/modify/{type}")
    public Result modify(@PathVariable(value = "type")Integer type,@RequestBody SkuInfo skuInfo){
        //索引更新
        skuInfoService.modify(type,skuInfo);
        return new Result(true,StatusCode.OK,"操作成功");
    }
    /***
     * 修改Sku
     * @param type
     * @param sku
     * @return
     */
    @PostMapping(value = "/modify/sku/{type}")
    public Result modifySku(@PathVariable(value = "type")Integer type,@RequestBody Sku sku){
        //索引更新
        SkuInfo skuInfo = JSON.parseObject(JSON.toJSONString(sku),SkuInfo.class);
        skuInfoService.modify(type,skuInfo);
        return new Result(true,StatusCode.OK,"操作成功！");
    }

    @GetMapping(value = "/del/all")
    void deleteAll(){
        skuInfoService.deleteAll();
    }

    /***
     * 批量导入
     */
    @GetMapping(value = "/add/all")
    public Result addAll(){
        //批量导入
        skuInfoService.addAll();
        return new Result(true, StatusCode.OK,"批量导入成功！");
    }
}
