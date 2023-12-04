package cl.ucn.disc.as;

import io.ebean.DB;
import io.ebean.Database;
import lombok.extern.slf4j.Slf4j;
import cl.ucn.disc.as.model.Persona;

import cl.ucn.disc.as.grpc.PersonaGrpcServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * THe main.
 * @Author Edgardo Ortiz Gonzalez
 */
@Slf4j
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Javalin app = ApiRestServer.start(7070,new WebController());

        log.debug("Starting Grpc server");
        Server server = ServerBuilder
                .forPort(50123)
                .addService(new PersonaGrpcServiceImpl())
                .build();
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
        server.awaitTermination();

        log.debug("Sistema deteniendose...");

        app.stop();

        log.debug("Sistema finalizado...");
    }

}