package com.wh.controller;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AsyncController {

	/**
	 * 1.控制器返回Callable
	 * 2.SpringMVC异步处理，将Callable提交到TaskExcutor，使用一个隔离的线程执行
	 * 3.Dispacher和所有的filter退出Web容器线程，但response保持打开状态
	 * 4.Callable返回结果，SpringMVC将请求重新派发给容器，恢复之前的处理。
	 * 5.根据Callable返回的结果。SpringMVC继续进行视图渲染等流程（收请求->视图渲染）
	 * 
	 * preHandle....http://localhost:8080/springmvc-annotation/async01
		main thread startThread[http-bio-8080-exec-4,5,main]
		main thread endThread[http-bio-8080-exec-4,5,main]
		===============DispatcherServlet及所有的Filter退出线程
		
		===============等待Callable执行
		sub thread startThread[MvcAsync1,5,main]
		sub thread endThread[MvcAsync1,5,main]
		===============Callable执行完成
		preHandle....http://localhost:8080/springmvc-annotation/async01
		postHandle....（Callable的返回值，就是目标方法的返回值）
		afterCompletion...
		
		异步拦截器：
			1）、原生API的AsyncListener
			2）、SpringMVC：实现AsyncHandlerInterceptor
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
