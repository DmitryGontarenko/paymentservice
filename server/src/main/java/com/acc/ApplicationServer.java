package com.acc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.HashMap;

@Slf4j
@SpringBootApplication
public class ApplicationServer {

    public static void main(String[] args) {
        if (args.length != 1) {
            log.warn("Set the port");
            return;
        }
        HashMap<String, Object> props = new HashMap<>();
        props.put("server.port", Integer.valueOf(args[0]));
        new SpringApplicationBuilder()
                .sources(ApplicationServer.class)
                .properties(props)
                .run(args);
    }
}
