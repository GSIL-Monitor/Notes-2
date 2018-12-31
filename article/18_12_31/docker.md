最近在自己黑群晖上安装了docker准备安装一个tomcat来跑我的项目
##Doker(黑群晖下安装的docker)



###查看当前运行的容器

	sudo docker ps

![](https://i.imgur.com/YPmSt47.png)

###进入相应的容器
	
	sudo docker exec -it 61ec3c9df56a /bin/bash

###拷贝文件到容器
	
	docker cp /root/test.txt ecef8319d2c8:/root/