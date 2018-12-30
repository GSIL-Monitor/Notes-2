
![](https://i.imgur.com/ZCzgp0S.jpg)

- 打包 
  
		mvn package

- 导入本地jar包
	
			 mvn install:install-file -DgroupId=com.csidez  
		-DartifactId=dubbo_provider -Dversion=0.0.1-SNAPSHOT
		-Dfile=E:\eclipse\workspace_dubbo\dubbo_provider\target\dubbo_provide-0.0.1-SNAPSHOT.jar    
		-Dpackaging=jar

- 在pom.xml中引入jar包

		<dependency>
			<groupId>com.csidez</groupId>
			<artifactId>dubbo_provider</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
