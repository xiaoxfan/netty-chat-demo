package com.github.xiaoxfan;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.net.DatagramPacket;

/**
 * @author xiaofan
 * @date 2018/10/30 17:34
 */
public class EchoClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        // init local UDP Socket
        LocalUDPSocketProvider.getInstance().initSocket();
        LocalUDPDataReceiver.getInstance().startup();
        while (true) {
            String message = "Hi ! i'm client 时间:" + System.currentTimeMillis();
            byte[] bytes = message.getBytes(CharsetUtil.UTF_8);
            LocalUDPSocketProvider.getInstance().getLocalUDPSocket().send(new DatagramPacket(bytes, bytes.length));
            System.out.println("Send message to server >> " + message);
            Thread.sleep(3000);
        }
    }
}
