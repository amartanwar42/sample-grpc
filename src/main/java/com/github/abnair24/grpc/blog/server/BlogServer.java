package com.github.abnair24.grpc.blog.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class BlogServer {

    public static void main(String[] args) throws InterruptedException, IOException {
        int port = 50050;
        Server server = ServerBuilder.forPort(port)
                .addService(new BlogServiceImpl())
                .build();

        log.info("Starting server at : {}",port);
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutting down server at : {}",port);
            server.shutdown();
            log.info("Server shutdown completed at : {}",port);
        }));

        server.awaitTermination();
    }

}
