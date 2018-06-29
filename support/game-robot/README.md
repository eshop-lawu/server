#game-robot

##命令启动
```bash
nohup java -jar game-robot-1.0-SNAPSHOT.jar --spring.profiles.active=test >/dev/null 2>&1 &
```
```
sudo docker run -d -p 6001:6001 --name game-robot \
    -v /etc/localtime:/etc/localtime:ro \
    -e TZ=Asia/Shanghai \
    -e spring.profiles.active=pp \
    registry.eshop.com/eshop/game-robot
```