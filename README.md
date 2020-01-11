## DIPzilla

DIPzilla 可以用于在一台**静态** IP 地址的服务器上记录**动态** IP 地址的家用服务器，用户通过访问**静态** IP 地址的服务器，以得到**动态** IP 地址客户端的信息。

#### [> ENGLISH README <](/README-EN.md)

### 与 DDNS 相比的优缺点

* 不需要购买域名，不需要借助第三方域名服务器
* 需要拥有一台静态 IP 地址的服务器
* 需要在家中拥有一台
* 适用于拥有动态 IP 地址的家用宽带
* 不能绑定域名（小提示：运营商已经开始检查封禁被域名解析的家用宽带了）
* 服务端提供 Telnet 服务，以供方便提取客户端的 IP 地址
* 通过“同步密钥（Sync Key）”保证同步数据安全

### 原理

1. 在拥有**静态**公网 IP 地址的服务器架设 DIPzilla-Server
2. 在**动态**公网 IP 地址的家庭服务器架设 DIPzilla-Client
3. Client 会定时向 Server 发送请求，Server 分析 Client 的公网 IP 地址，并保存到 Server 数据库中
4. 用户通过访问 Server 端交互终端，获得 Client（可以多个）的公网 IP 地址

![](/pic/DIP.png)

### 下载 && 安装

[服务端从这里下载](https://github.com/AdlerED/DIPzilla-Server/releases)

[客户端从这里下载](https://github.com/AdlerED/DIPzilla-Client/releases)

```shell script
For Server:
java -jar DIPzilla-server.jar [listenPort] [syncKey]

For Client:
java -jar DIPzilla-client.jar [serverHost] [serverPort] [syncKey] [clientAlias]
```

### 截图

![](/pic/1.png)

Telnet 访问服务端，读取客户端信息

![](/pic/2.png)

客户端自动同步至服务端



