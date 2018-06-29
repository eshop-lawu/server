#eureka

##构建镜像
```Bash
docker build -t eshop/mall-srv:1.0-SNAPSHOT .
```

##启动容器
```Bash
docker run --name mall-srv -it -d -p 8005:8005 \
    -v /etc/localtime:/etc/localtime:ro \
    eshop/mall-srv:1.0-SNAPSHOT
```
