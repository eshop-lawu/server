#eureka

##构建镜像
```Bash
docker build -t eshop/order-srv:1.0-SNAPSHOT .
```

##启动容器
```Bash
docker run --name order-srv -it -d -p 8004:8004 \
    -v /etc/localtime:/etc/localtime:ro \
    eshop/order-srv:1.0-SNAPSHOT
```
