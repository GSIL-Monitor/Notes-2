# SpringBoot打包war包记录 #
<br>
参考博客：https://blog.csdn.net/yalishadaa/article/details/70037846
## pom.xml修改 ##
<br>1.将jar改成war

	<groupId>com.example</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

<br>2.移除嵌入式tomcat插件，在pom.xml里找到spring-boot-starter-web依赖节点，在其中添加如下代码：

	<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <!-- 移除嵌入式tomcat插件 -->
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-tomcat</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
<br>3.修改plugin

	<plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <configuration>
                <warSourceExcludes>src/main/resources/**</warSourceExcludes>
                <warName>demo</warName>
            </configuration>
        </plugin>
    </plugins>
<br>4.添加servlet-api的依赖
<br>下面两种方式都可以，任选其一

	<dependency>
	    <groupId>javax.servlet</groupId>
	    <artifactId>javax.servlet-api</artifactId>
	    <version>3.1.0</version>
	    <scope>provided</scope>
	</dependency>
<br>

	<dependency>
	    <groupId>org.apache.tomcat</groupId>
	    <artifactId>tomcat-servlet-api</artifactId>
	    <version>8.0.36</version>
	    <scope>provided</scope>
	</dependency>


<br><br>
##2.启动类修改 ##

	@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
	@ServletComponentScan
	public class DemoApplication extends SpringBootServletInitializer {
	
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
	        return applicationBuilder.sources(DemoApplication.class);
	    }
	
	    public static void main(String[] args) {
	        SpringApplication.run(DemoApplication.class, args);
	    }
	}

