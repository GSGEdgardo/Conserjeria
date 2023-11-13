package cl.ucn.disc.as.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.javalin.Javalin;
import io.javalin.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;

@Slf4j
public final class ApiRestServer {
    private ApiRestServer() {
        //nothing
    }

    /**
     * @return the configured Gson.
     */
    private static Gson createAndConfigureGson() {
        //Instant   serializer
        TypeAdapter<Instant> instantTypeAdapter = new TypeAdapter<>() {
            @Override
            public void write(JsonWriter out, Instant value) throws IOException {
                if (value == null) {
                    out.nullValue();
                } else {
                    out.value(value.toEpochMilli());
                }

            }

            @Override
            public Instant read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                return Instant.ofEpochMilli(in.nextLong());
            }
        };
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Instant.class, instantTypeAdapter)
                .create();
    }

    /**
     * return he configured Jvaalin Server
     */
    private static Javalin createAndConfigureJavalin() {
        //Gson config
        JsonMapper jsonMapper = new JsonMapper() {

            //Gson serializer
            private static final Gson GSON = createAndConfigureGson();

            /**
             * Json to object
             */
            @NotNull
            @Override
            public <T> T fromJsonString(@NotNull String json, @NotNull Type targetType) {
                return GSON.fromJson(json, targetType);
            }

            /**
             * Object to json
             */
            @NotNull
            @Override
            public String toJsonString(@NotNull Object obj, @NotNull Type type) {
                return GSON.toJson(obj, type);

            }
        };
        return Javalin.create(config -> {
            config.jsonMapper(jsonMapper);
            config.compression.gzipOnly(9);
            config.requestLogger.http((ctx, ms) -> {
                log.debug("Served: {} in {} ms.", ctx.fullUrl(), ms);
            });
            //enable debug logger
            config.plugins.enableDevLogging();
        });
    }

    public static Javalin start(final Integer port, final RoutesConfigurator routesConfigurator) {
        if (port < 1024 || port > 65535) {
            log.error("Bad port: {}", port);
            throw new IllegalArgumentException("Bad port: " + port);
        }
        log.debug("Starting ApiRestServer on port: {}", port);

        //the server
        Javalin app = createAndConfigureJavalin();

        //configure paths
        routesConfigurator.configure(app);

        //the hooup thread
        Runtime.getRuntime().addShutdownHook(new Thread(app::stop));

        //Hooks to detect shutdown
        app.events(event -> {
            event.serverStarting(() -> {
                log.debug("ApiRestServer starting...");
            });
            event.serverStarted(() -> {
                log.debug("ApiRestServer started.");
            });
            event.serverStopping(() -> {
                log.debug("ApiRestServer stopping...");
            });
            event.serverStopped(() -> {
                log.debug("ApiRestServer stopped.");
            });
        });
        return app.start(port);
    }
}