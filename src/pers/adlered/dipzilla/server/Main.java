package pers.adlered.dipzilla.server;

import pers.adlered.dipzilla.server.cron.Daemon;
import pers.adlered.dipzilla.server.log.Logger;
import pers.adlered.dipzilla.server.service.ArgsAnalyze;

/**
 * <h3>DIPzilla-Server</h3>
 * <p>主方法</p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2020-01-11 13:45
 **/
public class Main {
    public static void main(String[] args) {
        Logger.info("DIPzilla Server v" + Context.VERSION);
        ArgsAnalyze.toMemory(args);
        Logger.info("Initializing...");
        Context.init();
        Logger.info("Listening on port " + Context.port + "...");
        Thread daemon = new Thread(new Daemon());
        daemon.start();
    }
}
