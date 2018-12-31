前些天看了微信公众号发现还有那么厉害的东西
# Jenkins
——JavaWeb项目的git拉取,编译,部署到本地Tomcat以及远程机的Tomcat上

1. git拉取![](https://i.imgur.com/NXVuCPN.png)

2. 构建环境配置![](https://i.imgur.com/PvYeU1u.png)
选择第四个编译过程中进行输出

3. maven配置![](https://i.imgur.com/sfhkZz7.png)
目标表示选择的命令(install过程中跳过测试)
4. 部署![](https://i.imgur.com/cUVEjBg.png)
部署本地时连接地址写http://localhost:8080(可以不需要配置本地tomcat的远程连接的权限)，若写成http://本地服务器ip地址:8080(需要配置本地机tomcat连接权限等，具体参照<<Tomcat开放远程连接权限>>的文章)，连接远程机部署war包同理<br><br>
另外contextPath指tomcat的webapps文件夹路径下的文件地址，可以不填，不填则为打包的名称(访问时候多添加一级的连接地址)
比如http://114.55.26.64:8080/demo/hello中的demo