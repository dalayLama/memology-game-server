package org.toxicgames.memology.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.toxicgames.memology")
public class Start {

    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}
