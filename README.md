# Study_Spring+SpringMVC+Mybatis

基于SSM(Spring+SpringMVC+Mybatis)实现的基本购物平台功能如下:

1.前端注册页面的用户输入内容具备文字基本校验功能

2.注册页面的验证码可以实现点击更新

3.注册的账号邮箱后端具备重复校验、验证码校验

4.登入用户的账号密码Cookie保存

5.登入后商品信息的展示和修改操作


(以下是详细的配置内容 可能小白我有些地方描述不太正确 还请大佬提提建议)

这是用SpringMVC+Spring+Mybatis实现的注册登录查询商品信息以及修改商品信息

1.其中由Maven管理整个工程
	管理工程中包含的jar包和依赖包
	
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<maven.compiler.source>1.7</maven.compiler.source>
		<maven.compiler.target>1.7</maven.compiler.target>
		<spring.version>5.0.16.RELEASE</spring.version>
		<mybatis.version>3.4.6</mybatis.version>
		<mybatis.spring.version>1.3.2</mybatis.spring.version>
		<mysql.version>8.0.11</mysql.version>
		<servlet.version>1.2</servlet.version>
		<taglibs.version>1.1.2</taglibs.version>
	</properties>
	
	
2.通过springmvc使用注解的形式配置处理器映射器 处理器适配器 通过扫描包的形式配置处理器

    <!--该文件被applicationContext-dao.xml引用-->
    <!--处理器映射器-->
    <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping"/>
    <!--处理器适配器-->
    <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"/>
    <!--配置处理器 即handler 这里使用扫描包的形式-->
    <context:component-scan base-package="cn.lh.items.controller,cn.lh.user.controller"/>
    <!--配置视图解析器 要求将jstl的包加到classpath 配置返回jsp文件的前后缀-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    	
   (1).在Controller类前使用注解@Controller标明其为处理器
   
   (2).使用注解@RequestMapping 确认对应的访问路径(虚拟路径)
   
   (3).在web.xml中配置SpringMVC前端控制器使其加载springmvc.xml配置文件
   
   (4).在web.xml中配置SpringMVC并进行路径访问的限制*.action
	
	
3.通过整合spring管理

    1.Service实例对象的注入(IOC)
    <!--将服务层的实现对象注入到controllor中-->
    <bean id="itemsService" class="cn.lh.items.service.ItemsServiceImpl"/>
    <bean id="userService" class="cn.lh.user.service.UserServiceImpl"/>
	
    2.jdbc来管理数据库的连接关系
    <!--加载jdbc配置文件-->
    <context:property-placeholder location="classpath:jdbc.properties"/>
    <!--数据库连接池 连接……-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
	
    3.整合mybatis一起配置SqlSessionFactory
    <!-- 加载数据库的SqlsessionFactory -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!-- 数据源 为上面配置的jdbc数据源 -->
        <property name="dataSource" ref="dataSource"/>
        <!-- mybatis配置文件 为SqlMapConfig.xml文件中配置的 -->
        <property name="configLocation" value="classpath:SqlMapConfig.xml"/>
    </bean> 
	
	4.配置Mapper扫描器
    <!--Mapper扫描器-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!-- 扫描mapper接口 并将其生成对应bean对象注入到ServiceImpl中-->
        <property name="basePackage" value="cn.lh.items.mapper,cn.lh.user.mapper"/>
        <!--将上面加载的数据库的sqlSessionFactory用来执行mapper对应xml中的sql语句-->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
    </bean>	
	
    5.进行事务管理(工程中没用到)
	
    6.在web.xml中配置spring容器的监听器 使得在服务器启动的时候 加载Spring对应的xml配置文件
	
4.通过Mapper和Mapper.xml的配置实现sql语句和java代码的解耦

    通过xml的namespace以及xml内部各个标签的id使得XML中的sql语句和Mapper中的方法一一对应
	
