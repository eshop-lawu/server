#eureka

##构建镜像
```Bash
docker build -t eshop/property-srv:1.0-SNAPSHOT .
```

##启动容器
```Bash
docker run --name property-srv -it -d -p 8002:8002 \
    -v /etc/localtime:/etc/localtime:ro \
    eshop/property-srv:1.0-SNAPSHOT
```
