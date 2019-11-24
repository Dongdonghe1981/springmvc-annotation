package com.wh;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.wh.config.AppConfig;
import com.wh.config.RootConfig;


//web����������ʱ�򴴽����󣬵��÷�������ʼ�������Լ�ǰ�˿�����
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//��ȡ��������������(Spring�������ļ�)������
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {RootConfig.class};
	}

	//��ȡweb�����������ࣨSpringMVC�������ļ���������
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {AppConfig.class};
	}

	//��ȡDispatcherServlet��ӳ����Ϣ
	// /:�����������󣨰�����̬��Դ��xx.js,xx.png��������������*.jsp
	// /*:�����������󣬰���*.jsp
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

}
