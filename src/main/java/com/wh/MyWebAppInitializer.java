package com.wh;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.wh.config.AppConfig;
import com.wh.config.RootConfig;


//web容器启动的时候创建对象，调用方法来初始化容器以及前端控制器
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//获取根容器的配置类(Spring的配置文件)父容器
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {RootConfig.class};
	}

	//获取web容器的配置类（SpringMVC的配置文件）子容器
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {AppConfig.class};
	}

	//获取DispatcherServlet的映射信息
	// /:拦截所有请求（包括静态资源（xx.js,xx.png）），但不包括*.jsp
	// /*:拦截所有请求，包括*.jsp
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

}
