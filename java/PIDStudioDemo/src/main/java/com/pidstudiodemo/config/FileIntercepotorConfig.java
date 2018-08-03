package com.pidstudiodemo.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.pidstudiodemo.interceptor.FileInterceptor;
/**
 * 配置
 * */
@Configuration
public class FileIntercepotorConfig extends WebMvcConfigurerAdapter {
	//配置拦截器
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor( new FileInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
	//配置404页面
	 @Bean
	    public EmbeddedServletContainerCustomizer containerCustomizer() {
	        return new EmbeddedServletContainerCustomizer() {
	            public void customize(ConfigurableEmbeddedServletContainer container) {
	                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
	                container.addErrorPages(error404Page);
	            }
	        };
	    }
}
