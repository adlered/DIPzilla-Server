package pers.adlered.dipzilla.server.log;

import pers.adlered.dipzilla.server.Context;

/**
 * <h3>DIPzilla-Server</h3>
 * <p>日志</p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2020-01-11 13:50
 **/
public class Logger {
    private static boolean debug = false;

    public static void log(String log) {
        System.out.println(log);
    }

    public static void info(String log) {
        System.out.println("[INFO] " + log);
    }

    public static void warn(String log) {
        System.out.println("[WARN] " + log);
    }

    public static void err(String log) {
        System.out.println("[ERR] " + log);
    }

    public static void conf(String log) {
        System.out.println("[CONF] " + log);
    }

    public static void debug(String log) {
        if (debug) {
            System.out.println("[DEBUG] " + log);
        }
    }

    public static String getHelp() {
        return "\r\n" + "1 - Show Hosts" + "\r\n"
                + "2 - Update your IP manual" + "\r\n"
                + "q - Exit" + "\r\n"
                + "\r\n";
    }

    public static String getVersion() {
        return "DIPzilla Server v" + Context.VERSION + "\r\n";
    }
}
