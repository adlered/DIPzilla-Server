package pers.adlered.dipzilla.server.service;

import pers.adlered.dipzilla.server.Context;
import pers.adlered.dipzilla.server.log.Logger;

/**
 * <h3>DIPzilla-Server</h3>
 * <p>分析传入的 args ，并写入至静态变量</p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2020-01-11 13:48
 **/
public class ArgsAnalyze {
    public static void toMemory(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            if (port > 0 && port < 65536) {
                Context.port = port;
            } else {
                throw new Exception("Invalid port");
            }
            Logger.conf("Port = " + Context.port);

            String syncKey = args[1];
            Context.syncKey = syncKey;
            Logger.conf("Sync Key = " + Context.syncKey);
        } catch (Exception e) {
            Logger.err("Usage:");
            Logger.log("java -jar dipzilla-server.jar [listenPort] [syncKey]");
            Logger.log("java -jar dipzilla-server.jar [监听的端口] [同步密钥]");
            System.exit(0);
        }
    }
}
