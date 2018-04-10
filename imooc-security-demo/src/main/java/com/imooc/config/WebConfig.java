package com.imooc.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.imooc.web.filter.TimeFilter;
import com.imooc.web.inteceptor.TimeIntercptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	//@Autowired
	private TimeIntercptor timeIntercptor;

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//		configurer.registerCallableInterceptors(interceptors);
//		configurer.registerDeferredResultInterceptors(interceptors);
//		configurer.setDefaultTimeout(timeout);//实战超时时间
//		configurer.setTaskExecutor(taskExecutor);//设置可重用的线程池
		super.configureAsyncSupport(configurer);
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(timeIntercptor);
	}

	//@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);

		return registrationBean;
	}
	
	/**
	 * 解决controller乱码问题
	 * @return
	 */
//	@Bean
//	public HttpMessageConverter<String> responseBodyConverter() {
//	    StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//	    return converter;
//	}
//
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//	    super.configureMessageConverters(converters);
//	    converters.add(responseBodyConverter());
//	}
}
