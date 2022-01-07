package com.seckill.goods.task.dynamic;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;
public class DynamicListener extends AbstractDistributeOnceElasticJobListener {

    /***
     * 构造函数
     * @param startedTimeoutMilliseconds
     * @param completedTimeoutMilliseconds
     */
    public DynamicListener(long startedTimeoutMilliseconds, long completedTimeoutMilliseconds) {
        super(startedTimeoutMilliseconds, completedTimeoutMilliseconds);
    }

    /***
     * 执行前通知
     * @param shardingContexts
     */
    @Override
    public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts) {
        System.out.println("doBeforeJobExecutedAtLastStarted");
    }

    /***
     * 执行后通知
     * @param shardingContexts
     */
    @Override
    public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts) {
        System.out.println("doAfterJobExecutedAtLastCompleted");
    }
}
