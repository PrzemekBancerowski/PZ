package org.pz.listener.core;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceiverTaskRunner {

    @Autowired
    private UdpReceiver receiverTask;

    @PostConstruct
    public void start() {
        Thread receiving = new Thread(receiverTask, "UDP-Receiver");
        receiving.start();
    }

}
