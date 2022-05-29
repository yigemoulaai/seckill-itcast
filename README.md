#  seckill-itcast
2020秒杀  分布式项目 技术包括：redis kafka canal nginx Spring Cloud
开发环境: Elasticsearch 6.2.4 Kafka 2.12-2.5 redis 6.2.3 zookeeper 3.5.6
##  开发文档及 DB.sql  在 dev_doc
### 第一章内容  （文档完整）
  1 module seckill-api
   
  2 module seckill-module
  
  3 module seckill-service
    
   3.1 seckill-user  
   
   3.2 seckill-search
   
   3.3 seckill-canal
   
   3.4 seckill-goods
### 第二章内容 (seckill-Goods ) 
   
+ L1 Sku处理逻辑  
    + M1 热点商品隔离 hotIsolation(List<String> ids)  
        根据热点探测的结果即，修改商品状态,canal监控mysql的binlog处理下游逻辑  
      
    + M2 非热点商品减库存 del(String id)  
        减库存可能的三个状态 1 减库存成功 2 库存不足 3 商品为热点商品  
      
    + M3 多条件搜索品牌数据 findList(Sku sku)
        基于Mybatis的Example创建多条件的查询对象
    + M4 CRUD add(Sku sku)  findById(String id) add(Sku sku)  
     ``` 
     // POJO SkuAct -------
         private String skuId;
         private String activityId;//
         private Integer isDel;  //是否删除
         private List<Sku> skus; //Sku集合
      // POJO Activity -------
          private String id;//
          private String name;//
          private Integer status;//状态：1开启，2未开启
          private Date startdate;//
          private Date begintime;//开始时间，单位：时分秒
          private Date endtime;//结束时间，单位：时分秒
          private Float totalTime;//
          private Integer isDel;// 
          private Integer timeId;
          private SeckillTime seckillTime;
          private List<SeckillTime> seckillTimeList;
     ``` 
    + L2 SkuAct 秒杀活动处理逻辑
        + M1  修改秒杀活动 update(SkuAct skuAct,String id)
        
        + M2  新增商品秒杀活动 add(SkuAct skuAct)
          1. 根据skuAct.activityId查询所有的SkuAct中的所有sku商品,在Activity表中查询秒杀起始和终止时间  
          2. 其中修改商品的秒杀时间，并标记为秒杀商品，将这些修改后的Sku的信息保存至mysql
          3. 把修改后的Sku和SkuAct建立起关联
     + L3 Task 定时任务处理逻辑 task(String jobName, Long time, String id)
        + M1 添加动态定时任务 addDynamicTask cron表达式  {Seconds} {Minutes} 
        {Hours} {DayofMonth} {Month} {DayofWeek} {Year}  
        */10 * * * * ? 每隔10秒执行一次  0 */5 * * * ? 每隔5分钟执行一次
        添加一个动态任务,以当前系统时间+ 等待时间转换为cron表达式  
            + 测试任务的逻辑
            1. 删除所有活动和所有时间以及活动商品信息, 批量加入secTime的集合
            2. 对每个sekTime创建一个秒杀Activity,每个Sku->SkuAct是多对一，关联关系为行数据的关联  
            同时要修改SKU的商品价格以及状态，以及增加静态资源和下游逻辑。
            3. 这里没有体现动态定时任务，只是初始化了秒杀的活动表
      + L4 Activity 活动的处理逻辑
        + M1 Activity的上线/下线 isUp(id,isup)  
        主要修改Activity表中的数据的Status字段 
        + M2 新增一个Activity 
            1. 这里注入了分布式自增id(idWorker),作为唯一性id字段等,设置开始和结束时间  
            2. 插入数据库
### 第三章内容(seckill-Order ) 
+ L1 Sku处理逻辑  
  
     
##  视频同步更新中 
#### uri： https://www.bilibili.com/video/BV1RR4y1u7Ls