
#问题：org.apache.ibatis.binding.BindingException: Invalid bound statement (not found)

一般的原因是Mapper interface和xml文件的定义对应不上，需要检查包名，namespace，函数名称等能否对应上，需要比较细致的对比，我经常就是写错了一两个字母搞的很长时间找不到错误

按以下步骤一一执行：

>1：检查xml文件所在的package名称是否和interface对应的package名称一一对应

>2：检查xml文件的namespace是否和xml文件的package名称一一对应

>3：检查函数名称能否对应上

>4：去掉xml文件中的中文注释

>5：随意在xml文件中加一个空格或者空行然后保存

>**6.正确配置加载文件目录**

>在pom 配置文件中键入 <build>节点，并指明资源类型，这样在程序启动时，就可以正确加载配置文件：

Java中的配置资源类型：

    <build>
    	<resources>
    		<resource>
    			<directory>src/main/java</directory>
    			<includes>asdasdasd
    				<include>**/*.properties</include>
    				<include>**/*.xml</include>
    			</includes>
    			<filtering>false</filtering>
    		</resource>
    	</resources>
    </build>

Resource中的配置资源类型：


    <build>	
    	<resources>
    		<resource>
    			<directory>src/main/resources</directory>
    			<includes>
    				<include>**/*.properties</include>
    				<include>**/*.xml</include>
    			</includes>
    			<filtering>false</filtering>
    		</resource>
    	</resources>
    </build>

当然项目需求可以同时键入两个：


    <build>	
    	<resources>
    		<resource>
    			<directory>src/main/java</directory>
    			<includes>
    				<include>**/*.properties</include>
    				<include>**/*.xml</include>
    			</includes>
    			<filtering>false</filtering>
    		</resource>
    		<resource>
    			<directory>src/main/resources</directory>
    			<includes>
    				<include>**/*.properties</include>
    				<include>**/*.xml</include>
    			</includes>
    			<filtering>false</filtering>
    		</resource>
    	</resources>
    </build>


键入<build> 节点后，在次启动项目，在项目的 target 目标文件中，就可以看到接口对应的映射文件了