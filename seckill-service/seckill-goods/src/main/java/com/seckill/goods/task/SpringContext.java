package com.seckill.goods.task;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/*****
 * @Author:
 * @Description:
 ****/
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext act;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        act = applicationContext;
    }

    public static <T> T getBean(Class clazz){
        return act.getBean((Class<T>) clazz);
    }
 }
