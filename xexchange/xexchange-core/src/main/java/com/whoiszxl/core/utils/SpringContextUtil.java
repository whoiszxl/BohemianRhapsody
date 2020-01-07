package com.whoiszxl.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @description: spring对象获取工具
 * @author: whoiszxl
 * @create: 2019-09-15
 **/
@Configuration
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取spring容器的应用上下文对象
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过bean名称从spring容器中拿到对象
     *
     * @param beanName bean名称
     * @return spring实例对象
     */
    public static Object getBean(String beanName) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String a : beanDefinitionNames) {
            System.out.println("所有：" + a);
        }
        return applicationContext.getBean(beanName);
    }

    /**
     * 通过class类型从spring容器中拿到对象
     *
     * @param c bean的类型
     * @return spring实例对象
     */
    public static Object getBean(Class c) {
        return applicationContext.getBean(c);
    }


    public static String getWalletFeignName(String currencyName) {
        return "com.whoiszxl.wallet.feign." + currencyName + "Client";
    }
}

