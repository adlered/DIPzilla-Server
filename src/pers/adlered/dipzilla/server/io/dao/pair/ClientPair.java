package pers.adlered.dipzilla.server.io.dao.pair;

/**
 * <h3>DIPzilla-Server</h3>
 * <p>保存客户端 Bean 类</p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2020-01-11 17:14
 **/
public class ClientPair {
    private String ip;
    private String updateTime;

    public ClientPair(String ip, String updateTime) {
        this.ip = ip;
        this.updateTime = updateTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
