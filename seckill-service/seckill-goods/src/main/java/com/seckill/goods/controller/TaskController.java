package com.seckill.goods.controller;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.github.pagehelper.PageInfo;
import com.seckill.goods.pojo.Activity;
import com.seckill.goods.pojo.SeckillTime;
import com.seckill.goods.pojo.Sku;
import com.seckill.goods.service.ActivityService;
import com.seckill.goods.service.SeckillTimeService;
import com.seckill.goods.service.SkuActService;
import com.seckill.goods.service.SkuService;
import com.seckill.goods.task.dynamic.DynamicTask;
import com.seckill.goods.task.dynamic.ElasticjobDynamicConfig;
import com.seckill.goods.task.statictask.ModifySeckillTimesHandler;
import com.seckill.util.Result;
import com.seckill.util.StatusCode;
import com.seckill.util.TimeUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*****
 * @Author: http://www.itheima.com
 * @Description: com.seckill.goods.controller.TaskController
 ****/
@RestController
@RequestMapping(value = "/task")
//@CrossOrigin
public class TaskController {

    @Autowired
    private ElasticjobDynamicConfig elasticjobDynamicConfig;

    /***
     * 动态定时任务案例测试
     */
    @GetMapping
    public Result task(String jobName, Long time, String id) {
        //在当前时间往后延迟time毫秒执行
        String cron = ElasticjobDynamicConfig.date2cron(new Date(System.currentTimeMillis() + time));

        elasticjobDynamicConfig.addDynamicTask(jobName, cron, 1, id, new DynamicTask());
        return new Result(true, StatusCode.OK, "执行成功！");
    }

    @Autowired
    private SeckillTimeService seckillTimeService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private SkuActService skuActService;

    @Autowired
    private SkuService skuService;


    /***
     * 动态定时任务案例测试
     */
    @GetMapping(value = "/init/{count}")
    public Result init(@PathVariable(value = "count") Integer count) throws Exception {
        if(count==400){
            //删除所有数据
            skuActService.deleteAllIndex();
        }
        if (count == 603) {
            //初始化数据
            //删除所有活动和所有时间以及活动商品信息
            seckillTimeService.deleteAll();
            activityService.deleteAll();
            skuActService.deleteAll();
            //所有商品更新成非秒杀商品
            skuService.modifySku();

            //批量增加时间段
            seckillTimeService.addTimes(initSeckillTimes());

            //1.查询所有时间段
            List<SeckillTime> seckillTimes = seckillTimeService.findAllValidTimes();

            //查询前500个商品个商品
            PageInfo<Sku> skuPage = skuService.findPage(1, 500);
            if (skuPage != null && skuPage.getList() != null && skuPage.getList().size() >= 500) {
                //商品结合
                List<Sku> skus = skuPage.getList();
                int index = 0;

                for (SeckillTime seckillTime : seckillTimes) {
                    //2.给每个时间段创建一个活动
                    Activity activity = activityService.createActivity(seckillTime);

                    //3.给每个活动增加50件商品
                    skuActService.addList(activity, skus.subList(index, index + 50));
                    index += 50;
                }
            }
            System.out.println("=================OK!总条数：" + skuPage.getList().size());
        }

        return new Result(true, StatusCode.OK, "执行成功！");
    }

    /***
     * 初始化时间段
     * @return
     */
    public List<SeckillTime> initSeckillTimes() {
        SeckillTime seckillTime1 = new SeckillTime();
        seckillTime1.setStarttime(TimeUtil.getTimes(0));
        seckillTime1.setEndtime(TimeUtil.getTimes(8));
        seckillTime1.setTotalTime(8f);
        seckillTime1.setStatus(1);
        seckillTime1.setName("00:00");
        seckillTime1.setSort(1);

        SeckillTime seckillTime2 = new SeckillTime();
        seckillTime2.setStarttime(TimeUtil.getTimes(8));
        seckillTime2.setEndtime(TimeUtil.getTimes(10));
        seckillTime2.setTotalTime(2f);
        seckillTime2.setStatus(1);
        seckillTime2.setName("08:00");
        seckillTime2.setSort(2);

        SeckillTime seckillTime3 = new SeckillTime();
        seckillTime3.setStarttime(TimeUtil.getTimes(10));
        seckillTime3.setEndtime(TimeUtil.getTimes(12));
        seckillTime3.setTotalTime(2f);
        seckillTime3.setStatus(1);
        seckillTime3.setName("10:00");
        seckillTime3.setSort(3);

        SeckillTime seckillTime4 = new SeckillTime();
        seckillTime4.setStarttime(TimeUtil.getTimes(12));
        seckillTime4.setEndtime(TimeUtil.getTimes(14));
        seckillTime4.setTotalTime(2f);
        seckillTime4.setStatus(1);
        seckillTime4.setName("12:00");
        seckillTime4.setSort(4);

        SeckillTime seckillTime5 = new SeckillTime();
        seckillTime5.setStarttime(TimeUtil.getTimes(14));
        seckillTime5.setEndtime(TimeUtil.getTimes(16));
        seckillTime5.setTotalTime(2f);
        seckillTime5.setStatus(1);
        seckillTime5.setName("14:00");
        seckillTime5.setSort(5);

        SeckillTime seckillTime6 = new SeckillTime();
        seckillTime6.setStarttime(TimeUtil.getTimes(16));
        seckillTime6.setEndtime(TimeUtil.getTimes(18));
        seckillTime6.setTotalTime(2f);
        seckillTime6.setStatus(1);
        seckillTime6.setName("16:00");
        seckillTime6.setSort(6);

        SeckillTime seckillTime7 = new SeckillTime();
        seckillTime7.setStarttime(TimeUtil.getTimes(18));
        seckillTime7.setEndtime(TimeUtil.getTimes(20));
        seckillTime7.setTotalTime(2f);
        seckillTime7.setStatus(1);
        seckillTime7.setName("18:00");
        seckillTime7.setSort(7);

        SeckillTime seckillTime8 = new SeckillTime();
        seckillTime8.setStarttime(TimeUtil.getTimes(20));
        seckillTime8.setEndtime(TimeUtil.getTimes(22));
        seckillTime8.setTotalTime(2f);
        seckillTime8.setStatus(1);
        seckillTime8.setName("20:00");
        seckillTime8.setSort(8);

        SeckillTime seckillTime9 = new SeckillTime();
        seckillTime9.setStarttime(TimeUtil.getTimes(22));
        seckillTime9.setEndtime(TimeUtil.getTimes(0));
        seckillTime9.setTotalTime(2f);
        seckillTime9.setStatus(1);
        seckillTime9.setName("22:00");
        seckillTime9.setSort(9);
        List<SeckillTime> seckillTimes = new ArrayList<SeckillTime>();
        seckillTimes.add(seckillTime1);
        seckillTimes.add(seckillTime2);
        seckillTimes.add(seckillTime3);
        seckillTimes.add(seckillTime4);
        seckillTimes.add(seckillTime5);
        seckillTimes.add(seckillTime6);
        seckillTimes.add(seckillTime7);
        seckillTimes.add(seckillTime8);
        seckillTimes.add(seckillTime9);
        return seckillTimes;
    }
}
