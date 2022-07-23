package com.seckill.monitor;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import com.seckill.goods.feign.SkuFeign;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/*****
 * @Author: lichuang
 * @Description: com.seckill.monitor.MonitorTask
 ****/
@Component
@ElasticSimpleJob(
        cron = "1/5 * * * * ?",
        jobName = "monitortask",
        shardingTotalCount = 1
)
public class MonitorTask implements SimpleJob{

    @Autowired
    private MonitorItemsAccess monitorItemsAccess;

    @Autowired
    private SkuFeign skuFeign;

    /***
     * 执行业务逻辑
     * @param shardingContext
     */
    @SneakyThrows
    @Override
    public void execute(ShardingContext shardingContext) {
        List<String> ids = monitorItemsAccess.loadData();
        for (String id : ids) {
            System.out.println("热点商品ID："+id);
        }

        //热点数据隔离
        skuFeign.hotIsolation(ids);
    }
}
