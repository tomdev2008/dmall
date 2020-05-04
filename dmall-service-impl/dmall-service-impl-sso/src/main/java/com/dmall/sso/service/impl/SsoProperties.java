package com.dmall.sso.service.impl;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

/**
 * @description: SsoAdminProperties
 * @author: created by hang.yu on 2020/1/9 23:28
 */
@Data
@ConfigurationProperties(prefix = "dmall.sso")
public class SsoProperties {

    /**
     * 后台登录成功后默认跳转的url
     */
    private String adminSuccessUrl;

    /**
     * 前台登录成功后默认跳转的url
     */
    private String portalSuccessUrl;

    /**
     * 后台用户的ttl
     */
    private Integer adminTtlDay;

    /**
     * 后台用户的ttl
     */
    private Integer portalTtlDay;


    @PostConstruct
    public void check(){

    }
}
