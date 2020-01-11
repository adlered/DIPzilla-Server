package pers.adlered.dipzilla.server.cron;

import pers.adlered.dipzilla.server.Context;

import java.io.IOException;
import java.net.Socket;

/**
 * <h3>DIPzilla-Server</h3>
 * <p>守护线程，接收客户端的请求</p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2020-01-11 13:45
 **/
public class Daemon implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Socket dipClient = Context.dipServer.accept();
                ClientHandler clientHandler = new ClientHandler(dipClient);
                Thread handlerThread = new Thread(clientHandler);
                handlerThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
