package com.dmall.component.web.configuration;

import com.dmall.common.constants.WebConstants;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: hystrix配置
 * @author: created by hang.yu on 2019/10/27 23:37
 */
@Configuration
public class HystrixConfiguration {

    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings(WebConstants.ACTUATOR_HYSTRIX_STREAM);
        registrationBean.setName(WebConstants.HYSTRIX_METRICS_STREAM_SERVLET);
        return registrationBean;
    }
}
