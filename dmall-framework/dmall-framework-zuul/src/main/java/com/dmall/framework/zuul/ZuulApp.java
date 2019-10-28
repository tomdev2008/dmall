package com.dmall.framework.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @description: 网关启动类
 * 访问路由规则 : API网关地址+访问的服务名称+接口URI
 * @author: created by yuhang on 2019/10/16 20:35
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
//@EnableApolloConfig
public class ZuulApp {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApp.class, args);
    }

}