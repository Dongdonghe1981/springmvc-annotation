package com.wh.controller;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AsyncController {

	/**
	 * 1.����������Callable
	 * 2.SpringMVC�첽������Callable�ύ��TaskExcutor��ʹ��һ��������߳�ִ��
	 * 3.Dispacher�����е�filter�˳�Web�����̣߳���response���ִ�״̬
	 * 4.Callable���ؽ����SpringMVC�����������ɷ����������ָ�֮ǰ�Ĵ���
	 * 5.����Callable���صĽ����SpringMVC����������ͼ��Ⱦ�����̣�������->��ͼ��Ⱦ��
	 * 
	 * preHandle....http://localhost:8080/springmvc-annotation/async01
		main thread startThread[http-bio-8080-exec-4,5,main]
		main thread endThread[http-bio-8080-exec-4,5,main]
		===============DispatcherServlet�����е�Filter�˳��߳�
		
		===============�ȴ�Callableִ��
		sub thread startThread[MvcAsync1,5,main]
		sub thread endThread[MvcAsync1,5,main]
		===============Callableִ�����
		preHandle....http://localhost:8080/springmvc-annotation/async01
		postHandle....��Callable�ķ���ֵ������Ŀ�귽���ķ���ֵ��
		afterCompletion...
		
		�첽��������
			1����ԭ��API��AsyncListener
			2����SpringMVC��ʵ��AsyncHandlerInterceptor
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/async01")
	public Callable<String> asyn01() {
		System.out.println("main thread start"+Thread.currentThread());
		Callable<String> callable = () -> {
			System.out.println("sub thread start"+Thread.currentThread());
			Thread.sleep(2000);
			System.out.println("sub thread end"+Thread.currentThread());
			return "asyn01";
		};
		
		System.out.println("main thread end"+Thread.currentThread());
		return callable;
		
	}
}
