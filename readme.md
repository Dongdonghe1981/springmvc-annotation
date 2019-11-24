
Servlet3.0-与SpringMVC整合分析
官方文档
https://docs.spring.io/spring/docs/5.2.1.RELEASE/spring-framework-reference/web.html#mvc-servlet
1.	Web容器在启动的时候，会扫描每个Jar包下的META-INFO/services/javax.servlet.ServletContainerInitializer
2.	加载这个文件指定的类org.springframework.web.SpringServletContainerInitializer
3.	Spring应用启动时会加载WebApplicationInitializer接口下的所有组件
4.	为这些组件创建对象（组件不是接口，不是抽象类）
a)	AbstractContextLoaderInitializer：创建一个根容器createRootApplicationContext()
b)	AbstractDispatcherServletInitializer：
创建一个Web的IOC容器createServletApplicationContext()
创建一个DispatcherServlet，createDispatcherServlet()
将创建的DispatcherServlet添加到ServletContext中
c)	AbstractAnnotationConfigDispatcherServletInitializer：注解方式配置的DispatchServlet初始化器
创建一个根容器createRootApplicationContext()
创建一个Web的IOC容器createServletApplicationContext()
总结：以注解的方式来启动SpringMVC，继承AbstractAnnotationConfigDispatcherServletInitializer，
	实现抽象方法指定DispatchServlet的配置信息。
 
定制SpringMVC
1.@EnableWebMvc：开启SpringMVC定制配置功能，<mvc:annotation-driven>
2.	配置组件（视图解析器、视图映射、静态资源映射）继承
extends WebMvcConfigurerAdapter
