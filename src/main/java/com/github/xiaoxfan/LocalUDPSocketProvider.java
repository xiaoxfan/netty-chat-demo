package com.github.xiaoxfan;


import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author xiaofan
 * @date 2018/10/30 17:35
 */
public class LocalUDPSocketProvider {
    private static LocalUDPSocketProvider instance = null;
    private DatagramSocket localUDPSocket = null;

    public static LocalUDPSocketProvider getInstance() {
        if (instance == null) {
            instance = new LocalUDPSocketProvider();
        }
        return instance;
    }

    public void initSocket() {
        try {
            this.localUDPSocket = new DatagramSocket(Config.CLIENT_PORT);
            this.localUDPSocket.connect(InetAddress.getByName(Config.SERVER_ADDRESS), Config.SERVER_PORT);
            this.localUDPSocket.setReuseAddress(true);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public DatagramSocket getLocalUDPSocket() {
        return this.localUDPSocket;
    }
}
