package pers.adlered.dipzilla.server;

import com.sun.security.ntlm.Server;
import pers.adlered.dipzilla.server.log.Logger;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * <h3>DIPzilla-Server</h3>
 * <p>存储静态数据</p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2020-01-11 13:47
 **/
public class Context {
    // Info
    final public static String VERSION = "1.0";

    // Variable
    public static ServerSocket dipServer;

    // Config
    public static int port;
    public static String syncKey;

    public static void init() {
        try {
            Context.dipServer = new ServerSocket(Context.port);
            Logger.info("Server initialized.");
        } catch (Exception e) {
            Logger.err("Startup failed! " + e.getCause());
            System.exit(0);
        }
    }
}
