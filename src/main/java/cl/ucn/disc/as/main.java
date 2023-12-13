package cl.ucn.disc.as;

import cl.ucn.disc.as.grpc.PersonaGrpcServiceImpl;
import cl.ucn.disc.as.ui.ApiRestServer;
import cl.ucn.disc.as.ui.WebController;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public final class main {

    public static void main(String[] args) throws InterruptedException, IOException {

        //var app = Javalin.create(/*config*/)
        //.get("/", ctx -> ctx.result("Hello World"))
        //.get("personas", ctx -> ctx.json((sistema.getPersonas())))
        //.start(7070);

        /*log.debug("Starting Main ..");


        //Start the API Rest server
        Javalin app = ApiRestServer.start(7070, new WebController());

        log.debug("Done. :)");
        */


        log.debug("Starting Main with library path: {}", System.getProperty("java.library.path"));

        // Start the API REST server
        log.debug("Starting ApiRest server ..");
        Javalin app = ApiRestServer.start(7070, new WebController());

        // stop the API REST server.
        // app.stop();

        // Start the gRPC server
        log.debug("Starting the gRPC server ..");
        Server server = ServerBuilder
                .forPort(50123)
                .addService(new PersonaGrpcServiceImpl())
                .build();

        server.start();

        // shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));

        // wait for the stop
        server.awaitTermination();

        log.debug("Done. :)");

    }

}