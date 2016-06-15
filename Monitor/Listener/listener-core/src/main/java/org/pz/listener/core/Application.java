package org.pz.listener.core;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author Katarzyna Piotrowicz
 */
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        System.out.println("### Application works ###");

     //   if (args.length > 1 && "sender".equals(args[1])) {
            System.out.println("### Starting sending UDP packets ###");
            Thread t = new Thread(ctx.getBean(UdpSender.class));
            t.start();
      //  }
    }

}
