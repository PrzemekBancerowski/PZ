package org.pz.listener.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Arrays;
import org.pz.listener.core.model.SensorDetails;
import org.pz.listener.core.model.SensorMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Katarzyna Piotrowicz
 */
@Component
public class UdpReceiver implements Runnable {
    
    @Value("${udp.receiver.port}")
    private Integer udpPort;

    @Value("${udp.receiver.packet.size}")
    private Integer packetSize;

    private final ObjectMapper mapper = new ObjectMapper();
    
    @Autowired 
    private DatabaseController controller;

    @Override
    public void run() {
        System.out.println("### UDP receiver thread started ###");
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(udpPort);
            byte[] receiveData = new byte[packetSize];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            while (true) {
                serverSocket.receive(receivePacket);
//                ByteArrayInputStream bis = new ByteArrayInputStream(receivePacket.getData());
                String json = new String(receivePacket.getData());
                System.out.println("RECEIVED: " + json);
                SensorMetadata meta = mapper.readValue(json, SensorMetadata.class);
                SensorDetails details = mapper.readValue(json, SensorDetails.class);
                controller.saveNewSensorDetails(details);
                controller.saveNewSensorMetadata(meta);
            }
        } catch (IOException ex) {
            System.out.println("UDP Receiver exception: ");
            ex.printStackTrace();
            try {
                serverSocket.close();
            } catch (Exception e) {
                System.out.println("UDP Receiver Socket closing exception: " + Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
