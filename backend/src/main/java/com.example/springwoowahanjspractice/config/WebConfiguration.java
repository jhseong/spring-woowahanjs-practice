package com.example.springwoowahanjspractice.config;

import in.woowa.platform.gatekeeper.client.GateKeeperClientConfiguration;
import in.woowa.platform.gatekeeper.client.filter.GateKeeperConfigProvideFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private UserSessionCheckerInterceptor interceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .excludePathPatterns("/static/*.js")
                .excludePathPatterns("/static/css/**")
        ;
    }

    @Bean
    public FilterRegistrationBean gatekeeperConfigProvideFilter(GateKeeperClientConfiguration gateKeeperClientConfiguration) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.addUrlPatterns(GateKeeperConfigProvideFilter.DEFAULT_FILTER_URL);
        registrationBean.setFilter(new GateKeeperConfigProvideFilter(gateKeeperClientConfiguration));
        return registrationBean;
    }
}
