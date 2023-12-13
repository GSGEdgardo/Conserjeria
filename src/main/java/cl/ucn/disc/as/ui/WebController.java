package cl.ucn.disc.as.ui;

import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.services.Sistema;
import cl.ucn.disc.as.services.SistemaImpl;
import io.ebean.DB;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import cl.ucn.disc.as.grpc.PersonaGrpc;
import cl.ucn.disc.as.grpc.PersonaGrpcRequest;
import cl.ucn.disc.as.grpc.PersonaGrpcResponse;
import cl.ucn.disc.as.grpc.PersonaGrpcServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Optional;

public final class WebController  implements RoutesConfigurator {

    private final Sistema sistema;

    public WebController(){
        this.sistema = new SistemaImpl(DB.getDefault());
    }

    @Override
    public void configure(final Javalin app) {

        /*
        Default route or home
         */
        app.get("/", ctx -> {
            ctx.result("Welcome to Conserjeria API REST");
        });

        app.get("/grpc/personas/rut/{rut}", ctx -> {
            String rut = ctx.pathParam("204168539");

            ManagedChannel channel = ManagedChannelBuilder
                    .forAddress("localhost", 50123)
                    .usePlaintext()
                    .build();

            PersonaGrpcServiceGrpc.PersonaGrpcServiceBlockingStub stub =
                    PersonaGrpcServiceGrpc.newBlockingStub(channel);

            PersonaGrpcResponse response = stub.retrieve(PersonaGrpcRequest
                    .newBuilder()
                    .setRut(rut)
                    .build());

            PersonaGrpc personaGrpc = response.getPersona();

            Optional<Persona> oPersona = Optional.of(Persona.builder()
                    .rut(personaGrpc.getRut())
                    .nombre(personaGrpc.getNombre())
                    .apellidos(personaGrpc.getApellidos())
                    .email(personaGrpc.getEmail())
                    .telefono(personaGrpc.getTelefono())
                    .build());
            ctx.json(oPersona.orElseThrow(() -> new NotFoundResponse("Can't find Persona with rut: " + rut)));
        });
}
}