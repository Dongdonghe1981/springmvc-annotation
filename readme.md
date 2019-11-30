
### Servlet3.0-与SpringMVC整合分析
[官方文档](https://docs.spring.io/spring/docs/5.2.1.RELEASE/spring-framework-reference/web.html#mvc-servlet)
1.	Web容器在启动的时候，会扫描每个Jar包下的META-INFO/services/javax.servlet.ServletContainerInitializer
2.	加载这个文件指定的类org.springframework.web.SpringServletContainerInitializer
3.	Spring应用启动时会加载WebApplicationInitializer接口下的所有组件
4.	为这些组件创建对象（组件不是接口，不是抽象类）
* AbstractContextLoaderInitializer：
    > 创建一个根容器createRootApplicationContext()
* AbstractDispatcherServletInitializer：
    > 创建一个Web的IOC容器createServletApplicationContext()<br/>
    > 创建一个DispatcherServlet，createDispatcherServlet()<br/>
    > 将创建的DispatcherServlet添加到ServletContext中
* AbstractAnnotationConfigDispatcherServletInitializer
    > 注解方式配置的DispatchServlet初始化器<br/>
    > 创建一个根容器createRootApplicationContext()<br/>
    > 创建一个Web的IOC容器createServletApplicationContext()

##### 总结：
以注解的方式来启动SpringMVC，继承AbstractAnnotationConfigDispatcherServletInitializer，实现抽象方法指定DispatchServlet的配置信息。
 
#### 定制SpringMVC
+ @EnableWebMvc：开启SpringMVC定制配置功能，<mvc:annotation-driven>
+ 配置组件（视图解析器、视图映射、静态资源映射）继承
	extends WebMvcConfigurerAdapter


#### SpringMVC-异步请求-返回Callable
1.	控制器返回Callable
2.	SpringMVC异步处理，将Callable提交到TaskExcutor，使用一个隔离的线程执行
3.	Dispacher和所有的filter退出Web容器线程，但response保持打开状态
4.	Callable返回结果，SpringMVC将请求重新派发给容器，恢复之前的处理。
5.	根据Callable返回的结果。SpringMVC继续进行视图渲染等流程（收请求->视图渲染）

##### 处理流程
>preHandle....http://localhost:8080/springmvc-annotation/async01<br/>
>main thread startThread[http-bio-8080-exec-4,5,main]<br/>
>main thread endThread[http-bio-8080-exec-4,5,main]<br/>
>====DispatcherServlet及所有的Filter退出线程
---		
>=====等待Callable执行<br/>
>sub thread startThread[MvcAsync1,5,main]<br/>
>sub thread endThread[MvcAsync1,5,main]
---
>=====Callable执行完成<br/>
>preHandle....http://localhost:8080/springmvc-annotation/async01<br/>
>postHandle....（Callable的返回值，就是目标方法的返回值）<br/>
>afterCompletion...

##### 异步拦截器
1. 原生API的AsyncListener
2. SpringMVC：实现AsyncHandlerInterceptor
