package com.github.xiaoxfan;


import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author xiaofan
 * @date 2018/10/30 17:35
 */
public class LocalUDPDataReceiver {
    private static final String TAG = LocalUDPDataReceiver.class.getSimpleName();
    private static LocalUDPDataReceiver instance = null;
    private Thread thread = null;

    public static LocalUDPDataReceiver getInstance() {
        if (instance == null) {
            instance = new LocalUDPDataReceiver();
        }
        return instance;
    }

    public void startup() {
        this.thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LocalUDPDataReceiver.this.udpListeningImpl();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.thread.start();
    }

    private void udpListeningImpl() throws Exception {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
            DatagramSocket localUDPSocket = LocalUDPSocketProvider.getInstance().getLocalUDPSocket();
            if ((localUDPSocket == null) || localUDPSocket.isClosed()) {
                continue;
            }
            // block util receive message
            localUDPSocket.receive(datagramPacket);

            String message = new String(datagramPacket.getData(), 0, datagramPacket.getLength(), "UTF-8");
            System.out.println(TAG + " receive message from server >> " + message);
        }
    }
}
