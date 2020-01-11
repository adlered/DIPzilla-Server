package pers.adlered.dipzilla.server.io.dao;

import pers.adlered.dipzilla.server.io.dao.pair.ClientPair;
import pers.adlered.dipzilla.server.io.dao.tool.ConsoleTable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * <h3>DIPzilla-Server</h3>
 * <p>客户端列表</p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2020-01-11 15:12
 **/
public class ClientList {
    private static Map<String, ClientPair> clientList = new TreeMap<>();

    public static void add(String hostname, String ip, String updateTime) {
        ClientPair clientPair = new ClientPair(ip, updateTime);
        clientList.put(hostname, clientPair);
    }

    public static String getAll() {
        Iterator<Map.Entry<String, ClientPair>> list = clientList.entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        ConsoleTable consoleTable = new ConsoleTable(3, true);
        consoleTable.appendRow();
        consoleTable.appendColum("Hostname");
        consoleTable.appendColum("IP Address");
        consoleTable.appendColum("Update Time");
        while (list.hasNext()) {
            Map.Entry<String, ClientPair> client = list.next();
            String hostname = client.getKey();
            ClientPair clientPair = client.getValue();
            String ip = clientPair.getIp();
            String updateTime = clientPair.getUpdateTime();
            consoleTable.appendRow();
            consoleTable.appendColum(hostname);
            consoleTable.appendColum(ip);
            consoleTable.appendColum(updateTime);
        }
        sb.append(consoleTable.toString());
        return sb.toString();
    }
}
