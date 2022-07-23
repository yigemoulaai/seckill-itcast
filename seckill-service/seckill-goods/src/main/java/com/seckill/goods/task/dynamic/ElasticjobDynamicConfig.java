package com.seckill.goods.task.dynamic;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/*****

 * 动态定时任务案例配置
 ****/
@Configuration
public class ElasticjobDynamicConfig {

    @Value("${zkserver}")
    private String zkserver;
    @Value("${zknamespace}")
    private String zknamespace;

    /****
     * 1.配置初始化数据
     */
    @Bean
    public ZookeeperConfiguration zkConfig(){
        //1.Zookeeper地址
        //2.定时任务命名空间
        return new ZookeeperConfiguration(zkserver,zknamespace);
    }

    /****
     * 2.注册初始化数据
     */
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter registryCenter(ZookeeperConfiguration zkConfig){
        return new ZookeeperRegistryCenter(zkConfig);
    }
    /****
     * 监听器
     */
    @Bean
    public DynamicListener dynamicListener(){
        return new DynamicListener(10000L,100000L);
    }
    @Autowired
    private DynamicListener dynamicListener;
    @Autowired
    private ZookeeperRegistryCenter zookeeperRegistryCenter;
    /****
     * 3.动态添加定时任务案例
     */
    public void addDynamicTask(String jobName, String cron,
                               int shardingTotalCount,String id, SimpleJob instance){
        //1.添加Elastjob-lite的任务作业器
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(
                new SimpleJobConfiguration(
                        JobCoreConfiguration.newBuilder(jobName, cron, shardingTotalCount)
                                .jobParameter(id)  //额外的参数
                                .build(),
                        instance.getClass().getName())
        ).overwrite(true).build();
        //2.将Lite的任务作业器添加到Spring的任务启动器中，并初始化
        new SpringJobScheduler(instance,zookeeperRegistryCenter,
                liteJobConfiguration,dynamicListener).init();
    }
    /****
     * 时间转换成Cron表达式
     * "1/5 * * * * ?"
     */
    private static String cron="ss mm HH dd MM ? yyyy";
    public static String date2cron(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(cron);
        return simpleDateFormat.format(date);
    }
}
