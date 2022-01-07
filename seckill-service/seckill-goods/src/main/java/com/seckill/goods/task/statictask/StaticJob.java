package com.seckill.goods.task.statictask;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/*****
 * @Author: http://www.itheima.com
 * @Description: com.seckill.goods.task.statictask.StaticJob
 ****/
@Component
@ElasticSimpleJob(
        jobName = "updatetask",   //和定时任务命名空间保持一致
        cron = "1/5 * * * * ?",  //任务执行周期
        shardingTotalCount = 1    //分片
)
public class StaticJob implements SimpleJob {

    /*****
     * 业务处理方法
     * @param shardingContext
     */
    @Override
    public void execute(ShardingContext shardingContext) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        //System.out.println("时间："+simpleDateFormat.format(new Date()));
    }
}
