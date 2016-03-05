package com.xianda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.xianda.interceptor.ThymeleafLayoutInterceptor;
import com.xianda.web.view.ExcelViewBusiInfo;
import com.xianda.web.view.ExcelViewCustomer;
import com.xianda.web.view.SimpleConfigurableViewResolver;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ThymeleafLayoutInterceptor());
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		// registry.addConverter(carConverter());
		// registry.addConverter(employeeConverter());
		// registry.addConverter(providerConverter());
		// registry.addFormatter(new DateFormatter(CommonTool.dateFormatStr));
	}

//	@Bean
//	public StringToEntityConverter carConverter() {
//		return new StringToEntityConverter(Car.class);
//	}

//	@Bean
//	public StringToEntityConverter employeeConverter() {
//		return new StringToEntityConverter(Employee.class);
//	}
//
//	@Bean
//	public StringToEntityConverter providerConverter() {
//		return new StringToEntityConverter(Provider.class);
//	}
	
	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
		ContentNegotiatingViewResolver viewResolver;
		viewResolver = new ContentNegotiatingViewResolver();
		List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
		viewResolvers.add(xlsViewResolver());
		viewResolver.setViewResolvers(viewResolvers);
		return viewResolver;
	}

	@Bean
	public ViewResolver xlsViewResolver() {
		SimpleConfigurableViewResolver viewResolver;
		viewResolver = new SimpleConfigurableViewResolver();
		Map<String, AbstractExcelView> views;
		views = new HashMap<String, AbstractExcelView>();
		views.put("ExcelViewCustomer", new ExcelViewCustomer());
		viewResolver.setViews(views);
		return viewResolver;
	}
}
