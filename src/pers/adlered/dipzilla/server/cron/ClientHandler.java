package pers.adlered.dipzilla.server.cron;

import pers.adlered.dipzilla.server.Context;
import pers.adlered.dipzilla.server.io.dao.ClientList;
import pers.adlered.dipzilla.server.log.Logger;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h3>DIPzilla-Server</h3>
 * <p>接收客户端实例，读取发送的内容</p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2020-01-11 14:18
 **/
public class ClientHandler implements Runnable {
    private Socket dipClient;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    private boolean run = true;

    public ClientHandler(Socket dipClient) {
        this.dipClient = dipClient;
    }

    @Override
    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(dipClient.getInputStream(), StandardCharsets.UTF_8));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(dipClient.getOutputStream(), StandardCharsets.UTF_8));
            Logger.info(dipClient.getRemoteSocketAddress() + " Connected.");
            bufferedWriter.write(Logger.getVersion());
            bufferedWriter.write("Please login with \"Sync Key\": ");
            bufferedWriter.flush();
            String loginKey = bufferedReader.readLine();
            // MacOS Telnet 兼容
            loginKey = loginKey.replaceAll("��\u0003��\u0018��\u001F�� ��!��\"��'��\u0005��#", "");
            Logger.info("Login: " + loginKey);
            if (loginKey.equals(Context.syncKey)) {
                while (true) {
                    if (!run) {
                        break;
                    }
                    sendHelp();
                    try {
                        String line = bufferedReader.readLine();
                        bufferedWriter.write("\r\n");
                        bufferedWriter.flush();
                        put(line);
                    } catch (SocketException | NullPointerException e) {
                        break;
                    }
                }
            } else {
                if (!update(loginKey)) {
                    bufferedWriter.write("Wrong Sync Key!" + "\r\n");
                    bufferedWriter.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        stop();
    }

    private void put(String line) throws IOException {
        if (!update(line)) {
            if (line.equals("q")) {
                run = false;
                bufferedWriter.write("Good bye!" + "\r\n");
                bufferedWriter.flush();

                return;
            } else if (line.equals("1")) {
                bufferedWriter.write(ClientList.getAll());
                bufferedWriter.flush();
            } else if (line.equals("2")) {
                bufferedWriter.write("Input your \"Hostname\" and press ENTER: ");
                bufferedWriter.flush();
                String hostname = bufferedReader.readLine();
                bufferedWriter.write("\r\n");
                bufferedWriter.flush();
                String hostSet = Context.syncKey + "\\" + hostname;
                if (update(hostSet)) {
                    bufferedWriter.write("OK!" + "\r\n");
                    bufferedWriter.flush();
                } else {
                    bufferedWriter.write("Wrong Sync Key!" + "\r\n");
                    bufferedWriter.flush();
                }
            } else {
                Logger.debug("Unknown command: " + line);
            }
        }
    }

    public boolean update(String line) {
        try {
            String[] props = line.split("\\\\");
            String syncKey = props[0];
            String hostname = props[1];
            String ip = dipClient.getInetAddress().getHostAddress();
            if (syncKey.equals(Context.syncKey)) {
                Logger.info("Update client information [syncKey=" + syncKey + ", hostname=" + hostname + ", ip=" + ip + "]");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String updateTime = simpleDateFormat.format(new Date());
                ClientList.add(hostname, ip, updateTime);
                return true;
            } else {
                Logger.info("Incorrect syncKey set [syncKey=" + syncKey + ", hostname=" + hostname + ", ip=" + ip + "]");
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private void sendHelp() throws IOException {
        bufferedWriter.write(Logger.getHelp());
        bufferedWriter.flush();
    }

    private void stop() {
        try {
            bufferedReader.close();
        } catch (IOException e) {
        }
        try {
            bufferedWriter.close();
        } catch (IOException e) {
        }
        try {
            dipClient.close();
        } catch (IOException e) {
        }
        Logger.info(dipClient.getRemoteSocketAddress() + " Disconnected.");
    }
}
