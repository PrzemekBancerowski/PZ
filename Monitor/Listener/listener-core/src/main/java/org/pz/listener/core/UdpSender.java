package org.pz.listener.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Katarzyna Piotrowicz
 * This is only for testing/presentation purposes. 
 */
@Component
public class UdpSender implements Runnable {

    @Value("${udp.receiver.packet.size}")
    private Integer packetSize;
    
    @Value("${udp.receiver.port}")
    private Integer udpPort;
    
    @Override
    public void run() {
        try {
            System.out.println("$$$ UDP Sender started $$$");
            DatagramSocket clientSocket = null;
            InputStream is = UdpSender.class.getResourceAsStream("/response.json");
            String json = IOUtils.toString(is, "UTF-8");
            for (int i = 0; i < 10; ++i) {
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                clientSocket = new DatagramSocket();
                InetAddress IPAddress = InetAddress.getByName("localhost");
                byte[] sendData = new byte[packetSize];
                sendData = json.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, udpPort);
                clientSocket.send(sendPacket);
                Thread.sleep(500);
            }
            clientSocket.close();
        } catch (IOException | InterruptedException ex) {
            System.out.println("UDP Receiver exception: " + Arrays.toString(ex.getStackTrace()));
        }
    }

}
