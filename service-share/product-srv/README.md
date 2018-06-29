#eureka

##构建镜像
```Bash
docker build -t eshop/product-srv:1.0-SNAPSHOT .
```

##启动容器
```Bash
docker run --name product-srv -it -d -p 8003:8003 \
    -v /etc/localtime:/etc/localtime:ro \
    eshop/product-srv:1.0-SNAPSHOT
```
