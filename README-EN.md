## DIPzilla

DIPzilla can be used for a home server that records **dynamic** IP address on a server with **static** IP address. Users access the server with **static** IP address to get **dynamic** Address client information.

### Pros and cons compared to DDNS

* No need to purchase a domain name, no need to use a third-party name server
* Requires a server with a static IP address
* Requires one at home
* For home broadband with dynamic IP address
* Cannot bind the domain name (Tip: the operator has started to check the ban on home broadband that is resolved by the domain name)
* The server provides Telnet service for easy extraction of the client's IP address
* Synchronization key (Sync Key) to ensure the safety of synchronized data

### Principle

1. Set up DIPzilla-Server on a server with a **static** public network IP address
2. Set up DIPzilla-Client on the home server with **dynamic** public IP address
3. The client sends a request to the server at regular intervals. The server analyzes the client's public IP address and saves it to the server database.
4. The user obtains the public IP address of the client (multiple clients) by accessing the interactive terminal on the server.

![](/pic/DIP.png)

### Download and install

[Server download from here](https://github.com/AdlerED/DIPzilla-Server/releases)

[Client download from here](https://github.com/AdlerED/DIPzilla-Client/releases)

```shell script
For Server:
java -jar dipzilla-server.jar [listenPort] [syncKey]

For Client:
java -jar dipzilla-client.jar [serverHost] [serverPort] [syncKey] [clientAlias]
```

### Screenshot

![](/pic/1.png)

Telnet to server, read client information

![](/pic/2.png)

Client automatically syncs to server