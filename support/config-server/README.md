#config-server

```bash
nohup java -jar config-server-1.0-SNAPSHOT.jar --spring.profiles.active=native,test >/dev/null 2>&1 &
```

##docker启动
```bash
sudo docker run -d --name config-server -p 8080:8080 \
    -e spring.profiles.active=native,product \
    -v /usr/local/eshop/config:/usr/local/eshop/config:ro \
    -v /etc/localtime:/etc/localtime:ro \
    registry.eshop.com/eshop/config-server
```