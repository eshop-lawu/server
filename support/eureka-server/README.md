#eureka

##命令启动
```bash
nohup java -jar eureka-server-1.0-SNAPSHOT.jar --spring.profiles.active=test >/dev/null 2>&1 &
```

##docker启动

正式环境
70
```bash
sudo docker run -d --name eureka-server -p 8888:8888 \
    -e spring.profiles.active=product1 \
    -v /etc/localtime:/etc/localtime:ro \
    registry.eshop.com/eshop/eureka-server
```
71
```bash
sudo docker run -d --name eureka-server -p 8888:8888 \
    -e spring.profiles.active=product2 \
    -v /etc/localtime:/etc/localtime:ro \
    registry.eshop.com/eshop/eureka-server
```