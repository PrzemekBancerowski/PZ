package com.project.pz.webserver;

import com.project.pz.webserver.service.MonitorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by Piotr Sołtysiak on 2016-05-06.
 * Contact: piotrek.soltysiak@gmail.com
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context =  SpringApplication.run(App.class, args);

        MonitorService monitorService = context.getBean(MonitorService.class);
        monitorService.getMonitors();
    }
}