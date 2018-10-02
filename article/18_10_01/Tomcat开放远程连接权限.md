## Tomcat8及以上版本开放远程连接权限 ##

1.测试是否成功开放权限：

	http://114.55.26.64:8080/manger

2.对/usr/local/apache-tomcat-8.5.33/conf文件夹下的tomcat-users.xml进行编辑，添加一下内容(目的是添加一些权限以及一个连接使用的用户)：

	<role rolename="manager"/>
	<role rolename="manager"/>
	<role rolename="manager-gui"/>
	<role rolename="admin"/>
	<role rolename="admin-gui"/>
	<role rolename="manager-script"/>  
	<user username="tomcat" password="tomcat" roles="admin-gui,admin,manager-gui,manager,manager-script"/>

3.在/usr/local/apache-tomcat-8.5.33/webapps/manager/META-INF目录下编辑content.xml文件配置可连接的ip地址(将allow值改成"^.*$"表示所有ip都可以访问)：

之前：

	<Valve className="org.apache.catalina.valves.RemoteAddrValve" allow="127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1" />

修改后：

	<Valve className="org.apache.catalina.valves.RemoteAddrValve"allow="^.*$" />

	